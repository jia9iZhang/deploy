package com.qi.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author jiaqi.zhang
 * @version 1.0
 * @date 2022/3/16 22:40
 */
public interface MinioService {
  List<String> list();

  String upload(MultipartFile[] multipartFiles);

  String delete(String fileName);

  String getPresignedObjectUrl(String bucketName, String objectName);
}
