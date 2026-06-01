package com.niroja.project.controller;

import com.niroja.project.dto.TaskDTO;
import com.niroja.project.response.ApiResponse;
import com.niroja.project.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tasks", description = "Task Management APIs")
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @Operation(summary = "Create a new task")
    @PostMapping
    public ResponseEntity<ApiResponse<TaskDTO>> create(
            @Valid @RequestBody TaskDTO dto) {
        TaskDTO saved = service.create(dto);
        return new ResponseEntity<>(
                ApiResponse.success("Task created successfully", saved),
                HttpStatus.CREATED);
    }

    @Operation(summary = "Get task by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskDTO>> getById(
            @PathVariable Long id) {
        TaskDTO task = service.getById(id);
        return ResponseEntity.ok(
                ApiResponse.success("Task fetched successfully", task));
    }

    @Operation(summary = "3. Get all tasks with pagination")
    @GetMapping
    public ResponseEntity<ApiResponse<List<TaskDTO>>> getAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "taskId") String sortBy) {
            Page<TaskDTO> tasks = service.getAllTasks(page, size, sortBy);
            return ResponseEntity.ok(
                ApiResponse.success(
                    "Tasks fetched successfully. " +
                    "Page " + (page + 1) + " of " + tasks.getTotalPages() +
                    " | Total records: " + tasks.getTotalElements(),
                    tasks.getContent()
                ));
            }

    @Operation(summary = "Update task by ID")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskDTO>> update(
            @PathVariable Long id,
            @Valid @RequestBody TaskDTO dto) {
        TaskDTO updated = service.update(id, dto);
        return ResponseEntity.ok(
                ApiResponse.success("Task updated successfully", updated));
    }

    @Operation(summary = "Delete task by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(
                ApiResponse.success("Task deleted successfully", null));
    }
}