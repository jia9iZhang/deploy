package com.qi.controller;

import com.qi.util.MinioUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
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
    MinioUtil minioUtil;

    /**
     * 获取桶中文件列表
     *
     * @return
     */
    @GetMapping("/list")
    @SneakyThrows
    public List<String> list() {
        return minioUtil.bucketObjectNameLists(MINIO_BUCKET);
    }

    /**
     * 上传多文件至Minio
     *
     * @param multipartFiles
     * @return
     */
    @PostMapping("/upload")
    @SneakyThrows
    public String upload(@RequestParam(name = "file") MultipartFile[] multipartFiles) {

        if (multipartFiles == null || multipartFiles.length == 0) {
            return "上传文件不能为空";
        }
        for (MultipartFile file : multipartFiles) {
            //遍历文件数组
            String originalFilename = file.getOriginalFilename();
            try (InputStream in = file.getInputStream()) {
                minioUtil.uploadFileToBucket(MINIO_BUCKET, originalFilename, in);
            }
        }
        return "上传成功";
    }

    /**
     * 从桶中下载文件
     *
     * @return
     */
    @PostMapping("/download")
    @SneakyThrows
    public String download(String fileName) {
        //TODO 有问题
//        minioClient.downloadObject(DownloadObjectArgs.builder().bucket(MINIO_BUCKET).filename("/Users/jiaqi.zhang/Desktop/upload/" + fileName).object(fileName).build());
//        return "下载成功";
        return null;
    }

    /**
     * 删除单个文件
     *
     * @param fileName
     * @return
     */
    @RequestMapping("/delete")
    @SneakyThrows
    public String delete(String fileName) {
        minioUtil.deleteObject(MINIO_BUCKET, fileName);
        return "删除成功";
    }
}
