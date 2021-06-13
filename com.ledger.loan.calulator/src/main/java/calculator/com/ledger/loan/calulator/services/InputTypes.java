package calculator.com.ledger.loan.calulator.services;

import java.util.Optional;

public enum InputTypes {
    LOAN;

    public static Optional<InputTypes> findInput(String stringValue) {
        for (InputTypes type : InputTypes.values()) {
            if (type.name().equalsIgnoreCase(stringValue)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}