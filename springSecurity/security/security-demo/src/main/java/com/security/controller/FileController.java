package com.security.controller;

import com.security.dto.FIleInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by  邱伟
 * 2018/5/16 19:58
 */

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping
    public FIleInfo upload(MultipartFile file) {
        return null;
    }

}