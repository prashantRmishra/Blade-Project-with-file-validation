package com.example.demo.service;

import java.io.BufferedReader;

import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Inject;
import com.example.demo.dao.LoginDAO;

@Bean
public class LoginServiceImpl implements LoginService {
    @Inject
    LoginDAO loginDao;

    @Override
    public String getIFrameUrl(int iframeID) {
        return loginDao.getIFrameUrlDao(iframeID);
    }

    @Override
    public boolean deleteRecord(int iframeId) {
        return loginDao.deleteRecord(iframeId);
    }

    @Override
    public boolean uploadCustomerUserList(BufferedReader projectCustomerList) {
        
        return loginDao.uploadCustomerUserList(projectCustomerList);
    }
    
}