package calculator.com.ledger.loan.calulator.services;

import calculator.com.ledger.loan.calulator.models.Loan;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoanManager {

    private static final Logger LOGGER = Logger.getLogger(LoanManager.class.getName());

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
            LOGGER.log(Level.WARNING, String.format("loan was already created for the user : %1s with Bank Name%2s ",
                userName, bankName));
            return Optional.empty();
        }

        Loan loan = new Loan(bankName, userName, amount, years, rate);
        loanMap.put(key, loan);
        return Optional.of(loan);
    }

    public Optional<Loan> makePayment(String bankName, String userName, int lump, int emi) {
        Optional<Loan> loan = findLoan(bankName, userName);

        return loan.isEmpty() ? loan : Optional.of(loan.get().addPayment(lump, emi));
    }

    private Optional<Loan> findLoan(String bankName, String userName) {
        String key = bankName + userName;
        Loan loan = loanMap.get(key);
        if (loan == null) {
            LOGGER.log(Level.WARNING, String.format("loan was not found for the user : %1s with Bank Name%2s ",
                userName, bankName));
            return Optional.empty();
        }
        return Optional.of(loan);
    }

    public String generateBalance(String bankName, String userName, int emi) {
        Optional<Loan> loan = findLoan(bankName, userName);

        return loan.isEmpty()?null: loan.get().getBalance(emi);
    }

    protected Loan getLoan(String key) {
        return loanMap.get(key);
    }

    protected Map<String, Loan> getLoanMap() {
        return loanMap;
    }

}
