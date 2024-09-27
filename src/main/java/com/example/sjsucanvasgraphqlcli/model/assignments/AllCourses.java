package com.example.sjsucanvasgraphqlcli.model.assignments;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AllCourses {
    String name;
    AssignmentsConnection assignmentsConnection;
}
