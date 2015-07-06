package com.zer0trusion.sqlwhitehat.detection;

import com.google.inject.Inject;
import com.google.inject.name.Named;

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
}
