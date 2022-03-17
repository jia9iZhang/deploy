package com.qi.util;

import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author jiaqi.zhang
 * @version 1.0
 * @date 2022/3/16 19:13
 */
@Component
@Slf4j
public class MinioUtil {

  @Autowired MinioClient minioClient;

  /**
   * 查看桶是否存在
   *
   * @param bucketName
   * @return
   */
  @SneakyThrows
  public boolean bucketExists(String bucketName) {
    boolean bucketExists =
        minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    return bucketExists;
  }

  /**
   * 创建桶
   *
   * @param bucketName
   */
  @SneakyThrows
  public void creatBucket(String bucketName) {
    minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
  }

  /**
   * 列出所有的Buckets
   *
   * @return
   */
  @SneakyThrows
  public List<String> BucketLists() {
    List<Bucket> buckets = minioClient.listBuckets();
    ArrayList<String> bucketLists = new ArrayList<>();
    for (Bucket bucket : buckets) {
      bucketLists.add(String.valueOf(bucket));
    }
    return bucketLists;
  }

  /**
   * 列出Bucket中所有的对象名称
   *
   * @return
   */
  @SneakyThrows
  public List<String> bucketObjectNameLists(String bucketName) {
    boolean flag = bucketExists(bucketName);
    if (flag) {
      Iterable<Result<Item>> objectIterable =
          minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build());
      ArrayList<String> objectNameLists = new ArrayList<>();
      Iterator<Result<Item>> iterator = objectIterable.iterator();
      while (iterator.hasNext()) {
        Item item = iterator.next().get();
        objectNameLists.add(item.objectName());
      }
      return objectNameLists;
    }
    return null;
  }

  /**
   * 通过流上传文件至桶
   *
   * @param bucketName
   * @param objectName
   * @param in
   * @return
   */
  @SneakyThrows
  public boolean uploadFileToBucket(String bucketName, String objectName, InputStream in) {
    if (!bucketExists(bucketName)) {
      creatBucket(bucketName);
    } else {
      minioClient.putObject(
          PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                  in, in.available(), -1)
              .build());
      log.info(objectName + "上传成功");
      return true;
    }
    log.error("上传失败");
    return false;
  }

  /**
   * @param bucketName
   * @param objectName
   * @return
   */
  @SneakyThrows
  public void deleteObject(String bucketName, String objectName) {
    minioClient.removeObject(
        RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
  }

  @SneakyThrows
  public String getPresignedObjectUrl(String bucketName, String objectName) {
    String url =
        minioClient.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .bucket(bucketName)
                .method(Method.GET)
                .object(objectName)
                .expiry(60 * 60 * 24)
                .build());
    return url;
  }
}
