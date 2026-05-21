package com.portfolio.api.service;

import com.portfolio.api.dto.CreateProjectRequest;
import com.portfolio.api.model.Project;

import java.util.List;

public interface ProjectService {
    List<Project> getAllProjects();

    Project getProjectById(Long id);

    Project createProject(CreateProjectRequest request);
}
