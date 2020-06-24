package com.example.demo.service;

import java.io.File;

import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Inject;
import com.example.demo.dao.UploadCustomerDAO;
@Bean
public class UploadCustomerServiceImpl implements UploadCustomerService {
    @Inject
    UploadCustomerDAO uploadCustomerDAO;
    @Override
    public boolean uploadCustomerUserList(File projectCustomerList) {
        
        return uploadCustomerDAO.uploadCustomerUserList(projectCustomerList);
    }
}