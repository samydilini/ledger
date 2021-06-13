package calculator.com.ledger.loan.calulator.services;

import static org.junit.Assert.*;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import calculator.com.ledger.loan.calulator.models.Loan;

import java.util.Optional;

public class LoanManagerTest {

    private LoanManager loanManager;

    @Before
    public void setUp() {
        loanManager = LoanManager.getLoanManager();
    }

    @Test
    public void testAddNewLoan() {
        String bank = "IDIDI";
        String userName = "Dale";
        int amount = 1000;
        int years = 5;
        int rate = 4;
        Optional<Loan> optionalLoan = loanManager.getNewLoan(bank, userName, amount, years, rate);
        assertEquals(loanManager.getLoan(bank + userName), optionalLoan.get());
    }

    @Test
    public void testNotAddSameLoan() {
        String bank = "IDIDI1";
        String userName = "Dale1";
        int amount = 1000;
        int years = 5;
        int rate = 4;
        Optional<Loan> optionalLoan = loanManager.getNewLoan(bank, userName, amount, years, rate);
        int initMapSize = loanManager.getLoanMap().size();
        assertEquals(loanManager.getLoan(bank + userName), optionalLoan.get());
        Optional<Loan> secondValue = loanManager.getNewLoan(bank, userName, amount, years, rate);
        assertEquals(initMapSize, loanManager.getLoanMap().size());
        assertTrue(secondValue.isEmpty());
    }

    @Test
    public void testNotAddDifferentLoan() {
        String bank2 = "IDIDI2";
        String userName2 = "Dale2";
        int amount = 1000;
        int years = 5;
        int rate = 4;
        Optional<Loan> optionalLoan = loanManager.getNewLoan(bank2, userName2, amount, years, rate);
        int initMapSize = loanManager.getLoanMap().size();
        assertEquals(loanManager.getLoan(bank2 + userName2), optionalLoan.get());
        String bank3 = "IDIDI3";
        String userName3 = "Dale3";
        Optional<Loan> secondValue = loanManager.getNewLoan(bank3, userName3, amount, years, rate);
        assertEquals(initMapSize + 1, loanManager.getLoanMap().size());
        assertFalse(secondValue.isEmpty());
    }

    @Test
    public void testEmptyForNoLoan() {
        String bank = "IDIDI_PAY1";
        String userName = "Dale";

        int lump = 50;
        int payEmi = 3;
        Optional<Loan> valueAfterPay = loanManager.makePayment(bank, userName, lump, payEmi);
        assertTrue(valueAfterPay.isEmpty());
    }

    @Test
    public void testPayForExistingLoan() {
        String bank = "IDIDI_PAY2";
        String userName = "Dale";
        int amount = 1000;
        int years = 5;
        int rate = 4;
        loanManager.getNewLoan(bank, userName, amount, years, rate);

        int lump = 50;
        int payEmi = 3;
        Optional<Loan> valueAfterPay = loanManager.makePayment(bank, userName, lump, payEmi);
        assertTrue(valueAfterPay.isPresent());
    }

    @Test
    public void testBalanceNullForLoanNotFound() {
        String bank = "IDIDI_BAL1";
        String userName = "Dale";

        int payEmi = 3;
        String balance = loanManager.generateBalance(bank, userName, payEmi);
        assertTrue(StringUtils.isBlank(balance));
    }

    @Test
    public void testBalanceExistingLoanNotFound() {
        String bank = "IDIDI_BAL2";
        String userName = "Dale";
        int amount = 1000;
        int years = 5;
        int rate = 4;
        loanManager.getNewLoan(bank, userName, amount, years, rate);

        int payEmi = 3;
        String balance = loanManager.generateBalance(bank, userName, payEmi);
        assertFalse(StringUtils.isBlank(balance));
    }

}
