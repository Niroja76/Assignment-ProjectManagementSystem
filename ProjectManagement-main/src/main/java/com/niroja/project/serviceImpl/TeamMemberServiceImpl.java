package com.niroja.project.serviceImpl;

import com.niroja.project.dto.TeamMemberDTO;
import com.niroja.project.entity.TeamMember;
import com.niroja.project.exception.ResourceNotFoundException;
import com.niroja.project.repository.TeamMemberRepository;
import com.niroja.project.service.TeamMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamMemberServiceImpl implements TeamMemberService {

    private final TeamMemberRepository repository;

    private TeamMemberDTO mapToDTO(TeamMember m) {
        return TeamMemberDTO.builder()
                .memberId(m.getMemberId())
                .name(m.getName())
                .email(m.getEmail())
                .role(m.getRole())
                .department(m.getDepartment())
                .build();
    }

    private TeamMember mapToEntity(TeamMemberDTO dto) {
        return TeamMember.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .role(dto.getRole())
                .department(dto.getDepartment())
                .build();
    }

    public TeamMemberDTO create(TeamMemberDTO dto) {
        log.info("Creating team member: {}", dto.getName());
        TeamMemberDTO saved = mapToDTO(repository.save(mapToEntity(dto)));
        log.info("Team member created with ID: {}", saved.getMemberId());
        return saved;
    }

    public TeamMemberDTO getById(Long id) {
        log.info("Fetching team member with ID: {}", id);
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Team member not found with ID: " + id));
    }

    public List<TeamMemberDTO> getAll() {
        log.info("Fetching all team members");
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public TeamMemberDTO update(Long id, TeamMemberDTO dto) {
        log.info("Updating team member with ID: {}", id);
        TeamMember existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Team member not found with ID: " + id));
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setRole(dto.getRole());
        existing.setDepartment(dto.getDepartment());
        TeamMemberDTO updated = mapToDTO(repository.save(existing));
        log.info("Team member updated with ID: {}", id);
        return updated;
    }

    public void delete(Long id) {
        log.info("Deleting team member with ID: {}", id);
        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Team member not found with ID: " + id));
        repository.deleteById(id);
        log.info("Team member deleted with ID: {}", id);
    }
}