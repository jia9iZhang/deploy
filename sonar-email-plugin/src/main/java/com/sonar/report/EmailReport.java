package com.sonar.report;

import com.sonar.myconfig.MyConfig;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

/**
 * @author jiaqi.zhang@shopee.com
 * @version 1.0
 * @date 2022/1/23 15:02
 */
public class EmailReport{
    private static final Logger LOGGER = Loggers.get(EmailReport.class);

    private final MyConfig myConfig;

    private EmailReport(MyConfig myConfig) {
        this.myConfig = myConfig;
    }

    private void testGetApi(){
        Boolean booleanKey = myConfig.getBooleanKey();
        if (booleanKey){
            LOGGER.info("-------------------------EmailReport.testGetApi.true------------------------------------");
        }else {
            LOGGER.info("-------------------------EmailReport.testGetApi.false------------------------------------");
        }
    }
}
