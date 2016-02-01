package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
@SpringBootApplication
public class BootExitcodeApplication implements ExitCodeGenerator, CommandLineRunner {

    @Autowired
    private ApplicationContext context;

    public static void main(String[] args) {
        ConfigurableApplicationContext cont = SpringApplication.run(BootExitcodeApplication.class, args);
        ExitCodeGenerator codeGen = cont.getBean(ExitCodeGenerator.class);
        int code = codeGen.getExitCode();
        // this line doesn't interrupt in unit test
        System.exit(code);
    }

    @Override
    public int getExitCode() {
        return 30;
    }

    @Override
    public void run(String... strings) throws Exception {
        // no non 0 exit code returned if the next statement is applied or commented out
        // in case applied abode getExitCode() is called but exit code for the process is still 0
        // SpringApplication.exit(context);
    }
}
