package com.qi.controller;

import com.qi.config.Result;
import com.qi.service.MinioConfigServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiaqi.zhang
 * @version 1.0
 * @date 2022/3/21 11:26
 */
@RestController
@RequestMapping("/minioConfig")
public class MinioConfigController {
  private static final String BUCKET = "mybucketdemo";

  @Autowired MinioConfigServiceImpl minioConfigService;

  /**
   * 获取桶中文件列表
   *
   * @return
   */
  @GetMapping("/list")
  @SneakyThrows
  public Result listObjects(String bucketName) {
    return minioConfigService.listObjects(BUCKET);
  }
}
