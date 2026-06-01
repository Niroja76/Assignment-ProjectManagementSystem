package com.niroja.project.serviceImpl;

import com.niroja.project.dto.TaskDTO;
import com.niroja.project.entity.Task;
import com.niroja.project.exception.ResourceNotFoundException;
import com.niroja.project.repository.TaskRepository;
import com.niroja.project.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private static final Logger log =
            LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;

    @Override
    public TaskDTO create(TaskDTO dto) {
        log.info("Creating task: {}", dto.getTaskTitle());
        Task saved = taskRepository.save(mapToEntity(dto));
        log.info("Task created with ID: {}", saved.getTaskId());
        return mapToDTO(saved);
    }

    @Override
    public TaskDTO getById(Long id) {
        log.info("Fetching task with ID: {}", id);
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Task not found with ID: " + id));
        return mapToDTO(task);
    }

    @Override
    public List<TaskDTO> getAll() {
        log.info("Fetching all tasks");
        return taskRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO update(Long id, TaskDTO dto) {
        log.info("Updating task with ID: {}", id);
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Task not found with ID: " + id));
        task.setTaskTitle(dto.getTaskTitle());
        task.setDescription(dto.getDescription());
        task.setPriority(dto.getPriority());
        task.setStatus(dto.getStatus());
        task.setDueDate(dto.getDueDate());
        task.setProjectId(dto.getProjectId());
        task.setMemberId(dto.getMemberId());
        Task updated = taskRepository.save(task);
        log.info("Task updated with ID: {}", updated.getTaskId());
        return mapToDTO(updated);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting task with ID: {}", id);
        taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Task not found with ID: " + id));
        taskRepository.deleteById(id);
        log.info("Task deleted with ID: {}", id);
    }

    private TaskDTO mapToDTO(Task task) {
        return TaskDTO.builder()
                .taskId(task.getTaskId())
                .taskTitle(task.getTaskTitle())
                .description(task.getDescription())
                .priority(task.getPriority())
                .status(task.getStatus())
                .dueDate(task.getDueDate())
                .projectId(task.getProjectId())
                .memberId(task.getMemberId())
                .build();
    }

    private Task mapToEntity(TaskDTO dto) {
        return Task.builder()
                .taskTitle(dto.getTaskTitle())
                .description(dto.getDescription())
                .priority(dto.getPriority())
                .status(dto.getStatus())
                .dueDate(dto.getDueDate())
                .projectId(dto.getProjectId())
                .memberId(dto.getMemberId())
                .build();
    }
    
    @Override
public Page<TaskDTO> getAllTasks(int page, int size, String sortBy) {
    log.info("Fetching tasks - page: {}, size: {}, sortBy: {}",
            page, size, sortBy);
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    return taskRepository.findAll(pageable)
            .map(this::mapToDTO);
}
}