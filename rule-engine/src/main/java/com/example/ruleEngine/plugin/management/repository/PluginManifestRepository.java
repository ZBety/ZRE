package com.example.ruleEngine.plugin.management.repository;

import com.example.ruleEngine.plugin.management.domain.PluginManifest;
import org.springframework.data.repository.CrudRepository;

public interface PluginManifestRepository extends CrudRepository<PluginManifest, String> {
}
