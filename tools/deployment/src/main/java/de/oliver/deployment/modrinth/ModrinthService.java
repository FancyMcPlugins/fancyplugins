package de.oliver.deployment.modrinth;

import de.oliver.deployment.Configuration;
import de.oliver.deployment.git.GitService;
import masecla.modrinth4j.client.agent.UserAgent;
import masecla.modrinth4j.endpoints.version.CreateVersion;
import masecla.modrinth4j.main.ModrinthAPI;
import masecla.modrinth4j.model.version.ProjectVersion;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModrinthService {

    private final ModrinthAPI api;

    public ModrinthService(String apiKey) {
        this.api = ModrinthAPI.rateLimited(
                UserAgent.builder()
                        .authorUsername("Oliver")
                        .contact("oliver@fancyinnovations.com")
                        .build(),
                apiKey
        );
    }

    public void deployPlugin(Configuration config) throws IOException {
        String changelog = Files.readString(Path.of(config.changeLogPath()));
        changelog = changelog.replaceAll("%COMMIT_HASH%", GitService.getCommitHash());
        changelog = changelog.replaceAll("%COMMIT_MESSAGE%", GitService.getCommitMessage());

        String version = Files.readString(Path.of(config.versionPath()));

        String pluginJarPath = config.pluginJarPath().replace("%VERSION%", version);
        File pluginFile = new File(pluginJarPath);

        InputStream pluginJarFileStream = Files.newInputStream(pluginFile.toPath());

        CreateVersion.CreateVersionRequest request = new CreateVersion.CreateVersionRequest(
                version,
                version,
                changelog,
                new ArrayList<>(),
                Arrays.asList(config.supportedVersions()),
                ProjectVersion.VersionType.valueOf(config.channel()),
                Arrays.asList(config.loaders()),
                config.featured(),
                config.projectID(),
                pluginJarPath,
                List.of(pluginFile.getName()),
                List.of(pluginJarFileStream)
        );

        System.out.println("Creating version: " + request);

        CompletableFuture<ProjectVersion> resp = api.versions().createProjectVersion(request);
        ProjectVersion createdVersion = resp.join();

        System.out.println("Version created: " + createdVersion);
    }
}