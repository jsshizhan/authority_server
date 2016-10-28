package com.own.authority.domain;

import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by xiemeilong on 15-3-28.
 */

public class FileUploadControl {

    @Value("${file.temp_location}")
    String TEMP_LOCATION;

    @Value("${file.location}")
    String LOCATION;


    public boolean submitFile(String filename) {
        if (filename == null) return true;

        try {
            Files.move(Paths.get(TEMP_LOCATION, filename), Paths.get(LOCATION, filename));
            return true;
        } catch (IOException e) {
            return Files.exists(Paths.get(LOCATION, filename));
        }
    }
}
