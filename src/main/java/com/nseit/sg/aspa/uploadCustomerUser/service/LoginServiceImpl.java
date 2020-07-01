package com.nseit.sg.aspa.uploadCustomerUser.service;


import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Inject;
import com.nseit.sg.aspa.uploadCustomerUser.dao.LoginDAO;
import com.nseit.sg.aspa.uploadCustomerUser.model.IFrame;

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