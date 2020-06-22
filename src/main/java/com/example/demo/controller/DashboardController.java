package com.example.demo.controller;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PathParam;
import com.example.demo.service.LoginService;

@Path
public class DashboardController {
    @Inject
    LoginService loginService;
    @GetRoute("/getIframeUrl/:iframeID")
    @JSON
    public String getIframeUrl(@PathParam int iframeID){
        String iframeURL=null;
        
        try {
            iframeURL=loginService.getIFrameUrl(iframeID);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iframeURL;
    }
}