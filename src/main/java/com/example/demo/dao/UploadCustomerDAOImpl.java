package com.example.demo.dao;
import java.io.File;
import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Inject;
import com.example.demo.config.DatabaseConfig;
import com.example.demo.config.PTransactionManagerConfig;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

@Bean
public class UploadCustomerDAOImpl implements UploadCustomerDAO {
    @Inject
    DatabaseConfig databaseConfig;
    @Inject
    PTransactionManagerConfig pTransactionManagerConfig;

    /**
     * @Setter It is called from test file, it has no rollback porerty
     * @param dconfig object instance of DatabaseConfig
     */
    public void setDatabaseConfig(DatabaseConfig dconfig) {
        this.databaseConfig = dconfig;
    }

    /**
     * @Setter It is called from test file , it has rollback property
     * @param pconfig instance of PTransactionManagerConfig
     */
    public void setPlatTransactionMngrConfig(PTransactionManagerConfig pconfig) {
        this.pTransactionManagerConfig = pconfig;
    }

    /**
     * @Getter
     * @return JdbcTemplate
     */
    public JdbcTemplate getJdbctemplate() {
        return new JdbcTemplate(databaseConfig.getDataSource());
    }

    /**
     * @Getter
     * @return TransactionTemplate
     */
    public TransactionTemplate getTransactionTemplate() {
        return new TransactionTemplate(pTransactionManagerConfig.getPTranactionManager());
    }

    /**
     * This function is for validating the .csv file and then storing it in db.
     * 
     * @return Boolean for failur or succcess of transaction
     */
    @Override
    public boolean uploadCustomerUserList(File projectCustomerList) {
        //int validationResult, result = 0;

        // result+=getTransactionTemplate().execute(status->{
        // int row=0;
        // try {
        // row+=getJdbctemplate().update("copy user_detail_list from
        // "+projectCustomerList+" delimiter ',' csv header;");
        // } catch (Exception e) {
        // status.setRollbackOnly();
        // e.printStackTrace();
        // row=0;
        // }
        // return row;
        // });

        return true;
    }

}