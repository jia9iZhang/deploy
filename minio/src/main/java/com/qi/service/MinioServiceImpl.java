package com.qi.service;

import com.qi.util.MinioUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @author jiaqi.zhang
 * @version 1.0
 * @date 2022/3/16 22:40
 */
@Service
@Slf4j
public class MinioServiceImpl implements MinioService {
  private static final String BUCKET = "mybucketdemo";

  @Autowired MinioUtil minioUtil;

  @Override
  public List<String> list() {
    return minioUtil.bucketObjectNameLists(BUCKET);
  }

  @Override
  @SneakyThrows
  public String upload(MultipartFile[] multipartFiles) {
    for (MultipartFile file : multipartFiles) {
      // 遍历文件数组
      String originalFilename = file.getOriginalFilename();
      try (InputStream in = file.getInputStream()) {
        minioUtil.uploadFileToBucket(BUCKET, originalFilename, in);
      }
    }
    return "上传成功";
  }

  @Override
  public String delete(String fileName) {
    minioUtil.deleteObject(BUCKET, fileName);
    return "删除成功";
  }

  @Override
  public String getPresignedObjectUrl(String bucketName, String objectName) {
    String presignedObjectUrl = minioUtil.getPresignedObjectUrl(bucketName, objectName);
    return presignedObjectUrl;
  }
}
