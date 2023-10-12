package com.onlygod.chagogchagogbe.domain.file.service;

import com.onlygod.chagogchagogbe.domain.file.presentation.dto.response.FileUploadResponse;
import com.onlygod.chagogchagogbe.infrastructure.s3.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class FileUploadService {

    private final S3Util s3Util;

    public FileUploadResponse execute(MultipartFile image) {
        return new FileUploadResponse(s3Util.uploadImage(image));
    }
}
