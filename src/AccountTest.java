import junit.framework.TestCase;

public class AccountTest extends TestCase {

    public void testGetBalanceShouldReturnHundred() {
            Account account = new Account(1001, 110, 100.00);
            assertEquals("\n"+Main.TEXT_BACKGROUND+"Ihr Kontostand beträgt: "+Main.BOLD+"100,00€\n"+Main.RESET, account.getBalance());
    }

    public void testGetBalanceShouldReturnMinusOneInRed() {
        Account account = new Account(1001, 110, -1);
        assertEquals("\n"+Main.TEXT_BACKGROUND+"Ihr Kontostand beträgt: "+Main.BOLD+Main.RED_BACKGROUND+"-1,00€\n"+Main.RESET, account.getBalance());
    }

    public void testGetBalanceShouldReturnZero() {
        Account account = new Account(1001, 110, 0);
        assertEquals("\n"+Main.TEXT_BACKGROUND+"Ihr Kontostand beträgt: "+Main.BOLD+"0,00€\n"+Main.RESET, account.getBalance());
    }

    public void testGetBalanceShouldReturnTwoKommaZeroZero() {
        Account account = new Account(1001, 110, 1.99999);
        assertEquals("\n"+Main.TEXT_BACKGROUND+"Ihr Kontostand beträgt: "+Main.BOLD+"2,00€\n"+Main.RESET, account.getBalance());
    }

    public void testDeposit() {

    }

    public void testWithdraw() {
    }

    public void testValidatePin() {
    }
}