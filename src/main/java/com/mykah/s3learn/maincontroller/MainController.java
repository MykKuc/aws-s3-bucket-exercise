package com.mykah.s3learn.maincontroller;


import com.mykah.s3learn.utilities.S3Util;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {

    @GetMapping
    public String getUpload(Model model) {
        model.addAttribute("something","coming from controller.");
        return "upload";
    }

    @PostMapping("/upload")
    public String handleUploadForm(Model model, String description,
                                   @RequestParam("file")MultipartFile multipartFile) {
        String filename = multipartFile.getOriginalFilename();

        System.out.println("Description of the file" + description);
        System.out.println("Filename" + filename);

        String message = "";

        S3Util mainS3Util = new S3Util();

        try {
            mainS3Util.saveS3ObjectInEuWest3InMykahBucket(filename, multipartFile.getInputStream());
            message = "File has been successfully uploaded.";
        } catch (Exception ex) {
            ex.printStackTrace();
            message = "upload was not successful";
        }

        model.addAttribute("message",message);

        return "message";
    }
}
