package com.niroja.project.service;

import com.niroja.project.dto.ProjectDTO;
import org.springframework.data.domain.Page;
import java.util.List;

public interface ProjectService {

    ProjectDTO createProject(ProjectDTO projectDTO);
    ProjectDTO updateProject(Long projectId, ProjectDTO projectDTO);
    void deleteProject(Long projectId);
    ProjectDTO getProjectById(Long projectId);
    List<ProjectDTO> getAllProjects();
    Page<ProjectDTO> getAllProjects(int page, int size, String sortBy);
}