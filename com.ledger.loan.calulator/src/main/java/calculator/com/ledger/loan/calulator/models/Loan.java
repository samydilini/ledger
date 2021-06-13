package calculator.com.ledger.loan.calulator.models;

public class Loan {


    private final String bank;
    private final int initAmount;
    private final String username;

    public Loan(String bank, String userName, int amount, int years, int rate) {
        this.bank = bank;
        this.username = userName;
        this.initAmount = amount;
    }

    public Loan addPayment(Integer lump, int emi) {
        return this;
    }

    public String getBalance(int emi) {
        return InputTypes.BALANCE.name();
    }

    public String getBank() {
        return bank;
    }

    public int getInitAmount() {
        return initAmount;
    }

    public String getUsername() {
        return username;
    }

}
