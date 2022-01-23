package com.sonar.mysensor;

import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

/**
 * 扫描仪传感器仅在扫描仪运行时进行实例化和执行
 * Sensor is a scanner extension point
 *
 * @author jiaqi.zhang@shopee.com
 * @version 1.0
 * @date 2022/1/20 17:57
 */
public class MySensor implements Sensor {
    private static final Logger LOGGER = Loggers.get(MySensor.class);

    @Override
    public void describe(SensorDescriptor sensorDescriptor) {
    }

    /**
     * 在执行扫描期间运行
     *
     * @param context
     */
    @Override
    public void execute(SensorContext context) {
        LOGGER.info("---------------------输出位置为扫描日志终端-----------------------");
        LOGGER.info("---------------------Hello World！！！！！-----------------------");
    }
}
