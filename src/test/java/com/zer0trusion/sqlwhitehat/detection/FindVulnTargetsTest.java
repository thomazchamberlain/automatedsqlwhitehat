package com.zer0trusion.sqlwhitehat.detection;

import com.carlosbecker.guice.GuiceModules;
import com.carlosbecker.guice.GuiceTestRunner;
import com.zer0trusion.sqlwhitehat.config.PropertiesModule;
import com.zer0trusion.sqlwhitehat.config.SqlMapModule;
import org.junit.runner.RunWith;

/**
 * Created by thomazc on 7/7/15.
 */
@RunWith(GuiceTestRunner.class)
@GuiceModules({PropertiesModule.class, SqlMapModule.class})
public class FindVulnTargetsTest {

}
