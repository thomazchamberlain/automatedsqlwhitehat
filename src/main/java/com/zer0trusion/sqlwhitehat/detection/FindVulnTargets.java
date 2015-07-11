package com.zer0trusion.sqlwhitehat.detection;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by thomazc on 7/6/15.
 */
public class FindVulnTargets implements Runnable {
    @Inject
    @Named("sqlmap.directory")
    private String sqlmapDirectory;

    @Inject
    @Named("sqlmap.output.directory")
    private String sqlmapOutDirectory;

    @Inject
    @Named("sqlmap.googledork")
    private String googleDork;

    private final String sqlMapCmd = "python sqlmap.py -g \"" + googleDork + "\" --batch --time-sec=15";

    public void run() {
        ProcessBuilder builder = new ProcessBuilder(sqlMapCmd.split(" "));
        builder.directory(new File(sqlmapDirectory));
        Process p = null;
        try {
            p = builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//        String line = null;
//
//        try {
//            while((line=br.readLine())!=null){
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
