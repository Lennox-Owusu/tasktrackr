package com.amalitech.tasktrackr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private String id;
    private String title;
    private String description;
    private String status = "OPEN";  // default status
}