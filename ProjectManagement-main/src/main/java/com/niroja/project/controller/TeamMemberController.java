package com.niroja.project.controller;

import com.niroja.project.dto.TeamMemberDTO;
import com.niroja.project.response.ApiResponse;
import com.niroja.project.service.TeamMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Team Members", description = "Team Member Management APIs")
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class TeamMemberController {

    private final TeamMemberService service;

    @Operation(summary = "Add a new team member")
    @PostMapping
    public ResponseEntity<ApiResponse<TeamMemberDTO>> create(
            @Valid @RequestBody TeamMemberDTO dto) {
        TeamMemberDTO saved = service.create(dto);
        return new ResponseEntity<>(
                ApiResponse.success("Team member created successfully", saved),
                HttpStatus.CREATED);
    }

    @Operation(summary = "Get team member by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TeamMemberDTO>> getById(
            @PathVariable Long id) {
        TeamMemberDTO member = service.getById(id);
        return ResponseEntity.ok(
                ApiResponse.success("Team member fetched successfully", member));
    }

    @Operation(summary = "Get all team members")
    @GetMapping
    public ResponseEntity<ApiResponse<List<TeamMemberDTO>>> getAll() {
        List<TeamMemberDTO> members = service.getAll();
        return ResponseEntity.ok(
                ApiResponse.success("Team members fetched successfully", members));
    }

    @Operation(summary = "Update team member by ID")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TeamMemberDTO>> update(
            @PathVariable Long id,
            @Valid @RequestBody TeamMemberDTO dto) {
        TeamMemberDTO updated = service.update(id, dto);
        return ResponseEntity.ok(
                ApiResponse.success("Team member updated successfully", updated));
    }

    @Operation(summary = "Delete team member by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(
                ApiResponse.success("Team member deleted successfully", null));
    }
}