package com.qi.entriy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiaqi.zhang
 * @version 1.0
 * @date 2022/3/14 15:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
public class Result<T> {
  //    private static final long serialVersionUID = 1L;
  private Integer code;
  private Object data = "";
  private String message = "";
}
