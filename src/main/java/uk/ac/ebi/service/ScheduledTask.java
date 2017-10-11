package uk.ac.ebi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * Created by chojnasm on 15/05/2017.
 * This task refreshes access to temparary directory to avoid problems with
 * the directory being deleted due to long inactivity
 */
@Component
public class ScheduledTask {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    @Scheduled(fixedDelay=60 * 60 * 1000)
    public void updateTempDirs() {
        log.debug("Update log dirs");
        try {
            Path startingDir = Paths.get("/tmp");
            Files.list(startingDir).filter(
                    p -> p.toString().contains("tomcat")
            ).forEach(
                    p -> p.toFile().setLastModified(System.currentTimeMillis())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        ScheduledTask scheduledTask = new ScheduledTask();
        scheduledTask.updateTempDirs();
    }

}