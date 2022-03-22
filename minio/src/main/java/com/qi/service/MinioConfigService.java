package com.qi.service;

import com.qi.config.Result;

/**
 * @author jiaqi.zhang
 * @version 1.0
 * @date 2022/3/21 11:28
 */
public interface MinioConfigService {
  /**
   * 获取桶对象列表
   *
   * @return
   */
  Result listObjects(String bucketName);
}
