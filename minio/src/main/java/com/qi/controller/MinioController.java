package com.qi.controller;

import com.qi.entriy.Result;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author jiaqi.zhang
 * @version 1.0
 * @date 2022/3/14 14:49
 */
@RestController
public class MinioController {
    private static final String MINIO_BUCKET = "mybucketdemo";
    @Autowired
    public MinioClient minioClient;
    @Autowired
    Result result;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/list")
    public List<Object> list() {
        return null;
    }

    @PostMapping("/upload")
    public Result upload(@RequestParam(name = "file") MultipartFile[] multipartFiles) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        if (multipartFiles == null || multipartFiles.length == 0) {
            result.setCode(500);
            result.setMessage("上传文件不能为空");
            return result;
        }

        boolean found =
                minioClient.bucketExists(BucketExistsArgs.builder().bucket(MINIO_BUCKET).build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(MINIO_BUCKET).build());
        }

        for (MultipartFile file : multipartFiles) {
            //
            String originalFilename = file.getOriginalFilename();

            minioClient.uploadObject(UploadObjectArgs.builder()
                    .bucket(MINIO_BUCKET)
                    .object(originalFilename)
                    .filename("/Users/jiaqi.zhang/Downloads/dahai.jpeg")
                    .build());
        }
        result.setCode(200);
        result.setMessage("上传成功");
        return result;
    }

    @PostMapping("/download")
    public List<Object> download() {
        return null;
    }
}
