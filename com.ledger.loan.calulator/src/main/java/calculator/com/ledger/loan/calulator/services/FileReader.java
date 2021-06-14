package calculator.com.ledger.loan.calulator.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import calculator.com.ledger.loan.calulator.exceptions.LedgerException;

public class FileReader {

    private static FileReader fileReader;
    private static final Logger LOGGER = Logger.getLogger(FileReader.class.getName());
    private InputResolver inputResolver;

    private FileReader() {
        inputResolver = InputResolver.getInputResolverInstance();
    }

    public FileReader(InputResolver inputResolver) {
        this.inputResolver = inputResolver;
    }

    public static FileReader getFileReaderInstance() {
        if (fileReader == null) {
            fileReader = new FileReader();
        }
        return fileReader;
    }

    public List<String> readFile(String path) throws LedgerException {
        try {
            List<String> lines = Files.lines(Paths.get(path)).collect(Collectors.toList());
            return inputResolver.resolve(lines);
        } catch (IOException e) {
            String message = String.format("file %s not found", path);
            LOGGER.log(Level.WARNING, message);
            throw new LedgerException(message);
        }
    }

}
