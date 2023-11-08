package com.example.springbootrestservices.util;

import org.apache.log4j.Logger;

import java.util.Objects;

import static org.junit.Assert.assertTrue;

public abstract class UtilCommon {

    Logger LOG = Logger.getLogger(UtilCommon.class);

    public abstract String getTextIdentifier();

    public void checkTextIsPresent() {
        assertTrue(Objects.nonNull(getTextIdentifier()));
        LOG.info(getTextIdentifier());
    };

}
