package com.example.sjsucanvasgraphqlcli.inputcli;

import com.example.sjsucanvasgraphqlcli.model.courses.Courses;
import com.example.sjsucanvasgraphqlcli.services.CanvasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.List;
import java.util.Objects;


@Component
@Command(name="list-courses")
@Slf4j
public class ListCourses implements Runnable {

    @Option(names = "--no-active",negatable = true,defaultValue = "true")
    private boolean courseStatus;
    private final CanvasService canvasService;

    public ListCourses(CanvasService canvasService){
        this.canvasService = canvasService;
    }

    @Override
    public void run() {
        try{
       List<Courses> allCourses = canvasService.getAllCourses();
        if(courseStatus){
            for (Courses course: allCourses) {
                if(Objects.equals(course.getTerm().getName(),"Spring 2024")){
                    System.out.println(course.getName());
                }
            }

        }else {
          for (Courses course: allCourses){
              if(!Objects.equals(course.getTerm().getName(),"Spring 2024"))
              {
                  System.out.println(course.getName());
              }
          }
       }
    }catch (Exception exception){
            log.error(exception.getMessage());
        }
    }
}

