package com.own.authority.crm.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Created by admin on 14-11-3.
 */
@RestController
public class FileController {

    @Value("${file.temp_location}")
    String TEMP_LOCATION;

    @Value("${file.location}")
    String LOCATION;


    @RequestMapping(value = "/api/file",method = RequestMethod.POST)
    ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        String type = file.getOriginalFilename().split("\\.(?=[^\\.]+$)")[1];
        String fileName = String.valueOf(UUID.randomUUID())+"." +type;
        Files.copy(file.getInputStream(), Paths.get(TEMP_LOCATION, fileName));
        return new ResponseEntity(fileName, HttpStatus.OK);
    }

}
