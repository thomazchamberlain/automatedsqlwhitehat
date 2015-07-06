package com.zer0trusion.sqlwhitehat.config;

import com.google.inject.AbstractModule;
import com.zer0trusion.sqlwhitehat.detection.FindVulnTargets;

/**
 * Created by thomazc on 7/6/15.
 */
public class SqlMapModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(FindVulnTargets.class).asEagerSingleton();
    }

}
