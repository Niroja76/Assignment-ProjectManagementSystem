package com.niroja.project.service;

import com.niroja.project.dto.TaskDTO;
import org.springframework.data.domain.Page;
import java.util.List;

public interface TaskService {

    TaskDTO create(TaskDTO dto);
    TaskDTO getById(Long id);
    List<TaskDTO> getAll();
    TaskDTO update(Long id, TaskDTO dto);
    void delete(Long id);
    Page<TaskDTO> getAllTasks(int page, int size, String sortBy);
}