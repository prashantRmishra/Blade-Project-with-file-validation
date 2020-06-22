package com.example.demo.service;

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
    
}