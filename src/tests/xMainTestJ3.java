package tests;

import junit.framework.TestCase;

public class xMainTestJ3 extends TestCase {
    /*

    public void testMain_ShouldReturnGutenTagHerrMustermann() {
        String userInput = "1001" + "\n110" + "\n4";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        String expectedCatch = "Bitte Kundennummer eingeben: Guten Tag Herr Mustermann!";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        EosBankingApplication.main(null);

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-2];

        assertEquals(expectedCatch, actual);
    }

    public void testMain_ShouldReturnMenuWithCostumerNumber() {
        String userInput = "1001" + "\n110" + "\n4";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        String expectedCatch = "Bitte Geheimzahl eingeben: " +
                EosBankingApplication.BOLD+"Sie haben Zugriff auf Ihr Konto mit der Nummer 1001"+ EosBankingApplication.RESET+
                "\nWählen Sie\n" +
                "1 - Kontostand abfragen\n" +
                "2 - Einzahlen\n" +
                "3 - Abheben\n" +
                "4 - System verlassen\n\n" +
                "Eingabe: " +
                "Auf Wiedersehen!";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        EosBankingApplication.main(null);

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];

        assertEquals(expectedCatch, actual);
    }

    public void testMain_ShouldReturnInkorrekteEingabe() {
        String userInput = "1001" + "\n110" + "\nasdfasd" + "\n4";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        String expectedCatch = "Bitte Geheimzahl eingeben: " +
                EosBankingApplication.BOLD+"Sie haben Zugriff auf Ihr Konto mit der Nummer 1001"+ EosBankingApplication.RESET+
                "\nWählen Sie\n" +
                "1 - Kontostand abfragen\n" +
                "2 - Einzahlen\n" +
                "3 - Abheben\n" +
                "4 - System verlassen\n\n" +
                "Eingabe: " +
                "Inkorrekte Eingabe! Versuchen Sie es erneut.\n";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        EosBankingApplication.main(null);

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-2];

        assertEquals(expectedCatch, actual);
    }


     */
}