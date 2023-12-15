package application.onlinebookstore.config;

import org.testcontainers.containers.PostgreSQLContainer;

public class CustomPostgreSQLContainer extends PostgreSQLContainer<CustomPostgreSQLContainer> {
    private static final String DB_IMAGE = "postgres:latest";

    private CustomPostgreSQLContainer() {
        super(DB_IMAGE);
    }

    private static CustomPostgreSQLContainer container;

    public static CustomPostgreSQLContainer getInstance() {
        if (container == null) {
            container = new CustomPostgreSQLContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("spring.datasource.url", container.getJdbcUrl());
        System.setProperty("spring.datasource.username", container.getUsername());
        System.setProperty("spring.datasource.password", container.getPassword());
    }
    @Override
    public void stop() {
    }
}