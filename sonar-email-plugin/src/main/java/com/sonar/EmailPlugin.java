package com.sonar;

/**
 * 插件的生命周期：
 * 插件扩展仅存在于其相关的技术堆栈中。
 * 例如，扫描仪传感器仅在扫描仪运行时进行实例化和执行，而不是在 Web 服务器或 Compute Engine 中。
 * 堆栈由注释@ScannerSide、@ServerSide（用于 Web 服务器）和@ComputeEngineSide 定义。
 */

import com.sonar.mypostjob.SimplePostJob;
import com.sonar.mysensor.MySensor;
import com.sonar.properties.MyPluginProperties;
import org.sonar.api.Plugin;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

/**
 * 入口类
 * 引用官方：插件提供的扩展点（称为“扩展”）的实现必须在其入口点类中声明，该类实现了 org.sonar.api.Plugin 并在 pom.xml 中引用
 *
 * @author jiaqi.zhang@shopee.com
 * @version 1.0
 * @date 2022/1/20 16:47
 */
public class EmailPlugin implements Plugin {

    /**
     * 该类用于将消息记录到扫描仪输出、Web 服务器日志/sonar.log 或 Compute Engine 日志（可从管理 Web 控制台获得）
     */
    private static final Logger LOGGER = Loggers.get(EmailPlugin.class);

    /**
     * @param context
     */
    @Override
    public void define(Context context) {
        LOGGER.info("-------------------注册EmailPlugin插件---------------------");

        //扩展点声明
        context.addExtension(MyPluginProperties.getProperties());
        context.addExtension(MySensor.class);
        context.addExtension(SimplePostJob.class);

    }
}
