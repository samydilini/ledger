package calculator.com.ledger.loan.calulator.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import calculator.com.ledger.loan.calulator.models.Loan;

public class InputResolver {

    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    private LoanManager loanManager;

    public InputResolver() {
        loanManager = LoanManager.getLoanManager();
    }

    public InputResolver(LoanManager loanManager) {
        this.loanManager = loanManager;
    }

    public List<String> resolve(List<String> records) {
        for (String record : records) {
            List<String> recordValues = Arrays.asList(record.split(" "));

            String type = recordValues.get(0);
            Optional<InputTypes> inputValue = InputTypes.findInput(type);
            if (inputValue.isEmpty()) {
                LOGGER.log(Level.WARNING, String.format("Type : %1s was not found ", type));
                break;
            }
            switch (inputValue.get()) {
                case LOAN:
                    if (handleAddingLoan(record, recordValues).isEmpty()) {
                        return new ArrayList<>();
                    }

                    break;

                default:
                    break;
            }
        }
        return new ArrayList<>();
    }

    private Optional<Loan> handleAddingLoan(String record, List<String> recordValues) {
        try {
            return loanManager
                .getNewloan(recordValues.get(1), recordValues.get(2),
                    Integer.getInteger(recordValues.get(3)),
                    Integer.parseInt(recordValues.get(4)), Integer.parseInt(recordValues.get(5)));
        } catch (NumberFormatException ex) {
            LOGGER.log(Level.WARNING, String.format("Error occurred while converting: %s to a loan type", record));
            return Optional.empty();
        }
    }

}
