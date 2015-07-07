package com.zer0trusion.sqlwhitehat;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.zer0trusion.sqlwhitehat.config.PropertiesModule;
import com.zer0trusion.sqlwhitehat.config.SqlMapModule;
import com.zer0trusion.sqlwhitehat.detection.FindVulnTargets;

import java.io.IOException;

/**
 * Created by thomazc on 7/6/15.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Injector injector = Guice.createInjector(new PropertiesModule(),
                new SqlMapModule());

        FindVulnTargets findVulnTargets = injector.getInstance(FindVulnTargets.class);
        findVulnTargets.start();
    }
}
