package com.portfolio.api.repository;

import com.portfolio.api.model.Project;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryProjectRepository implements ProjectRepository {

    private final List<Project> projects = new ArrayList<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public List<Project> findAll() {
        return List.copyOf(projects);
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projects.stream()
                .filter(project -> project.id().equals(id))
                .findFirst();
    }

    @Override
    public Project save(Project project) {
        var persisted = new Project(
                sequence.getAndIncrement(),
                project.name(),
                project.description(),
                project.repositoryUrl(),
                List.copyOf(project.skills())
        );
        projects.add(persisted);
        return persisted;
    }
}
