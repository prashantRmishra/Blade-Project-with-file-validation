package com.example.demo.service;


import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Inject;
import com.example.demo.dao.LoginDAO;
import com.example.demo.model.IFrame;

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
    public boolean setIFrameUrlService(IFrame iframe) {
        
        return loginDao.setIFrameUrlDaoPlatTransacMnger(iframe.getId(),iframe.getIFrameUrl(), iframe.getIFrameId());
    }

    
}