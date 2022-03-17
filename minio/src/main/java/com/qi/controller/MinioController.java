package com.qi.controller;

import com.qi.service.MinioServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author jiaqi.zhang
 * @version 1.0
 * @date 2022/3/14 14:49
 */
@RestController
public class MinioController {
  private static final String BUCKET = "mybucketdemo";

  @Autowired MinioServiceImpl minioService;

  /**
   * 获取桶中文件列表
   *
   * @return
   */
  @GetMapping("/list")
  @SneakyThrows
  public List<String> list() {
    return minioService.list();
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
    String upload = minioService.upload(multipartFiles);
    return upload;
  }

  /**
   * 从桶中下载文件
   *
   * @return
   */
  @PostMapping("/download")
  @SneakyThrows
  public String download(String fileName) {
    // TODO 有问题
    //
    // minioClient.downloadObject(DownloadObjectArgs.builder().bucket(MINIO_BUCKET).filename("/Users/jiaqi.zhang/Desktop/upload/" + fileName).object(fileName).build());
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
    String delete = minioService.delete(fileName);
    return delete;
  }

  /**
   * 指定一个GET请求，返回获取文件对象的URL，此URL过期时间为一天
   *
   * @param fileName
   * @return
   */
  @RequestMapping("/getPreObjectUrl")
  public String getPresignedObjectUrl(String fileName) {
    String presignedObjectUrl = minioService.getPresignedObjectUrl(BUCKET, fileName);
    return presignedObjectUrl;
  }
}
