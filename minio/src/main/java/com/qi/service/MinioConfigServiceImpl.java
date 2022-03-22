package com.qi.service;

import com.qi.config.Result;
import com.qi.util.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jiaqi.zhang
 * @version 1.0
 * @date 2022/3/21 11:28
 */
@Service
public class MinioConfigServiceImpl implements MinioConfigService {

  @Autowired MinioUtil minioUtil;

  @Override
  public Result listObjects(String bucketName) {
    List<String> lists = minioUtil.bucketObjectNameLists(bucketName);
    return new Result(200, lists);
  }
}
