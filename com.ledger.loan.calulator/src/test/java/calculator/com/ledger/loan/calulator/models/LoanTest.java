package calculator.com.ledger.loan.calulator.models;

import static org.junit.Assert.*;

import org.junit.Test;

public class LoanTest {

    @Test
    public void testCreateLoan(){
        Loan loanDale = new Loan("IDIDI", "Dale",10000,5,4);
        assertEquals("IDIDI Dale 1000 55", loanDale.getBalance(5));
        assertEquals("IDIDI Dale 8000 20", loanDale.getBalance(40));

        Loan loanHarry = new Loan("MBI", "Harry",2000,2,2);
        assertEquals("MBI Harry 1044 12", loanHarry.getBalance(12));
        assertEquals("MBI Harry 0 24", loanHarry.getBalance(0));
    }

    @Test
    public void testPayLoans(){
        Loan loanDale = new Loan("IDIDI", "Dale",5000,1,6);
        Loan loanHarry = new Loan("MBI", "Harry",10000,3,7);
        Loan loanShelly = new Loan("UON", "Shelly",15000,2,9);

        loanDale.addPayment(1000,5);
        loanHarry.addPayment(5000,10);
        loanShelly.addPayment(7000,12);


        assertEquals("IDIDI Dale 1326 9", loanDale.getBalance(3));
        assertEquals("IDIDI Dale 3652 4", loanDale.getBalance(6));
        assertEquals("UON Shelly 15856 2", loanShelly.getBalance(12));
        assertEquals("MBI Harry 9032 9", loanHarry.getBalance(12));
    }


}
