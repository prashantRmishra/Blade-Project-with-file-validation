package com.example.demo.service;

import java.io.BufferedReader;

public interface LoginService {
    public String getIFrameUrl(int iframeID);
    public boolean uploadCustomerUserList(BufferedReader projectCustomerList);
    boolean deleteRecord(int iframeId);
}