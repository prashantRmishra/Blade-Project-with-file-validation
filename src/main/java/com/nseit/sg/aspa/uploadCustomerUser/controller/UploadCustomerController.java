package com.nseit.sg.aspa.uploadCustomerUser.controller;

import java.io.File;
import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.http.Request;
import com.nseit.sg.aspa.uploadCustomerUser.service.UploadCustomerService;

@Path
public class UploadCustomerController {
    @Inject
    UploadCustomerService uploadCustomerService;
    
    @PostRoute("/upload")
    public void upload(Request request) {
        System.out.println("in upload function");
        request.fileItem("upload").ifPresent(fileItem -> {
            try {
                boolean result;
                // fileItem.moveTo(new File(fileItem.getFileName()));
                // System.out.println("file recieved "+fileItem.getFileName());
                File file = new File(fileItem.getFile().getAbsolutePath());
                // FileReader fileReader = new FileReader(file);
                // BufferedReader projectCustomerList = new BufferedReader(fileReader);
                result = uploadCustomerService.uploadCustomerUserList(file);
                System.out.println("status from dao:"+result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}