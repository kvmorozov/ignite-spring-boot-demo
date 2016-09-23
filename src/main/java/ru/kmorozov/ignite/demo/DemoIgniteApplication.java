package ru.kmorozov.ignite.demo;

import org.apache.ignite.Ignite;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.kmorozov.ignite.spring.IgniteApplicationContext;
import ru.kmorozov.ignite.spring.annotations.IgniteResource;

/**
 * Created by sbt-morozov-kv on 22.09.2016.
 */

@SpringBootApplication
public class DemoIgniteApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DemoIgniteApplication.class);
        app.setApplicationContextClass(IgniteApplicationContext.class);
        app.run(args);
    }

    @Bean
    public CommandLineRunner runIgnite() {
        return new CommandLineRunner() {
            @IgniteResource(gridName = "test", clientMode = true)
            private Ignite igniteClient;

            public void run(String... args) throws Exception {
                igniteClient.compute().broadcast(() -> System.out.println("Hello World!"));
                igniteClient.close();
            }
        };
    }
}
