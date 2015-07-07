package com.zer0trusion.sqlwhitehat.detection;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * Created by thomazc on 7/6/15.
 */
public class FindVulnTargets {
    @Inject
    @Named("sqlmap.directory")
    private String sqlmapDirectory;

    @Inject
    @Named("sqlmap.output.directory")
    private String sqlmapOutDirectory;

    @Inject
    @Named("sqlmap.googledork")
    private String googleDork;

    public void start() throws Exception {
        String command = "python sqlmap.py -g \"" + googleDork + "\" --batch --time-sec=15";
        ProcessBuilder builder = new ProcessBuilder(command.split(" "));
        builder.directory(new File(sqlmapDirectory));
        Process p = builder.start();

        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = null;

        while((line=br.readLine())!=null){
            System.out.println(line);
        }
    }
}
