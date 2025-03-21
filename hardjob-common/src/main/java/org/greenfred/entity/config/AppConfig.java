package org.greenfred.entity.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${project.folder}")
    private String projectFolder;

    @Value("${super.admin.phones}")
    private String superAdminPhones;

    public String getProjectFolder() {
        return projectFolder;
    }

    public String getSuperAdminPhones() {
        return superAdminPhones;
    }
}
