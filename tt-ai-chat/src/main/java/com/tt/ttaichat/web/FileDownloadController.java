package com.tt.ttaichat.web;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Controller
public class FileDownloadController {

    // 假设文件在用户桌面上，路径为 C:\Users\用户名\Desktop\example.txt
    private static final String DESKTOP_PATH = "C:\\Users\\guo\\Desktop\\郭怡涛-交接.zip";

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile() throws FileNotFoundException {
        // 构建文件路径
        File file = new File(DESKTOP_PATH );

        // 检查文件是否存在
        if (!file.exists()) {
            throw new FileNotFoundException("文件不存在: ");
        }

        // 创建输入流
        FileInputStream fileInputStream = new FileInputStream(file);

        // 设置响应头
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentDispositionFormData("attachment", "郭怡涛交接.zip");

        // 返回响应
        return ResponseEntity.ok()
                             .headers(httpHeaders)
                             .contentLength(file.length())
                             .body(new InputStreamResource(fileInputStream));
    }
}
