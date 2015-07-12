package com.zer0trusion.sqlwhitehat.detection;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Created by thomazc on 7/6/15.
 */
public class FindVulnTargets implements Runnable {
    @Inject
    @Named("sqlmap.directory")
    private String sqlmapDirectory;

    @Inject
    @Named("sqlmap.googledork")
    private String googleDork;

    private Logger log = Logger.getLogger(FindVulnTargets.class);

    public void run() {
        String sqlMapCmd = "python sqlmap.py -g \"" + googleDork + "\" --random-agent --batch --time-sec=15";
        ProcessBuilder builder = new ProcessBuilder(sqlMapCmd.split(" "));
        builder.directory(new File(sqlmapDirectory));
        Process p = null;
        try {
            p = builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
