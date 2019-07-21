package com.shan.cloudrundemo.controller;

import com.shan.cloudrundemo.service.CloudStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
class RTVController {


    @Autowired
    CloudStorageService cloudStorageService;

    @GetMapping("/reports/rtv")
    String generateRTVReport()  {

        String response ;
        try {
            String data = cloudStorageService.readGcsFile();
            response = cloudStorageService.writeGcs(data);
        }catch (Exception e){
            log.error(e.toString());
            response = " no where - Exception occurred";
        }

        return "Hello, report has been written  in  " + response;
    }

}