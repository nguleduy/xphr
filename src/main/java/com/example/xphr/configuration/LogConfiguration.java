package com.example.xphr.configuration;

import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Log Configuration.
 */
@Component
@NoArgsConstructor
public class LogConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(LogConfiguration.class);

    /**
     * Log Configuration.
     *
     * @param path log file path
     */
    @Autowired
    public LogConfiguration(
            @Value("${log4j.configurationFile}") String path) {

        logger.info("Load config log4j2.xml - START");
        if (Objects.isNull(path) || path.trim().isEmpty()) {
            logger.info("Load config log4j2.xml - FAIL (Reason the path is not exist) - END");
            return;
        }
        logger.info("Load config log4j2.xml - END");
    }
}
