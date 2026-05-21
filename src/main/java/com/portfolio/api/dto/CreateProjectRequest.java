package com.portfolio.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record CreateProjectRequest(
        @NotBlank(message = "Project name is required")
        String name,
        @NotBlank(message = "Project description is required")
        String description,
        @Pattern(regexp = "^https?://.*", message = "Repository URL must start with http:// or https://")
        String repositoryUrl,
        @NotEmpty(message = "At least one skill is required")
        List<@NotBlank(message = "Skill cannot be blank") String> skills
) {
}
