package com.sonar.email.plugin;

import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;

/**
 * @author jiaqi.zhang@shopee.com
 * @version 1.0
 * @date 2022/1/20 17:57
 */
public class MySensor implements Sensor {
    @Override
    public void describe(SensorDescriptor sensorDescriptor) {

    }

    @Override
    public void execute(SensorContext context) {
        int value = context.config().getInt("sonar.property").orElse(0);
    }
}
