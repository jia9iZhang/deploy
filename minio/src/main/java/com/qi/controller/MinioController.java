package com.qi.controller;

import com.qi.config.Result;
import com.qi.service.MinioServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * @author jiaqi.zhang
 * @version 1.0
 * @date 2022/3/14 14:49
 */
@RestController
@RequestMapping("/minio")
@Api(tags = "上传文件至Minio API")
public class MinioController {
  private static final String BUCKET = "mybucketdemo";

  @Autowired MinioServiceImpl minioService;

  /**
   * 上传多文件至Minio
   *
   * @param multipartFiles
   * @return
   */
  @PutMapping("/upload")
  @ApiOperation("上传接口")
  @SneakyThrows
  public Result uploadObjects(@RequestParam(name = "file") MultipartFile[] multipartFiles) {
    Result result = minioService.uploadObjects(multipartFiles);
    return result;
  }

  /**
   * 从桶中下载文件
   *
   * @return
   */
  @GetMapping("/download")
  public void download(String fileName, HttpServletResponse response) throws IOException {
    // TODO 有问题
    InputStream in;
    in = minioService.downloadObjects(BUCKET, fileName);
    response.setHeader(
        "Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
    response.setCharacterEncoding("UTF-8");
    IOUtils.copy(in, response.getOutputStream());
    if (in != null) {
      in.close();
    }
  }

  /**
   * 删除单个文件
   *
   * @param fileName
   * @return
   */
  @DeleteMapping("/delete")
  @SneakyThrows
  public Result deleteBucketObject(String fileName) {
    return minioService.deleteBucketObject(fileName);
  }

  /**
   * 指定一个GET请求，返回获取文件对象的URL，此URL过期时间为一天
   *
   * @param fileName
   * @return
   */
  @GetMapping("/getPreObjectUrl")
  public Result getPresignedObjectUrl(String fileName) {
    return minioService.getPresignedObjectUrl(BUCKET, fileName);
  }
}
