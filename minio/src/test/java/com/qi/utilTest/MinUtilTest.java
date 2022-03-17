package com.qi.utilTest;

import com.qi.util.MinioUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author jiaqi.zhang
 * @version 1.0
 * @date 2022/3/17 12:42
 */
@SpringBootTest
public class MinUtilTest {
  private static final String BUCKET = "mybucketdemo";
  @Autowired MinioUtil minioUtil;

  @Test
  public void getPresignedObjectUrlTest() {
    String presignedObjectUrl = minioUtil.getPresignedObjectUrl(BUCKET, "JVM.png");
    System.out.println(presignedObjectUrl);
  }
}
