package com.qi.config;

import com.qi.entriy.MinioProp;
import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author jiaqi.zhang
 * @version 1.0
 * @date 2022/3/14 14:44
 */
@Configuration
public class MinioConfiguration {
  @Resource private MinioProp minioProp;

  @Bean
  public MinioClient minioClient() {
    MinioClient minioClient =
        MinioClient.builder()
            .endpoint(minioProp.getEndpoint())
            .credentials(minioProp.getAccesskey(), minioProp.getSecretKey())
            .build();
    return minioClient;
  }
}
