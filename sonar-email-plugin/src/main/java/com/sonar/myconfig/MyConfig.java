package com.sonar.myconfig;

import com.sonar.properties.MyPluginProperties;
import org.sonar.api.config.Configuration;


/**
 * @author jiaqi.zhang@shopee.com
 * @version 1.0
 * @date 2022/1/23 15:21
 */
public class MyConfig {
    private final Configuration configuration;

    public MyConfig(Configuration configuration) {
        this.configuration = configuration;
    }

    public Boolean getBooleanKey() {
         return configuration.getBoolean(MyPluginProperties.ExampleKey).orElse(null);
    }
}
