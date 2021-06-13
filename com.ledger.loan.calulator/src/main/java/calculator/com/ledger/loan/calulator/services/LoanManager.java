package calculator.com.ledger.loan.calulator.services;

import calculator.com.ledger.loan.calulator.models.Loan;

import java.util.Optional;

public class LoanManager {

    private static LoanManager loanManager;

    private LoanManager() {
    }

    public static LoanManager getLoanManager() {
        if (loanManager == null) {
            loanManager = new LoanManager();
        }
        return loanManager;
    }

    public Optional<Loan> getNewLoan(String bankName, String userName, Integer integer, int parseInt, int parseInt1) {
        return Optional.of(new Loan());
    }

    public Optional<Loan> makePayment(String s, String s1, Integer integer, int parseInt) {
        return Optional.of(new Loan());
    }

    public String generateBalance(String s, String s1, int parseInt) {
        return null;
    }

}
