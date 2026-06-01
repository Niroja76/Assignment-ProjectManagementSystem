package com.niroja.project.services;

import com.niroja.project.dto.ProjectDTO;
import com.niroja.project.entity.Project;
import com.niroja.project.exception.ResourceNotFoundException;
import com.niroja.project.repository.ProjectRepository;
import com.niroja.project.serviceImpl.ProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    private Project project;
    private ProjectDTO projectDTO;

    @BeforeEach
    void setUp() {
        project = Project.builder()
                .projectId(1L)
                .projectName("Test Project")
                .description("Test Description")
                .startDate(LocalDate.of(2026, 1, 1))
                .endDate(LocalDate.of(2026, 12, 31))
                .projectStatus("ACTIVE")
                .build();

        projectDTO = ProjectDTO.builder()
                .projectName("Test Project")
                .description("Test Description")
                .startDate(LocalDate.of(2026, 1, 1))
                .endDate(LocalDate.of(2026, 12, 31))
                .projectStatus("ACTIVE")
                .build();
    }

    @Test
    void createProject_Success() {
        when(projectRepository.save(any(Project.class)))
                .thenReturn(project);
        ProjectDTO result = projectService.createProject(projectDTO);
        assertNotNull(result);
        assertEquals("Test Project", result.getProjectName());
        verify(projectRepository, times(1)).save(any(Project.class));
    }

    @Test
    void getProjectById_Success() {
        when(projectRepository.findById(1L))
                .thenReturn(Optional.of(project));
        ProjectDTO result = projectService.getProjectById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getProjectId());
        assertEquals("Test Project", result.getProjectName());
    }

    @Test
    void getProjectById_NotFound() {
        when(projectRepository.findById(99L))
                .thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> projectService.getProjectById(99L));
    }

    @Test
    void getAllProjects_Success() {
        when(projectRepository.findAll())
                .thenReturn(List.of(project));
        List<ProjectDTO> results = projectService.getAllProjects();
        assertNotNull(results);
        assertEquals(1, results.size());
    }

    @Test
    void deleteProject_Success() {
        when(projectRepository.findById(1L))
                .thenReturn(Optional.of(project));
        doNothing().when(projectRepository).delete(project);
        assertDoesNotThrow(() -> projectService.deleteProject(1L));
        verify(projectRepository, times(1)).delete(project);
    }

    @Test
    void deleteProject_NotFound() {
        when(projectRepository.findById(99L))
                .thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> projectService.deleteProject(99L));
    }

    @Test
    void updateProject_Success() {
        when(projectRepository.findById(1L))
                .thenReturn(Optional.of(project));
        when(projectRepository.save(any(Project.class)))
                .thenReturn(project);
        ProjectDTO result = projectService.updateProject(1L, projectDTO);
        assertNotNull(result);
        assertEquals("Test Project", result.getProjectName());
    }
}