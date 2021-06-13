package calculator.com.ledger.loan.calulator.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import calculator.com.ledger.loan.calulator.models.Loan;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class InputResolverTest {

    public static final String SPACE = " ";
    @InjectMocks
    InputResolver inputResolver;
    @Mock
    private LoanManager loanManager;

    @Test
    public void testValidLoanCall() {
        String bankName = "IDIDI";
        String userName = "Dale";
        String amount = "10000";
        String years = "5";
        String rate = "4";
        String record = "LOAN" + SPACE + bankName + SPACE + userName + SPACE + amount + SPACE + years + SPACE + rate;
        when(loanManager.getNewloan(bankName, userName, Integer.getInteger(amount), Integer.parseInt(years),
            Integer.parseInt(rate))).thenReturn(Optional.of(new Loan()));

        List<String> outPuts = inputResolver.resolve(Arrays.asList(record));
        verify(loanManager).getNewloan(bankName, userName, Integer.getInteger(amount), Integer.parseInt(years),
            Integer.parseInt(rate));
        assertEquals(0, outPuts.size());
    }

    @Test
    public void testValidCall() {
        String bankName = "IDIDI";
        String userName = "Dale";
        String amount = "10000";
        String years = "5";
        String rate = "4";
        String loanCall = "LOAN" + SPACE + bankName + SPACE + userName + SPACE + amount + SPACE + years + SPACE + rate;
        when(loanManager.getNewloan(bankName, userName, Integer.getInteger(amount), Integer.parseInt(years),
            Integer.parseInt(rate))).thenReturn(Optional.of(new Loan()));

        List<String> outPuts = inputResolver.resolve(Arrays.asList(loanCall));
        verify(loanManager).getNewloan(bankName, userName, Integer.getInteger(amount), Integer.parseInt(years),
            Integer.parseInt(rate));
        assertEquals(0, outPuts.size());
    }

}
