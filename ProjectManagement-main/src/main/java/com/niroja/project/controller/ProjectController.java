package com.niroja.project.controller;

import com.niroja.project.dto.ProjectDTO;
import com.niroja.project.response.ApiResponse;
import com.niroja.project.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Projects", description = "Project Management APIs")
@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @Operation(summary = "Create a new project")
    @PostMapping
    public ResponseEntity<ApiResponse<ProjectDTO>> createProject(
            @Valid @RequestBody ProjectDTO projectDTO) {
        ProjectDTO saved = projectService.createProject(projectDTO);
        return new ResponseEntity<>(
                ApiResponse.success("Project created successfully", saved),
                HttpStatus.CREATED);
    }

    @Operation(summary = "Get project by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectDTO>> getProjectById(
            @PathVariable("id") Long projectId) {
        ProjectDTO projectDTO = projectService.getProjectById(projectId);
        return ResponseEntity.ok(
                ApiResponse.success("Project fetched successfully", projectDTO));
    }


    @Operation(summary = "3. Get all projects with pagination")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProjectDTO>>> getAllProjects(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "projectId") String sortBy) {
                Page<ProjectDTO> projects =
                projectService.getAllProjects(page, size, sortBy);
                return ResponseEntity.ok(
                        ApiResponse.success(
                                "Projects fetched successfully. " +
                                "Page " + (page + 1) + " of " + projects.getTotalPages() +
                                " | Total records: " + projects.getTotalElements(),
                                projects.getContent()
                        ));
                }

                
    @Operation(summary = "Update project by ID")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectDTO>> updateProject(
            @PathVariable("id") Long projectId,
            @Valid @RequestBody ProjectDTO projectDTO) {
        ProjectDTO updated =
                projectService.updateProject(projectId, projectDTO);
        return ResponseEntity.ok(
                ApiResponse.success("Project updated successfully", updated));
    }

    @Operation(summary = "Delete project by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProject(
            @PathVariable("id") Long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.ok(
                ApiResponse.success("Project deleted successfully", null));
    }
}