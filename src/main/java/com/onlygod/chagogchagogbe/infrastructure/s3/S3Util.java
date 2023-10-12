package com.onlygod.chagogchagogbe.infrastructure.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.onlygod.chagogchagogbe.domain.file.exception.FileUploadFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RequiredArgsConstructor
@Component
public class S3Util {

    private final AmazonS3 amazonS3;
    private final S3Properties s3Properties;

    public String uploadImage(MultipartFile image) {
        try {
            InputStream inputStream = image.getInputStream();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(image.getContentType());
            objectMetadata.setContentLength(image.getSize());
            
            amazonS3.putObject(
                    new PutObjectRequest(
                            s3Properties.getBucket(),
                            image.getOriginalFilename(),
                            inputStream,
                            objectMetadata
                    )
            );

            return getFileUrl(image.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
            throw FileUploadFailedException.EXCEPTION;
        }
    }

    private String getFileUrl(String fileName) {
        return amazonS3.getUrl(s3Properties.getBucket(), fileName).toString();
    }
}