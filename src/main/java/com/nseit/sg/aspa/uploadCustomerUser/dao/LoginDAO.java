package com.nseit.sg.aspa.uploadCustomerUser.dao;

public interface LoginDAO {
    String getIFrameUrlDao( int iframeID);
    boolean setIFrameUrlDaoPlatTransacMnger( int pkey,String iframeUrl,int iframeID);
    boolean deleteRecord(int iframeID);
}