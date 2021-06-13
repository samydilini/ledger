package calculator.com.ledger.loan.calulator.services;

import calculator.com.ledger.loan.calulator.models.Loan;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LoanManager {

    private static LoanManager loanManager;

    private Map<String, Loan> loanMap = new HashMap<>();

    private LoanManager() {
    }

    public static LoanManager getLoanManager() {
        if (loanManager == null) {
            loanManager = new LoanManager();
        }
        return loanManager;
    }

    public Optional<Loan> getNewLoan(String bankName, String userName, int amount, int years, int rate) {
        String key = bankName + userName;
        if (loanMap.get(key) != null) {
            return Optional.empty();
        }

        Loan loan = new Loan(bankName, userName, amount, years, rate);
        loanMap.put(key, loan);
        return Optional.of(loan);
    }

    public Optional<Loan> makePayment(String bankName, String userName, int lump, int emi) {
        String key = bankName + userName;
        Loan loan = loanMap.get(key);
        if (loan == null) {
            return Optional.empty();
        }

        return Optional.of(loan.addPayment(lump, emi));
    }

    public String generateBalance(String bankName, String userName, int emi) {
        String key = bankName + userName;
        Loan loan = loanMap.get(key);
        if (loan == null) {
            return null;
        }
        return loan.getBalance(emi);
    }

    protected Loan getLoan(String key) {
        return loanMap.get(key);
    }

    protected Map<String, Loan> getLoanMap() {
        return loanMap;
    }

}
