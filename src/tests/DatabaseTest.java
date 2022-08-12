package tests;

import code.*;
import junit.framework.TestCase;

import java.util.ArrayList;

public class DatabaseTest extends TestCase {

    public void testFilepath_ShouldReturnDefaultLocation(){
        assertEquals("R:\\Java\\Bankautomat\\customer_database.csv", Database.filepath);
    }

    public void testReadCSV_ShouldBeMustermann() {
        ArrayList<ArrayList> database = Database.readCSV(Database.filepath);
        assertEquals("Mustermann", database.get(0).get(1));
    }

    public void testReadCSV_ShouldBeMusterfrau() {
        ArrayList<ArrayList> database = Database.readCSV(Database.filepath);
        assertEquals("Musterfrau", database.get(1).get(1));
    }

    public void testReadCSV_ShouldBe11111() {
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

    public void testInstantiateCustomer_ShouldBeMustermann() {
        Main.validCustomerNum = "1001";
        Customer testCustomer = new Customer("Max", "Mustermann", 0, 1001);
        Customer methodCustomer =  Database.instantiateCustomer();
        assertEquals(testCustomer.getSecondName(), methodCustomer.getSecondName());
    }

    public void testInstantiateCustomer_ShouldBeMusterfrau() {
        Main.validCustomerNum = "2002";
        Customer testCustomer = new Customer("Melina", "Musterfrau", 1, 2002);
        Customer methodCustomer =  Database.instantiateCustomer();
        assertEquals(testCustomer.getSecondName(), methodCustomer.getSecondName());
    }

    public void testInstantiateCustomer_ShouldBeMax() {
        Main.validCustomerNum = "1001";
        Customer testCustomer = new Customer("Max", "Mustermann", 0, 1001);
        Customer methodCustomer =  Database.instantiateCustomer();
        assertEquals(testCustomer.getFirstName(), methodCustomer.getFirstName());
    }

    public void testInstantiateCustomer_ShouldBeZero() {
        Main.validCustomerNum = "1001";
        Customer testCustomer = new Customer("Max", "Mustermann", 0, 1001);
        Customer methodCustomer =  Database.instantiateCustomer();
        assertEquals(testCustomer.getGender(), methodCustomer.getGender());
    }

    public void testInstantiateAccount_ShouldBe22222() {
        Main.validCustomerNum = "2002";
        Account testAccount = new Account(22222, 220, 0.00);
        Account methodAccount = Database.instantiateAccount();
        assertEquals(testAccount.getAccountNum(), methodAccount.getAccountNum());
    }

    public void testInstantiateAccount_ShouldBe220() {
        Main.validCustomerNum = "2002";
        Account testAccount = new Account(22222, 220, 0.00);
        Account methodAccount = Database.instantiateAccount();
        assertEquals(testAccount.getPinNum(), methodAccount.getPinNum());
    }

    public void testInstantiateAccount_ShouldBe11111() {
        Main.validCustomerNum = "1001";
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
        Main.validCustomerNum = "1001";
        Account testAccount = Database.instantiateAccount();
        double oldBalance = testAccount.getBareBalance();
        testAccount.setBalance(oldBalance+1);

        Database.writeNewBalance(testAccount);

        ArrayList<ArrayList> database = Database.readCSV(Database.filepath);
        assertEquals(String.valueOf(oldBalance+1), String.valueOf(database.get(0).get(6)));

        testAccount.setBalance(oldBalance);
        Database.writeNewBalance(testAccount);
    }

    public void testWriteNewBalance_ShouldWriteNewBalanceInSecondAccount() {
        Main.validCustomerNum = "2002";
        Account testAccount = Database.instantiateAccount();
        double oldBalance = testAccount.getBareBalance();
        testAccount.setBalance(oldBalance+1);

        Database.writeNewBalance(testAccount);

        ArrayList<ArrayList> database = Database.readCSV(Database.filepath);
        assertEquals(String.valueOf(oldBalance+1), String.valueOf(database.get(1).get(6)));

        testAccount.setBalance(oldBalance);
        Database.writeNewBalance(testAccount);
    }

    public void testWriteNewBalance_ShouldWriteNewNegativeBalance() {
        Main.validCustomerNum = "1001";
        Account testAccount = Database.instantiateAccount();
        double oldBalance = testAccount.getBareBalance();
        testAccount.setBalance(-1);

        Database.writeNewBalance(testAccount);
        ArrayList<ArrayList> database = Database.readCSV(Database.filepath);

        assertEquals(String.valueOf(-1.0), String.valueOf(database.get(0).get(6)));

        testAccount.setBalance(oldBalance);
        Database.writeNewBalance(testAccount);
    }
}