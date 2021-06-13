package calculator.com.ledger.loan.calulator.models;

import static org.junit.Assert.*;

import org.junit.Test;

public class LoanTest {

    @Test
    public void testCreateLoan(){
        String bank = "IDIDI";
        String userName = "Dale";
        int amount = 10000;
        int years = 5;
        int rate = 4;

        Loan loan = new Loan(bank, userName,amount,years,rate);
        assertEquals("IDIDI Dale 1000 55", loan.getBalance(5));
    }

}
