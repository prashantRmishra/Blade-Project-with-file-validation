package com.example.demo.config;
import com.blade.Blade;
import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Order;
import com.blade.loader.BladeLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;


@Bean
@Order(2)
public class PTransactionManagerConfig implements BladeLoader {
    @Inject
    DatabaseConfig dbConfig;
    private PlatformTransactionManager ptmDataSourceTM;
    @Override
    public void load(Blade blade) {
        this.ptmDataSourceTM = new DataSourceTransactionManager(dbConfig.getDataSource());
    }
    public PlatformTransactionManager getPTranactionManager(){
        return this.ptmDataSourceTM;
    }
    //to be called from test class
    public void setDataSource(PlatformTransactionManager ptm){
        this.ptmDataSourceTM=ptm;
    }
    
}