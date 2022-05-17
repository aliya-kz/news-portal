package org.zhumagulova.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StaticLogClass {
    private static final Logger logger = LoggerFactory.getLogger(StaticLogClass.class);

    public StaticLogClass() {
    }

    public Logger getLogger() {
        return logger;
    }
}

