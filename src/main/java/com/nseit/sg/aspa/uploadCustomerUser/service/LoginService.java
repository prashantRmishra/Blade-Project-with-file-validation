package com.nseit.sg.aspa.uploadCustomerUser.service;

import com.nseit.sg.aspa.uploadCustomerUser.model.IFrame;

public interface LoginService {
    public String getIFrameUrl(int iframeID);
    boolean deleteRecord(int iframeId);
    boolean setIFrameUrlService(IFrame iframe);
}