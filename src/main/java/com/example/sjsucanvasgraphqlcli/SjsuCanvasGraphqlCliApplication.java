package com.example.sjsucanvasgraphqlcli;

import com.example.sjsucanvasgraphqlcli.inputcli.MyCommand;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;
import picocli.CommandLine.IFactory;

@SpringBootApplication
public class SjsuCanvasGraphqlCliApplication implements CommandLineRunner, ExitCodeGenerator {

    private final IFactory factory;
    private final MyCommand myCommand;
    private int exitCode;

    public SjsuCanvasGraphqlCliApplication (IFactory factory, MyCommand myCommand){
        this.factory = factory;
        this.myCommand = myCommand;
    }
    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(SjsuCanvasGraphqlCliApplication.class, args)));
        SpringApplication.run(SjsuCanvasGraphqlCliApplication.class, args);
    }
    /*
    Used from offical documentation: https://picocli.info/#_spring_boot_example
     */
    @Override
    public void run(String... args) throws Exception {
        exitCode = new CommandLine(myCommand,factory).execute(args);
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }
}
