package com.example.productservice.service;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.awscore.defaultsmode.DefaultsMode;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Utilities;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;


import java.io.File;
import java.net.URL;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2(topic = "S3Service")
public class S3Service {

    private S3Client s3Client = S3Client.builder().defaultsMode(DefaultsMode.AUTO).build();

    private  String bucketName = "shopeeclone";
    private  String prefix = "product_image/";

    public String uploadFile(MultipartFile multipartFile, String productName){
        try{
            int dotIndex = multipartFile.getOriginalFilename().lastIndexOf(".");
            String postfix = multipartFile.getOriginalFilename().substring(dotIndex);
            String newObjectKeyName = prefix + productName + "_" + Instant.now().toEpochMilli() + postfix;
            Map<String, String> metadata = new HashMap<>();
            metadata.put("x-amz-meta-product-img", "test");
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(newObjectKeyName)
                    .metadata(metadata)
                    .acl(ObjectCannedACL.PUBLIC_READ)
                    .build();

//            convert multipart file to file
            File file = File.createTempFile("temps3image", ".jpg");
            multipartFile.transferTo(file);
            s3Client.putObject(putObjectRequest, RequestBody.fromFile(file));
            S3Utilities utilities = s3Client.utilities();
            GetUrlRequest request = GetUrlRequest.builder().bucket(bucketName).key(newObjectKeyName).build();
            String url = utilities.getUrl(request).toString();
            log.info("finish upload object with url : " + url);
            return url;
        }
        catch (Exception ex){
            log.error(ex.getMessage());
            log.warn(ex.getStackTrace().toString());
            return "failed";
        }
    }
}
