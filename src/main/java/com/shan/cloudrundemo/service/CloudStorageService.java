package com.shan.cloudrundemo.service;

import com.google.cloud.storage.Storage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.storage.GoogleStorageResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class CloudStorageService {


    //TODO make this configurable
    @Value("gs://cloudrun-demos/config/dummy.csv")
    private Resource gcsFile;

    @Autowired
    private Storage storage;

    public String readGcsFile() throws IOException {
        return StreamUtils.copyToString(
                gcsFile.getInputStream(),
                Charset.defaultCharset()) + "\n";
    }

    public String writeGcs(String data) throws IOException {
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmssSSS"));
        String gcsFileLocation = "gs://cloudrun-demos/reports/dummy-" + timeStamp;

        Resource resource = new GoogleStorageResource(this.storage, gcsFileLocation);
        try (OutputStream os = ((WritableResource) resource).getOutputStream()) {
            os.write(data.getBytes());
        }
        return " " + gcsFileLocation;
    }
}
