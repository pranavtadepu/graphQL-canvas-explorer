package com.example.sjsucanvasgraphqlcli.services;


import com.example.sjsucanvasgraphqlcli.inputcli.MyCommand;
import com.example.sjsucanvasgraphqlcli.model.assignments.AllCourses;
import com.example.sjsucanvasgraphqlcli.model.courses.Courses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class CanvasCoursesAndAssignmentsService implements CanvasService{
    private final WebClient webClient;
    private final MyCommand myCommand;
    private static final String coursesQuery = "query MyQuery { allCourses {term { name}    name  } }";
    private static final String assignmentsQuery = "query MyQuery { allCourses { name assignmentsConnection {nodes {dueAt name}}}}";
    private static final String bearerToken = "Bearer ";
    public CanvasCoursesAndAssignmentsService(WebClient webClient, MyCommand myCommand){
        this.webClient = webClient;
        this.myCommand = myCommand;
    }
/*
To retrieve all the courses with name and term from server
Used from Official Documentation: https://docs.spring.io/spring-graphql/reference/client.html#client.httpgraphqlclient
 */
    public List<Courses> getAllCourses() {
        List<Courses> courseList = null;
        try {
            String token = bearerToken + myCommand.token;
            HttpGraphQlClient httpGraphQlClient = HttpGraphQlClient.builder(webClient).headers(httpHeaders -> httpHeaders.add("Authorization", token)).build();
            Mono<List<Courses>> allCourses = httpGraphQlClient.document(coursesQuery).retrieve("allCourses").toEntityList(Courses.class);
            courseList = allCourses.block();
            return courseList;
        }
        catch (Exception exception){
            log.error(exception.getMessage());
        }
        return courseList;
    }
/*
To retrieve all the courses with name and assignments with due date from server
 */
    public List<AllCourses> getAllCoursesWithAssignments(){
        List<AllCourses> coursesList = null;
        try {
            String token = bearerToken + myCommand.token;
            HttpGraphQlClient httpGraphQlClient = HttpGraphQlClient.builder(webClient).headers(httpHeaders -> httpHeaders.add("Authorization", token)).build();
            Mono<List<AllCourses>> allCourses = httpGraphQlClient.document(assignmentsQuery).retrieve("allCourses").toEntityList(AllCourses.class);
            return allCourses.block();
        }catch (Exception exception){
            log.error(exception.getMessage());
        }
        return coursesList;
    }
}
