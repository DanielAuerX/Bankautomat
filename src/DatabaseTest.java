import junit.framework.TestCase;

import java.util.ArrayList;

public class DatabaseTest extends TestCase {

    public void testFilepathShouldReturnDefaultLocation(){
        assertEquals("R:\\Java\\Bankautomat\\customer_database.csv", Database.filepath);
    }

    public void testReadCSVShouldBeMustermann() {
        ArrayList<ArrayList> database = Database.readCSV(Database.filepath);
        assertEquals("Mustermann", database.get(0).get(1));
    }

    public void testReadCSVShouldBeMusterfrau() {
        ArrayList<ArrayList> database = Database.readCSV(Database.filepath);
        assertEquals("Musterfrau", database.get(1).get(1));
    }

    public void testReadCSVShouldBe11111() {
        ArrayList<ArrayList> database = Database.readCSV(Database.filepath);
        assertEquals("11111", database.get(0).get(4));
    }

    /*@Test
    @Disabled ("Not implemented yet, wait for JUnit5")
    public void testReadCSVShouldThrowException() {
        String incorrectFilepath = "R:\\Java\\Bankautomat\\customer_database.csv.csv.csv";
        ArrayList<ArrayList> database = Database.readCSV(incorrectFilepath);
        // assertThrows(FileNotFoundException.class, () ->{
        // Database.readCSV(incorrectFilepath);
        // });
    }*/

    public void testInstantiateCustomerShouldBeMustermann() {
        Main.validCustomerNum = "1001";
        Customer testCustomer = new Customer("Max", "Mustermann", 0, 1001);
        Customer methodCustomer =  Database.instantiateCustomer();
        assertEquals(testCustomer.secondName, methodCustomer.secondName);
    }

    public void testInstantiateCustomerShouldBeMusterfrau() {
        Main.validCustomerNum = "2002";
        Customer testCustomer = new Customer("Melina", "Musterfrau", 1, 2002);
        Customer methodCustomer =  Database.instantiateCustomer();
        assertEquals(testCustomer.secondName, methodCustomer.secondName);
    }

    public void testInstantiateCustomerShouldBeMax() {
        Main.validCustomerNum = "1001";
        Customer testCustomer = new Customer("Max", "Mustermann", 0, 1001);
        Customer methodCustomer =  Database.instantiateCustomer();
        assertEquals(testCustomer.firstName, methodCustomer.firstName);
    }

    public void testInstantiateCustomerShouldBeZero() {
        Main.validCustomerNum = "1001";
        Customer testCustomer = new Customer("Max", "Mustermann", 0, 1001);
        Customer methodCustomer =  Database.instantiateCustomer();
        assertEquals(testCustomer.gender, methodCustomer.gender);
    }

    public void testInstantiateAccountShouldBe22222() {
        Main.validCustomerNum = "2002";
        Account testAccount = new Account(22222, 220, 0.00);
        Account methodAccount = Database.instantiateAccount();
        assertEquals(testAccount.accountNum, methodAccount.accountNum);
    }

    public void testInstantiateAccountShouldBe220() {
        Main.validCustomerNum = "2002";
        Account testAccount = new Account(22222, 220, 0.00);
        Account methodAccount = Database.instantiateAccount();
        assertEquals(testAccount.pinNum, methodAccount.pinNum);
    }

    public void testInstantiateAccountShouldBe11111() {
        Main.validCustomerNum = "1001";
        Account testAccount = new Account(11111, 110, 0.00);
        Account methodAccount = Database.instantiateAccount();
        assertEquals(testAccount.accountNum, methodAccount.accountNum);
    }

    public void testWriteNewBalanceShouldNotDoAnything() {
        Account testAccount = new Account(9999, 999, 100.00);
        Database.writeNewBalance(testAccount);
        assertEquals(100.00, testAccount.balance);
    }

    public void testWriteNewBalanceShouldWriteNewBalancePlusOne() {
        Main.validCustomerNum = "1001";
        Account testAccount = Database.instantiateAccount();
        double oldBalance = testAccount.balance;
        testAccount.balance += 1;
        Database.writeNewBalance(testAccount);
        ArrayList<ArrayList> database = Database.readCSV(Database.filepath);
        assertEquals(String.valueOf(oldBalance+1), String.valueOf(database.get(0).get(6)));
    }

    public void testWriteNewBalanceShouldWriteNewBalanceInSecondAccount() {
        Main.validCustomerNum = "2002";
        Account testAccount = Database.instantiateAccount();
        double oldBalance = testAccount.balance;
        testAccount.balance += 1;
        Database.writeNewBalance(testAccount);
        ArrayList<ArrayList> database = Database.readCSV(Database.filepath);
        assertEquals(String.valueOf(oldBalance+1), String.valueOf(database.get(1).get(6)));
    }

    public void testWriteNewBalanceShouldWriteNewNegativeBalance() {
        Main.validCustomerNum = "1001";
        Account testAccount = Database.instantiateAccount();
        testAccount.balance = -1;
        Database.writeNewBalance(testAccount);
        ArrayList<ArrayList> database = Database.readCSV(Database.filepath);
        assertEquals(String.valueOf(-1.0), String.valueOf(database.get(0).get(6)));
    }
}