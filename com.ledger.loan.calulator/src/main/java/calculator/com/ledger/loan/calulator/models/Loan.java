package calculator.com.ledger.loan.calulator.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Loan {


    public static final String SPACE = " ";
    private final String bank;
    private final int initAmount;
    private final String username;
    private int total;
    private int emiAmount;
    private Map<Integer, Integer> emiMap = new HashMap<>();
    private Map<Integer, Integer> lumpMap = new TreeMap<>();

    public Loan(String bank, String userName, int amount, int years, int rate) {
        this.bank = bank;
        this.username = userName;
        this.initAmount = amount;
        setInitValues(years, rate);
    }

    private void setInitValues(int years, int rate) {
        int interest = (initAmount * years * rate) / 100;
        this.total = initAmount + interest;
        int months = years * 12;
        this.emiAmount = Math.round((float) total / months);
        emiMap.put(0, months);
        for (int i = 1; i <= months; i++) {
            emiMap.put(Integer.valueOf(i), Integer.valueOf(months - i));
        }
    }

    public Loan addPayment(int lump, int emi) {
        Integer currentKey = Integer.valueOf(emi);
        lumpMap.put(currentKey, Integer.valueOf(lump));
        long paidEmisTillNow = emiAmount * emi;
        Integer lumps = getCurrentLumpTotal(currentKey);
        long currentTotal = total - (lumps + paidEmisTillNow);
        int remainingTime = Math.round((float) currentTotal / (float) emiAmount);
        int newEnd = emi + remainingTime;

        int counter = remainingTime;
        for (int i = emi; i <= emiMap.size(); i++) {
            Integer loopKey = Integer.valueOf(i);
            if (i > newEnd) {
                emiMap.remove(loopKey);
            }
             else {
                emiMap.put(loopKey,Integer.valueOf(counter));
                counter--;
            }
        }

        return this;
    }

    private Integer getCurrentLumpTotal(Integer currentKey) {
        Integer lumps = 0;
        Set<Integer> keys = lumpMap.keySet();
        for (Integer key : keys) {
            lumps += lumpMap.get(key);
            if (key.equals(currentKey)) {
                break;
            }
        }
        return lumps;
    }

    public String getBalance(int emi) {
        Integer remainingEmis = emiMap.get(Integer.valueOf(emi));
        long paidEmiAmount = calculateRemaining(emi, remainingEmis);//emiAmount * emi;
        return bank + SPACE + username + SPACE + paidEmiAmount + SPACE + remainingEmis.toString();
    }

    private long calculateRemaining(int emi, Integer remainingEmis) {
        List<Integer> lumps = lumpMap.keySet().stream().filter(key -> key <= Integer.valueOf(emi))
            .map(key -> lumpMap.get(key)).collect(Collectors.toList());

        Integer lumpsTotal = lumps.stream()
            .reduce(0, (l1, l2) -> l1 + l2);
        int newEmiAmount = emiAmount;

        if (total - (emiAmount * emi) < emiAmount) {

            newEmiAmount = Math.round(((float) total - (emiAmount * emi)) / remainingEmis);
        }
        return (newEmiAmount * emi)+lumpsTotal;
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
