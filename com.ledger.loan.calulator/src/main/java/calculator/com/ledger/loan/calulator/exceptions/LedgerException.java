package calculator.com.ledger.loan.calulator.exceptions;

public class LedgerException extends Throwable {

    private static final long serialVersionUID = 1L;

    private String message;

    public LedgerException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}