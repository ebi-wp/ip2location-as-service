package uk.ac.ebi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@EnableCaching
@EnableScheduling
@SpringBootApplication
public class Application {

	public static String ROOT = "tmp-dir";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    // we might need to upload large files, currently not used
    @Bean
    CommandLineRunner init() {
        return (String[] args) -> {
            Path path = Paths.get(ROOT);
            Files.createDirectories(path);
            // We might add deleting files here
        };
    }
}
