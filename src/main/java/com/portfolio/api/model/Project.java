package com.portfolio.api.model;

import java.util.List;

public record Project(Long id, String name, String description, String repositoryUrl, List<String> skills) {
}
