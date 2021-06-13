package calculator.com.ledger.loan.calulator.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import calculator.com.ledger.loan.calulator.models.Loan;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class InputResolverTest {

    public static final String SPACE = " ";
    @InjectMocks
    InputResolver inputResolver;
    @Mock
    private LoanManager loanManager;

    @Test
    public void testValidLoanCall() {
        String bankName = "IDIDI";
        String userName = "Dale";
        String amount = "10000";
        String years = "5";
        String rate = "4";
        Optional<Loan> optionalLoan = Optional.of(Mockito.mock(Loan.class));
        String loanRecord =
            "LOAN" + SPACE + bankName + SPACE + userName + SPACE + amount + SPACE + years + SPACE + rate;
        when(loanManager.getNewLoan(bankName, userName, Integer.parseInt(amount), Integer.parseInt(years),
            Integer.parseInt(rate))).thenReturn(optionalLoan);


        List<String> outPuts = inputResolver.resolve(Arrays.asList(loanRecord));
        verify(loanManager).getNewLoan(bankName, userName, Integer.parseInt(amount), Integer.parseInt(years),
            Integer.parseInt(rate));
        assertEquals(0, outPuts.size());
    }

    @Test
    public void testValidPaymentCall() {
        String bankName = "IDIDI";
        String userName = "Dale";
        String lump = "10000";
        String emai = "5";
        Optional<Loan> optionalLoan = Optional.of(Mockito.mock(Loan.class));
        String paymentCall = "PAYMENT" + SPACE + bankName + SPACE + userName + SPACE + lump + SPACE + emai;
        when(loanManager.makePayment(bankName, userName, Integer.parseInt(lump), Integer.parseInt(emai)
        )).thenReturn(optionalLoan);

        List<String> outPuts = inputResolver.resolve(Arrays.asList(paymentCall));
        verify(loanManager).makePayment(bankName, userName, Integer.parseInt(lump), Integer.parseInt(emai)
        );
        assertEquals(0, outPuts.size());
    }

    @Test
    public void testValidBalanceCall() {
        String bankName = "IDIDI";
        String userName = "Dale";
        String emai = "5";
        String balanceCall = "BALANCE" + SPACE + bankName + SPACE + userName + SPACE + emai;
        when(loanManager.generateBalance(bankName, userName, Integer.parseInt(emai)
        )).thenReturn("balanceString");

        List<String> outPuts = inputResolver.resolve(Arrays.asList(balanceCall));
        verify(loanManager).generateBalance(bankName, userName, Integer.parseInt(emai)
        );
        assertEquals(1, outPuts.size());
        assertEquals("balanceString", outPuts.get(0));
    }

    @Test
    public void testValidInputsCall() {
        String bankName = "IDIDI";
        String userName = "Dale";
        String amount = "10000";
        String years = "5";
        String rate = "4";
        Optional<Loan> optionalLoan = Optional.of(Mockito.mock(Loan.class));
        String loanRecord =
            "LOAN" + SPACE + bankName + SPACE + userName + SPACE + amount + SPACE + years + SPACE + rate;
        when(loanManager.getNewLoan(bankName, userName, Integer.parseInt(amount), Integer.parseInt(years),
            Integer.parseInt(rate))).thenReturn(optionalLoan);

        String lump = "10000";
        String emai = "5";
        String paymentCall = "PAYMENT" + SPACE + bankName + SPACE + userName + SPACE + lump + SPACE + emai;
        when(loanManager.makePayment(bankName, userName, Integer.parseInt(lump), Integer.parseInt(emai)
        )).thenReturn(optionalLoan);

        String balanceCall = "BALANCE" + SPACE + bankName + SPACE + userName + SPACE + emai;
        when(loanManager.generateBalance(bankName, userName, Integer.parseInt(emai)
        )).thenReturn("balanceString");

        List<String> outPuts = inputResolver.resolve(Arrays.asList(loanRecord, paymentCall, balanceCall));
        verify(loanManager).getNewLoan(bankName, userName, Integer.parseInt(amount), Integer.parseInt(years),
            Integer.parseInt(rate));
        verify(loanManager).makePayment(bankName, userName, Integer.parseInt(lump), Integer.parseInt(emai)
        );
        verify(loanManager).generateBalance(bankName, userName, Integer.parseInt(emai)
        );
        assertEquals(1, outPuts.size());
        assertEquals("balanceString", outPuts.get(0));
    }

    @Test
    public void testInValidInputsCall() {
        String bankName = "IDIDI";
        String userName = "Dale";
        String amount = "10000";
        String years = "A";
        String rate = "4";
        String loanRecord =
            "LOAN" + SPACE + bankName + SPACE + userName + SPACE + amount + SPACE + years + SPACE + rate;
        String lump = "10000";
        String emai = "5";
        String paymentCall = "PAYMENT" + SPACE + bankName + SPACE + userName + SPACE + lump + SPACE + emai;
        String balanceCall = "BALANCE" + SPACE + bankName + SPACE + userName + SPACE + emai;

        List<String> outPuts = inputResolver.resolve(Arrays.asList(loanRecord, paymentCall, balanceCall));
        assertEquals(0, outPuts.size());
    }

    @Test
    public void testWhenLoanNotRecorded() {
        String bankName = "IDIDI";
        String userName = "Dale";
        String amount = "10000";
        String years = "5";
        String rate = "4";
        String loanRecord =
            "LOAN" + SPACE + bankName + SPACE + userName + SPACE + amount + SPACE + years + SPACE + rate;
        when(loanManager.getNewLoan(bankName, userName, Integer.parseInt(amount), Integer.parseInt(years),
            Integer.parseInt(rate))).thenReturn(Optional.empty());

        String lump = "10000";
        String emai = "5";
        String paymentCall = "PAYMENT" + SPACE + bankName + SPACE + userName + SPACE + lump + SPACE + emai;
        Optional<Loan> optionalLoan = Optional.of(Mockito.mock(Loan.class));
        when(loanManager.makePayment(bankName, userName, Integer.parseInt(lump), Integer.parseInt(emai)
        )).thenReturn(optionalLoan);

        String balanceCall = "BALANCE" + SPACE + bankName + SPACE + userName + SPACE + emai;
        when(loanManager.generateBalance(bankName, userName, Integer.parseInt(emai)
        )).thenReturn("balanceString");

        List<String> outPuts = inputResolver.resolve(Arrays.asList(loanRecord, paymentCall, balanceCall));
        verify(loanManager).getNewLoan(bankName, userName, Integer.parseInt(amount), Integer.parseInt(years),
            Integer.parseInt(rate));
        verify(loanManager, times(0)).makePayment(bankName, userName, Integer.parseInt(lump), Integer.parseInt(emai)
        );
        verify(loanManager, times(0)).generateBalance(bankName, userName, Integer.parseInt(emai)
        );
        assertEquals(0, outPuts.size());
    }

    @Test
    public void testWhenPaymentNotWorkingNotRecorded() {
        String bankName = "IDIDI";
        String userName = "Dale";
        String amount = "10000";
        String years = "5";
        String rate = "4";
        Optional<Loan> optionalLoan = Optional.of(Mockito.mock(Loan.class));
        String loanRecord =
            "LOAN" + SPACE + bankName + SPACE + userName + SPACE + amount + SPACE + years + SPACE + rate;
        when(loanManager.getNewLoan(bankName, userName, Integer.parseInt(amount), Integer.parseInt(years),
            Integer.parseInt(rate))).thenReturn(optionalLoan);

        String lump = "10000";
        String emai = "5";
        String paymentCall = "PAYMENT" + SPACE + bankName + SPACE + userName + SPACE + lump + SPACE + emai;
        when(loanManager.makePayment(bankName, userName, Integer.parseInt(lump), Integer.parseInt(emai)
        )).thenReturn(Optional.empty());

        String balanceCall = "BALANCE" + SPACE + bankName + SPACE + userName + SPACE + emai;
        when(loanManager.generateBalance(bankName, userName, Integer.parseInt(emai)
        )).thenReturn("balanceString");

        List<String> outPuts = inputResolver.resolve(Arrays.asList(loanRecord, paymentCall, balanceCall));
        verify(loanManager).getNewLoan(bankName, userName, Integer.parseInt(amount), Integer.parseInt(years),
            Integer.parseInt(rate));
        verify(loanManager).makePayment(bankName, userName, Integer.parseInt(lump), Integer.parseInt(emai)
        );
        verify(loanManager, times(0)).generateBalance(bankName, userName, Integer.parseInt(emai)
        );
        assertEquals(0, outPuts.size());
    }

}
