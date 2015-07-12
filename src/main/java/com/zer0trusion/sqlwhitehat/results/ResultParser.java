package com.zer0trusion.sqlwhitehat.results;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

/**
 * Created by thomazc on 7/11/15.
 */
public class ResultParser implements Runnable {

    private Logger log = Logger.getLogger(ResultParser.class);

    @Inject
    @Named("sqlmap.output.directory")
    private String outputDirectory;

    public void run() {
        Path outputFolder = Paths.get(outputDirectory);

        if(!outputFolder.toFile().exists()) {
            outputFolder.toFile().mkdirs();
        }

        WatchService watchService = null;

        try {
            watchService = FileSystems.getDefault().newWatchService();
            outputFolder.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        String lastDirectory = null;

        while (true) {
            WatchKey key;
            try {
                key = watchService.take();
            } catch (InterruptedException ex) {
                return;
            }

            for (WatchEvent<?> event : key.pollEvents()) {

                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path fileName = ev.context();
                if(!fileName.toString().endsWith(".csv")) {
                    String currentDirectory = outputDirectory + File.separator + fileName;
                    if(lastDirectory == null) {
                        lastDirectory = outputDirectory + File.separator + fileName;
                    }

                    if(!lastDirectory.equals(currentDirectory)) {
                        File targetLog = new File(lastDirectory + File.separator + "log");
                        if(targetLog.length() > 0) {
                            log.info(lastDirectory.substring(lastDirectory.lastIndexOf("/") + 1)
                                    + " is vulnerable to SQL Injections.");
                        }
                    }

                    lastDirectory = currentDirectory;

                }

            }

            if (!key.reset()) {
                break;
            }
        }

    }
}
