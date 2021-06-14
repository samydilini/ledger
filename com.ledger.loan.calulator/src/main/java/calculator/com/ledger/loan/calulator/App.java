package calculator.com.ledger.loan.calulator;

import calculator.com.ledger.loan.calulator.exceptions.LedgerException;
import calculator.com.ledger.loan.calulator.services.FileReader;

import java.util.List;

public class App {

    private static FileReader fileReader = FileReader.getFileReaderInstance();

    public static void main(String[] args) {
        System.out.println("##################");

        try {
            List<String> values = fileReader.readFile(args[0]);
            values.forEach(System.out::println);

        } catch (LedgerException e) {
            e.printStackTrace();
        }
        System.out.println("##################");
    }

}
