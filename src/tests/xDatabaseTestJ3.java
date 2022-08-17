package tests;

import junit.framework.TestCase;

public class xDatabaseTestJ3 extends TestCase {
/*
    public void testFilepath_ShouldReturnDefaultLocation(){
        assertEquals("R:\\Java\\Bankautomat\\customer_database.csv", Database.filepath);
    }

    public void testReadCSV_ShouldBeMustermann() {
        ArrayList<ArrayList<String>> database = Database.readCSV(Database.filepath);
        assertEquals("Mustermann", database.get(0).get(1));
    }

    public void testReadCSV_ShouldBeMusterfrau() {
        ArrayList<ArrayList<String>> database = Database.readCSV(Database.filepath);
        assertEquals("Musterfrau", database.get(1).get(1));
    }

    public void testReadCSV_ShouldBe11111() {
        ArrayList<ArrayList<String>> database = Database.readCSV(Database.filepath);
        assertEquals("11111", database.get(0).get(4));
    }

    @Test
    @Disabled ("Not implemented yet, wait for JUnit5")
    public void testReadCSVShouldThrowException() {
        String incorrectFilepath = "R:\\Java\\Bankautomat\\customer_database.csv.csv.csv";
        ArrayList<ArrayList> database = Database.readCSV(incorrectFilepath);
        // assertThrows(FileNotFoundException.class, () ->{
        // Database.readCSV(incorrectFilepath);
        // });
    }

    public void testInstantiateCustomer_ShouldBeMustermann() {
        EosBankingApplication.validCustomerID = "1001";
        Customer testCustomer = new Customer("Max", "Mustermann", 0, 1001);
        Customer methodCustomer =  Database.instantiateCustomer();
        assertEquals(testCustomer.getLastName(), methodCustomer.getLastName());
    }

    public void testInstantiateCustomer_ShouldBeMusterfrau() {
        EosBankingApplication.validCustomerID = "2002";
        Customer testCustomer = new Customer("Melina", "Musterfrau", 1, 2002);
        Customer methodCustomer =  Database.instantiateCustomer();
        assertEquals(testCustomer.getLastName(), methodCustomer.getLastName());
    }

    public void testInstantiateCustomer_ShouldBeMax() {
        EosBankingApplication.validCustomerID = "1001";
        Customer testCustomer = new Customer("Max", "Mustermann", 0, 1001);
        Customer methodCustomer =  Database.instantiateCustomer();
        assertEquals(testCustomer.getFirstName(), methodCustomer.getFirstName());
    }

    public void testInstantiateCustomer_ShouldBeZero() {
        EosBankingApplication.validCustomerID = "1001";
        Customer testCustomer = new Customer("Max", "Mustermann", 0, 1001);
        Customer methodCustomer =  Database.instantiateCustomer();
        assertEquals(testCustomer.getGender(), methodCustomer.getGender());
    }

    public void testInstantiateAccount_ShouldBe22222() {
        EosBankingApplication.validCustomerID = "2002";
        Account testAccount = new Account(22222, 220, 0.00);
        Account methodAccount = Database.instantiateAccount();
        assertEquals(testAccount.getAccountNum(), methodAccount.getAccountNum());
    }

    public void testInstantiateAccount_ShouldBe220() {
        EosBankingApplication.validCustomerID = "2002";
        Account testAccount = new Account(22222, 220, 0.00);
        Account methodAccount = Database.instantiateAccount();
        assertEquals(testAccount.getPinNum(), methodAccount.getPinNum());
    }

    public void testInstantiateAccount_ShouldBe11111() {
        EosBankingApplication.validCustomerID = "1001";
        Account testAccount = new Account(11111, 110, 0.00);
        Account methodAccount = Database.instantiateAccount();
        assertEquals(testAccount.getAccountNum(), methodAccount.getAccountNum());
    }

    public void testWriteNewBalance_ShouldNotDoAnything() {
        Account testAccount = new Account(9999, 999, 100.00);
        Database.writeNewBalance(testAccount);
        assertEquals(100.00, testAccount.getBareBalance());
    }

    public void testWriteNewBalance_ShouldWriteNewBalancePlusOne() {
        EosBankingApplication.validCustomerID = "1001";
        Account testAccount = Database.instantiateAccount();
        double oldBalance = testAccount.getBareBalance();
        testAccount.setBalance(oldBalance+1);

        Database.writeNewBalance(testAccount);

        ArrayList<ArrayList<String>> database = Database.readCSV(Database.filepath);
        assertEquals(String.valueOf(oldBalance+1), String.valueOf(database.get(0).get(6)));

        testAccount.setBalance(oldBalance);
        Database.writeNewBalance(testAccount);
    }

    public void testWriteNewBalance_ShouldWriteNewBalanceInSecondAccount() {
        EosBankingApplication.validCustomerID = "2002";
        Account testAccount = Database.instantiateAccount();
        double oldBalance = testAccount.getBareBalance();
        testAccount.setBalance(oldBalance+1);

        Database.writeNewBalance(testAccount);

        ArrayList<ArrayList<String>> database = Database.readCSV(Database.filepath);
        assertEquals(String.valueOf(oldBalance+1), String.valueOf(database.get(1).get(6)));

        testAccount.setBalance(oldBalance);
        Database.writeNewBalance(testAccount);
    }

    public void testWriteNewBalance_ShouldWriteNewNegativeBalance() {
        EosBankingApplication.validCustomerID = "1001";
        Account testAccount = Database.instantiateAccount();
        double oldBalance = testAccount.getBareBalance();
        testAccount.setBalance(-1);

        Database.writeNewBalance(testAccount);
        ArrayList<ArrayList<String>> database = Database.readCSV(Database.filepath);

        assertEquals(String.valueOf(-1.0), String.valueOf(database.get(0).get(6)));

        testAccount.setBalance(oldBalance);
        Database.writeNewBalance(testAccount);
    }
    */
}