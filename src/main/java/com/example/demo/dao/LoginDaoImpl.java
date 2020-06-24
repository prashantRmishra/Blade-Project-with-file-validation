package com.example.demo.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Inject;
import com.example.demo.config.DatabaseConfig;
import com.example.demo.config.PTransactionManagerConfig;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.support.TransactionTemplate;
@Bean
public class LoginDaoImpl implements LoginDAO {
    @Inject
    DatabaseConfig databaseConfig;
    @Inject
    PTransactionManagerConfig pTransactionManagerConfig;

    /**
     * @Setter
     * It is called from test file, it has no rollbak porerty 
     * @param dconfig object instance of DatabaseConfig
    */
    public void setDatabaseConfig(DatabaseConfig dconfig){
        this.databaseConfig= dconfig;
    }
    /**
     * @Setter
     * It is called from test file , it has rollback property
     * @param pconfig instance of PTransactionManagerConfig
    */
    public void setPlatTransactionMngrConfig(PTransactionManagerConfig pconfig){
        this.pTransactionManagerConfig= pconfig;
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
    public TransactionTemplate getTransactionTemplate(){
        return new TransactionTemplate(pTransactionManagerConfig.getPTranactionManager());
    }
    /**
     * @param iframeID is used to get id for respective iframeUrl
     * @return iframeUrl String from database
    */
    @Override
    public String getIFrameUrlDao(int iframeID) {
        List<String> urlResult=new ArrayList<>();
       try {
           
           urlResult= getJdbctemplate().query("select \"iframeURL\" from tb_iframe_details where \"iframeID\"=? and delete_flag=?", new  Object[]{iframeID,false}, new RowMapper<String>(){

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                String iframeURL=null;
                iframeURL=rs.getString("iframeURL");
				return iframeURL;
			}});

       } catch (Exception e) {
           e.printStackTrace();
       }
        return urlResult.get(0);
    }
    /**
     * @param pkey is the primary key 
     * @param iframeID is the id for respective iframeUrl
     * @param iframeUrl is the new Url that user wnts to store into database
     * @return boolean value as a result of success or failure of transaction
    */
    @Override
    public boolean setIFrameUrlDaoPlatTransacMnger(int pkey, String iframeUrl,int iframeID) {
        int result=0;
        result+=getTransactionTemplate().execute(status->{
            int row=0;
            try {
                //iframeOp+=getJdbctemplate().update("insert into iframe_op values(?,?)", new Object[]{33,true});
                row+=getJdbctemplate().update("insert into tb_iframe_details values(?,?,?,?) ", new Object[]{pkey,iframeUrl,iframeID,false});
                
            } catch (Exception e) {

                status.setRollbackOnly();

                row=0;
            }
            return row;
        });
        return 0<result;
    }
 /**
     * @param iframeID is the id for respective iframeUrl fr its deletion
     * @return boolean value as a result of success or failure of transaction
    */
    @Override
    public boolean deleteRecord(int iframeID) {
       int result =0;
       result+=getTransactionTemplate().execute(status->{
           int row=0;
           try {
            row+=getJdbctemplate().update("update tb_iframe_details set delete_flag=? where \"iframeID\"=? and delete_flag=?", new Object[]{true,iframeID,false});
           
           } catch (Exception e) {
               status.setRollbackOnly();
               row=0;
           }
           return row;
       });
        return 0<result;
    }

    
}