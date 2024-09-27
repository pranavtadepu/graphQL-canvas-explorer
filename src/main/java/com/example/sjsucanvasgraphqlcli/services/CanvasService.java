package com.example.sjsucanvasgraphqlcli.services;

import com.example.sjsucanvasgraphqlcli.model.assignments.AllCourses;
import com.example.sjsucanvasgraphqlcli.model.courses.Courses;

import java.util.List;

public interface CanvasService {
    List<Courses> getAllCourses();

    List<AllCourses> getAllCoursesWithAssignments();
}
