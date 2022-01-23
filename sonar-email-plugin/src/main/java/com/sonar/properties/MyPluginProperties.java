package com.sonar.properties;

import org.sonar.api.config.PropertyDefinition;

import java.util.List;

import static java.util.Arrays.asList;
import static org.sonar.api.PropertyType.BOOLEAN;

/**
 * 插件可以定义自己的属性，以便可以从 Web 管理控制台进行配置。必须使用扩展点 org.sonar.api.config.PropertyDefinition：
 *
 * @author jiaqi.zhang@shopee.com
 * @version 1.0
 * @date 2022/1/21 16:24
 */
public class MyPluginProperties {

    //Example
    public static final String CATEGORY = "SonarEmail";
    public static final String SETCATEGORY = "SendMail";

    public static final String ExampleKey = "sonar.my.property";
    public static final String ExampleKey1 = "sonar.my.property1";

    public static List<PropertyDefinition> getProperties() {
        return asList(
                PropertyDefinition.builder(ExampleKey)
                        .name("Enable")
                        .description("This is the description displayed in web admin console")
                        .defaultValue(String.valueOf(true))
                        .category(CATEGORY)
                        .subCategory(SETCATEGORY)
                        .type(BOOLEAN)
                        .index(4)
                        .build(),
                PropertyDefinition.builder(ExampleKey1)
                        .name("Enable1")
                        .description("This is the description displayed in web admin console1")
                        .defaultValue(String.valueOf(true))
                        .category(CATEGORY)
                        .subCategory(SETCATEGORY)
                        .type(BOOLEAN)
                        .index(2)
                        .build()
        );
    }
}
