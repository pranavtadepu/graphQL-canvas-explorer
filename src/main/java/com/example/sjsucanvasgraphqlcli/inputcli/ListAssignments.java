package com.example.sjsucanvasgraphqlcli.inputcli;

import com.example.sjsucanvasgraphqlcli.model.assignments.AllCourses;
import com.example.sjsucanvasgraphqlcli.services.CanvasService;
import com.example.sjsucanvasgraphqlcli.model.assignments.Nodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
@Command(name = "list-assignments")
public class ListAssignments implements Runnable {

    @Option(names = "--no-active", negatable = true, defaultValue = "true")
    private boolean assignmentStatus; //true for active assignments
    @Parameters(index = "0")
    String courseName;
    private final CanvasService canvasService;

    public ListAssignments(CanvasService canvasService) {
        this.canvasService = canvasService;
    }


    @Override
    public void run() {
        List<AllCourses> courses = canvasService.getAllCoursesWithAssignments();
        int count = 0;
        try {
            List<String> s = new ArrayList<>();
            for (AllCourses course : courses) {
                if (course.getName().contains(courseName)) {
                    count++;
                    s.add(course.getName());
                }
            }
            if (assignmentStatus && count == 1) {
                for (AllCourses courses1 : courses) {
                    if (courses1.getName().equals(s.get(0))) {
                        List<Nodes> nodes = courses1.getAssignmentsConnection().getNodes();
                        for (Nodes node : nodes) {
                            OffsetDateTime now = OffsetDateTime.now();
                            String dueAt = node.getDueAt();
                            if (Objects.nonNull(dueAt)) {
                                OffsetDateTime obtainedDataTime = OffsetDateTime.parse(dueAt, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                                if (obtainedDataTime.isAfter(now)) {
                                    String format = String.format("%s due at %s", node.getName(), node.getDueAt());
                                    System.out.println(format);
                                }
                            }
                        }
                    }
                }
            } else if (!assignmentStatus && count == 1) {
                for (AllCourses courses1 : courses) {
                    if (courses1.getName().equals(s.get(0))) {
                        List<Nodes> nodes = courses1.getAssignmentsConnection().getNodes();
                        for (Nodes node : nodes) {
                            String format = String.format("%s due at %s", node.getName(), node.getDueAt());
                            System.out.println(format);
                        }
                    }
                }
            } else {
                System.out.println("Matches are not Unique");
                s.forEach(System.out::println);
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
    }
}

