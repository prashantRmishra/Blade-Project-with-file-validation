package com.example.demo.controller;
import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.BodyParam;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PathParam;
import com.blade.mvc.annotation.PostRoute;
import com.example.demo.model.IFrame;
import com.example.demo.service.LoginService;

@Path
public class DashboardController {
    @Inject
    LoginService loginService;

    @GetRoute("/getIframeUrl/:iframeID")
    @JSON
    public String getIframeUrl(@PathParam int iframeID) {
        String iframeURL = null;

        try {
            iframeURL = loginService.getIFrameUrl(iframeID);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return iframeURL;
    }

    @PostRoute("/delete/:iframeId")
    public void delete(@PathParam int iframeId){
        try {
            boolean result;
            result=loginService.deleteRecord(iframeId);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    @PostRoute("/api")
    @JSON
    public void add(@BodyParam IFrame iFrame){
        System.out.println("add called "+iFrame.getIFrameId()+" iframe :"+ iFrame);
        try {
            boolean result;
            result=loginService.setIFrameUrlService(iFrame);
            System.out.println("api add insert status "+result);
            
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
    
    // @PostRoute("/upload")
    // public void upload(Request request) {
    //     System.out.println("in upload function");
    //     request.fileItem("upload").ifPresent(fileItem -> {
    //         try {
    //             boolean result;
    //             fileItem.moveTo(new File(fileItem.getFileName()));
    //             System.out.println("file recieved "+fileItem.getFileName());
    //             File file = new File(fileItem.getFileName());
    //             // FileReader fileReader = new FileReader(file);
    //             // BufferedReader projectCustomerList = new BufferedReader(fileReader);
    //             result = loginService.uploadCustomerUserList(file);
    //             System.out.println("status:"+result);
    //         } catch (IOException e) {
    //             e.printStackTrace();
    //         }
    //     });
    // }

}