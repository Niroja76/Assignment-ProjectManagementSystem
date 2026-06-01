package com.niroja.project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDTO {

    private Long taskId;

    @NotBlank(message = "Task title is required")
    private String taskTitle;

    private String description;

    private String priority;

    private String status;

    private LocalDate dueDate;

    // Foreign Keys
    private Long projectId;

    private Long memberId;
}