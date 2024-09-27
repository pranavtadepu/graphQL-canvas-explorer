package com.example.sjsucanvasgraphqlcli.inputcli;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Component
@NoArgsConstructor
@Data
@Command(subcommands = {ListCourses.class, ListAssignments.class})
public class MyCommand implements Runnable {
    @Option(names = "--token",required = true)
    public String token;

    @Override
    public void run() {

    }
}
