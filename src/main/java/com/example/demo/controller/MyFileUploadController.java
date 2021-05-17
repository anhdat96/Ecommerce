package com.example.demo.controller;

import com.example.demo.models.MyUpLoadFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MyFileUploadController {

    // hien thi trang upload
    @GetMapping("/upload")
    public String uploadOneFileHandler(Model model){
        MyUpLoadFile myUpLoadFile = new MyUpLoadFile();
        model.addAttribute("myUpLoadFile",myUpLoadFile);
        return "uploadOneFile";
    }

    // su ly upload
    @PostMapping("/upload")
    public String uploadOneFileHandlerPOST(HttpServletRequest request, Model model, @ModelAttribute("myUpLoadFile") MyUpLoadFile myUpLoadFile) throws IOException {
        return this.doUpLoad(request,model,myUpLoadFile);
    }

    private String doUpLoad(HttpServletRequest request, Model model, MyUpLoadFile myUpLoadFile) throws IOException {
        String description = myUpLoadFile.getDescription();
        System.out.println("Description: " + description);

        // thu muc goc upload file
        String uploadRootPath = request.getServletContext().getRealPath("upload");
        System.out.println("uploadRootPath=" + uploadRootPath);

        File uploadRootDir =  new File(uploadRootPath);
        // tao thu muc goc upload new no khong ton tai
        if(!uploadRootDir.exists()){
            uploadRootDir.mkdirs();
        }
        MultipartFile[] fileDatas = myUpLoadFile.getFileData();
        List<File> uploadedFiles = new ArrayList<File>();
        List<String> failedFiles = new ArrayList<String>();

        for(MultipartFile fileData: fileDatas){
            // ten file goc tai client
            String name = fileData.getOriginalFilename();
            if(name != null && name.length() >0){
                // tao file tai server
                File ServerFile = new File(uploadRootDir.getAbsolutePath() + File.separator+name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(ServerFile));
                stream.write(fileData.getBytes());
                stream.close();
                uploadedFiles.add(ServerFile);
            }
        }
        model.addAttribute("description",description);
        model.addAttribute("uploadfiles",uploadedFiles);
        model.addAttribute("failedFiles",failedFiles);
        return "uploadfile";
    }

}
