package com.study.springboot;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

@Controller
public class FileUploadController {
   
   @RequestMapping("/")
   public String root() {
      return "fileForm";
   }
   
   @RequestMapping("fileUpLoad")
   public @ResponseBody String fileUpLoad(HttpServletRequest request) {
     String result = "";
      try {
      String filePath = ResourceUtils.getFile("classpath:static/upload/").toPath().toString();
      System.out.println("파일 저장 위치 : " + filePath);
      
      List<Part> fileParts = request.getParts()
            .stream()
            .filter(part -> "files".equals(part.getName()) && part.getSize() > 0)
            .collect(Collectors.toList()); //읽은 파일을 리스트화 시킴
      
      //파일이 여러개 일 때
      for(Part filePart : fileParts) {
         // 파일 이름 얻어오기
         String fileName = Paths.get(filePart.getSubmittedFileName())
                           .getFileName().toString();
         
         String fpn = filePath + "//" + fileName;
         
         try(BufferedInputStream fin = new BufferedInputStream(filePart.getInputStream());
         BufferedOutputStream fout = new BufferedOutputStream(new FileOutputStream(fpn)))
         {
            int data;
            while(true) {
               data = fin.read();
               if(data == -1)
                  break;
               fout.write(data);
         }
         
         
         }catch(IOException e) {
            e.printStackTrace();
         }
         System.out.println("Upload fileName : " + fpn);
      }
      result = "success";
   } catch (Exception e) {
      
      e.printStackTrace();
      result = "fail";
   }
      return "ok";
   }
   
   
}