package com.niroja.project.service;

import com.niroja.project.dto.TeamMemberDTO;

import java.util.List;

public interface TeamMemberService {

    TeamMemberDTO create(TeamMemberDTO dto);

    TeamMemberDTO getById(Long id);

    List<TeamMemberDTO> getAll();

    TeamMemberDTO update(Long id, TeamMemberDTO dto);

    void delete(Long id);
}