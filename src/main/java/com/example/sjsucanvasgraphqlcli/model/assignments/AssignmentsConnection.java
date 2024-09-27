package com.example.sjsucanvasgraphqlcli.model.assignments;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AssignmentsConnection {
    List<Nodes> nodes;
}
