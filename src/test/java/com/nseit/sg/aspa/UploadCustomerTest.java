package com.nseit.sg.aspa;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;

import com.nseit.sg.aspa.config.DatabaseConfig;
import com.nseit.sg.aspa.config.PTransactionManagerConfig;
import com.nseit.sg.aspa.uploadCustomerUser.dao.UploadCustomerDAOImpl;
import com.nseit.sg.aspa.util.PropFileReader;
import com.nseit.sg.aspa.webhook.UploadCustomerUserInterceptor;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

public class UploadCustomerTest {
    static DatabaseConfig databaseConfig;
    static PTransactionManagerConfig pTransactionManagerConfig;
    static PlatformTransactionManager ptmDataSource;
    static PropFileReader propFileReader;

    /**
     * This method has common operation that are executed before running the tests
     * 
     */
    @BeforeAll
    static void setup() {
        BasicDataSource bs = new BasicDataSource();
        bs.setDriverClassName("org.postgresql.Driver");
        bs.setUrl("jdbc:postgresql://localhost:5432/StargateSPA");
        bs.setUsername("postgres");
        bs.setPassword("password");
        databaseConfig = new DatabaseConfig();
        databaseConfig.setDataSource(bs);
        ptmDataSource = new DataSourceTransactionManager(databaseConfig.getDataSource());
        pTransactionManagerConfig = new PTransactionManagerConfig();
        pTransactionManagerConfig.setDataSource(ptmDataSource);
        propFileReader = new PropFileReader("src/main/resources/mycsv.csv",
                "src/main/resources/uploadUserDetailsRule.json");
        databaseConfig.setPropFileReader(propFileReader);

    }

    /**
     * This operation is executed after all the tests are excecuted
     */
    @AfterAll
    static void closeup() {
        databaseConfig = null;
        System.out.println();
        System.out.println("!!!!!!!!All Upload Customer Test Finished!!!!!!!!");
    }
    
    /*
     * This methods tests  uploadCustomerUserListValidation() method of interceptor 
     * @throws FileNotFoundException
    */
    @Test
    void printFileDetails() throws FileNotFoundException {
        boolean result;
        UploadCustomerDAOImpl uploadCustomerDAOImpl = new UploadCustomerDAOImpl();
        UploadCustomerUserInterceptor intercept = new UploadCustomerUserInterceptor();
        uploadCustomerDAOImpl.setDatabaseConfig(databaseConfig);
        intercept.setDatabaseConfig(databaseConfig);
        File file = new File("src/main/resources/userDetailList.csv");
        result=intercept.uploadCustomerUserListValidation(file) > 0;
        // FileReader fileForTesting = new FileReader(file);
        // BufferedReader brFile = new BufferedReader(fileForTesting) ; 
        //result=uploadCustomerDAOImpl.uploadCustomerUserList(file);
        assertTrue(result);

    }
    
}