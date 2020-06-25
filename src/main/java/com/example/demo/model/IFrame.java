package com.example.demo.model;

public class IFrame {
    private int id;
    private int iframeId;
    private String iframeUrl;


    public void setId(int id){
        this.id=id;
    }
    public void setIFrameId(int iframeId){
        this.iframeId=iframeId;
    }
    public void setId(String iframeUrl){
        this.iframeUrl=iframeUrl;
    }
    public int getId(){
        return this.id;
    }
    public int getIFrameId(){
        return this.iframeId;
    }
    public String getIFrameUrl(){
        return this.iframeUrl;
    }


}