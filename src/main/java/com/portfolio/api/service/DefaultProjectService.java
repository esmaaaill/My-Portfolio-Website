package com.portfolio.api.service;

import com.portfolio.api.dto.CreateProjectRequest;
import com.portfolio.api.exception.ResourceNotFoundException;
import com.portfolio.api.model.Project;
import com.portfolio.api.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultProjectService implements ProjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultProjectService.class);

    private final ProjectRepository projectRepository;

    public DefaultProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> getAllProjects() {
        LOGGER.info("Retrieving all projects");
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(Long id) {
        LOGGER.info("Retrieving project with id={}", id);
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found: " + id));
    }

    @Override
    public Project createProject(CreateProjectRequest request) {
        LOGGER.info("Creating project '{}'.", request.name());
        var projectToSave = new Project(null, request.name(), request.description(), request.repositoryUrl(), request.skills());
        return projectRepository.save(projectToSave);
    }
}
