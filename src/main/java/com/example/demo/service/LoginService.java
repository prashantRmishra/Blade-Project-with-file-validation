package com.example.demo.service;

import com.example.demo.model.IFrame;

public interface LoginService {
    public String getIFrameUrl(int iframeID);
    boolean deleteRecord(int iframeId);
    boolean setIFrameUrlService(IFrame iframe);
}