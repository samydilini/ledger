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

    public Optional<Loan> getNewloan(String bankName, String userName, Integer integer, int parseInt, int parseInt1) {
        return Optional.of(new Loan());
    }

}
