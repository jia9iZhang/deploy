package com.qi.service;

import com.qi.config.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author jiaqi.zhang
 * @version 1.0
 * @date 2022/3/16 22:40
 */
public interface MinioService {
  /**
   * 获取桶对象列表
   *
   * @return
   */
  Result listObjects();

  /**
   * 流式上传对象
   *
   * @param multipartFiles
   * @return
   */
  Result uploadObjects(MultipartFile[] multipartFiles);

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
