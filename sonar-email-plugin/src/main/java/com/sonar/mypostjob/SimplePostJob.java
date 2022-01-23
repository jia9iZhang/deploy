package com.sonar.mypostjob;

import org.sonar.api.batch.postjob.PostJob;
import org.sonar.api.batch.postjob.PostJobContext;
import org.sonar.api.batch.postjob.PostJobDescriptor;
import org.sonar.api.config.Configuration;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

/**
 * @author jiaqi.zhang@shopee.com
 * @version 1.0
 * @date 2022/1/21 15:36
 */
public class SimplePostJob implements PostJob {

    private static final Logger LOGGER = Loggers.get(SimplePostJob.class);

    private static final String ExampleKey = "sonar.my.property";

    private final Configuration configuration;

    public SimplePostJob(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void describe(PostJobDescriptor postJobDescriptor) {

    }

    @Override
    public void execute(PostJobContext postJobContext) {
        LOGGER.info("--------------+postJobContext.config()+---------------------");
        Boolean flag = postJobContext.config().getBoolean(ExampleKey).orElse(false);
        LOGGER.info("--------------+"+ flag+"---------------------");


        LOGGER.info("--------------configuration.getBoolean---------------------");
        Boolean aBoolean = configuration.getBoolean(ExampleKey).orElse(false);
        LOGGER.info("--------------+"+ aBoolean+"---------------------");

    }
}
