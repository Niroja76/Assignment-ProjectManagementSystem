package com.niroja.project.serviceImpl;

import com.niroja.project.dto.ProjectDTO;
import com.niroja.project.entity.Project;
import com.niroja.project.exception.ResourceNotFoundException;
import com.niroja.project.repository.ProjectRepository;
import com.niroja.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private static final Logger log =
            LoggerFactory.getLogger(ProjectServiceImpl.class);

    private final ProjectRepository projectRepository;

    @Override
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        log.info("Creating project: {}", projectDTO.getProjectName());
        Project saved = projectRepository.save(mapToEntity(projectDTO));
        log.info("Project created with ID: {}", saved.getProjectId());
        return mapToDTO(saved);
    }

    @Override
    public ProjectDTO updateProject(Long projectId, ProjectDTO projectDTO) {
        log.info("Updating project with ID: {}", projectId);
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Project not found with ID: " + projectId));
        project.setProjectName(projectDTO.getProjectName());
        project.setDescription(projectDTO.getDescription());
        project.setStartDate(projectDTO.getStartDate());
        project.setEndDate(projectDTO.getEndDate());
        project.setProjectStatus(projectDTO.getProjectStatus());
        Project updated = projectRepository.save(project);
        log.info("Project updated with ID: {}", updated.getProjectId());
        return mapToDTO(updated);
    }

    @Override
    public void deleteProject(Long projectId) {
        log.info("Deleting project with ID: {}", projectId);
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Project not found with ID: " + projectId));
        projectRepository.delete(project);
        log.info("Project deleted with ID: {}", projectId);
    }

    @Override
    public ProjectDTO getProjectById(Long projectId) {
        log.info("Fetching project with ID: {}", projectId);
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Project not found with ID: " + projectId));
        return mapToDTO(project);
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        log.info("Fetching all projects");
        return projectRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProjectDTO> getAllProjects(int page, int size, String sortBy) {
        log.info("Fetching projects - page: {}, size: {}, sortBy: {}",
                page, size, sortBy);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return projectRepository.findAll(pageable)
                .map(this::mapToDTO);
    }

    private ProjectDTO mapToDTO(Project project) {
        return ProjectDTO.builder()
                .projectId(project.getProjectId())
                .projectName(project.getProjectName())
                .description(project.getDescription())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .projectStatus(project.getProjectStatus())
                .build();
    }

    private Project mapToEntity(ProjectDTO projectDTO) {
        return Project.builder()
                .projectName(projectDTO.getProjectName())
                .description(projectDTO.getDescription())
                .startDate(projectDTO.getStartDate())
                .endDate(projectDTO.getEndDate())
                .projectStatus(projectDTO.getProjectStatus())
                .build();
    }
}