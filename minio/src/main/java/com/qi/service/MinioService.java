package com.qi.service;

import com.qi.config.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author jiaqi.zhang
 * @version 1.0
 * @date 2022/3/16 22:40
 */
public interface MinioService {

  /**
   * 流式上传对象
   *
   * @param multipartFiles
   * @return
   */
  Result uploadObjects(MultipartFile[] multipartFiles);

  InputStream downloadObjects(String bucketName, String objectName);

  /**
   * 删除桶中对象
   *
   * @param fileName
   * @return
   */
  Result deleteBucketObject(String fileName);

  /**
   * 获取文件对象的UR
   *
   * @param bucketName
   * @param objectName
   * @return
   */
  Result getPresignedObjectUrl(String bucketName, String objectName);
}
