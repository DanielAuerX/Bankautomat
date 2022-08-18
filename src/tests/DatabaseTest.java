package tests;

import code.Database;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    @Test
    void readCSV() {

    }

    @Test
    void readCSV_ShouldThrowException() {
        String incorrectFilepath = "R:\\Java\\Bankautomat\\WRONG-FILE-PATH.csv";
        assertThrows(FileNotFoundException.class,
                () -> {
                    Database.readCSV(incorrectFilepath);
                });
    }

    @Test
    void getCustomerData() {
    }

    @Test
    void getAccountData() {
    }

    @Test
    void writeAccountData() {
    }
}