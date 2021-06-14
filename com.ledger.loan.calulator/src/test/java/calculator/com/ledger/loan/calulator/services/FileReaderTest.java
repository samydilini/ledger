package calculator.com.ledger.loan.calulator.services;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import calculator.com.ledger.loan.calulator.exceptions.LedgerException;

@RunWith(MockitoJUnitRunner.class)
public class FileReaderTest {

    @InjectMocks
    FileReader fileReader;
    @Mock
    private InputResolver inputResolver;

    @Test(expected = LedgerException.class)
    public void testReadFileFromWrongPath() throws LedgerException {
        fileReader.readFile("wrongPath").size();
    }

    @Test
    public void testReadFileFromcorrectPath() throws LedgerException {
        final String testPath = "src/test/resources/test.txt";
        when(inputResolver.resolve(any())).thenReturn(any());
        fileReader.readFile(testPath);
        verify(inputResolver).resolve(any());
    }

}
