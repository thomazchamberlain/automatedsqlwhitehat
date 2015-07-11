package com.zer0trusion.sqlwhitehat;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.zer0trusion.sqlwhitehat.config.PropertiesModule;
import com.zer0trusion.sqlwhitehat.config.SqlMapModule;
import com.zer0trusion.sqlwhitehat.detection.FindVulnTargets;
import com.zer0trusion.sqlwhitehat.results.ResultParser;

/**
 * Created by thomazc on 7/6/15.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Injector injector = Guice.createInjector(new PropertiesModule(),
                new SqlMapModule());

        Thread findVulnTargets = new Thread(injector.getInstance(FindVulnTargets.class));
        Thread targetParser = new Thread(injector.getInstance(ResultParser.class));

        findVulnTargets.start();
        targetParser.start();

        findVulnTargets.join();
        targetParser.join();
    }
}
