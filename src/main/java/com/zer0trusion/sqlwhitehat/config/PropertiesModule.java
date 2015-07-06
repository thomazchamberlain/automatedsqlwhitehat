package com.zer0trusion.sqlwhitehat.config;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by thomazc on 7/6/15.
 */
public class PropertiesModule extends AbstractModule {

    @Override
    protected void configure() {
        Properties properties = new Properties();
        File propertiesFile = new File(getClass().getClassLoader().getResource("properties").getFile());
        try {
            properties.load(new FileReader(propertiesFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Names.bindProperties(binder(), properties);
    }
}

