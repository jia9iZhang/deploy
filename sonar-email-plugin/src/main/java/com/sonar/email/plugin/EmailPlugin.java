package com.sonar.email.plugin;

import org.sonar.api.Plugin;
import org.sonar.api.config.PropertyDefinition;

/**
 * @author jiaqi.zhang@shopee.com
 * @version 1.0
 * @date 2022/1/20 16:47
 */
public class EmailPlugin implements Plugin {
    @Override
    public void define(Context context) {
        context.addExtension(
                PropertyDefinition.builder("sonar.my.property")
                        .name("My Property")
                        .description("This is the description displayed in web admin console")
                        .defaultValue("42")
                        .build()
        );
    }
}
