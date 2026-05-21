package com.portfolio.api.service;

import com.portfolio.api.dto.CreateProjectRequest;
import com.portfolio.api.exception.ResourceNotFoundException;
import com.portfolio.api.model.Project;
import com.portfolio.api.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    private DefaultProjectService projectService;

    @BeforeEach
    void setUp() {
        projectService = new DefaultProjectService(projectRepository);
    }

    @Test
    void shouldReturnAllProjects() {
        var project = new Project(1L, "Portfolio API", "Spring Boot service", "https://example.com/repo", List.of("Java"));
        when(projectRepository.findAll()).thenReturn(List.of(project));

        var result = projectService.getAllProjects();

        assertEquals(1, result.size());
        assertEquals("Portfolio API", result.getFirst().name());
    }

    @Test
    void shouldThrowWhenProjectDoesNotExist() {
        when(projectRepository.findById(42L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> projectService.getProjectById(42L));
    }

    @Test
    void shouldCreateProject() {
        var request = new CreateProjectRequest(
                "Portfolio API",
                "Spring Boot service",
                "https://example.com/repo",
                List.of("Java", "Spring Boot")
        );

        var saved = new Project(1L, request.name(), request.description(), request.repositoryUrl(), request.skills());
        when(projectRepository.save(new Project(null, request.name(), request.description(), request.repositoryUrl(), request.skills())))
                .thenReturn(saved);

        var result = projectService.createProject(request);

        assertEquals(1L, result.id());
        verify(projectRepository).save(new Project(null, request.name(), request.description(), request.repositoryUrl(), request.skills()));
    }
}
