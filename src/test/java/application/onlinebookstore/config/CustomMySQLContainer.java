package application.onlinebookstore.config;

import org.testcontainers.containers.MySQLContainer;

public class CustomMySQLContainer extends MySQLContainer<CustomMySQLContainer> {
    private static final String DB_IMAGE = "mysql:8";
    private static final String DB_URL = "TEST_DB_URL";
    private static final String DB_USERNAME = "TEST_DB_USERNAME";
    private static final String DB_PASSWORD = "TEST_DB_PASSWORD";
    private static CustomMySQLContainer container;

    private CustomMySQLContainer() {
        super(DB_IMAGE);
    }

    public static CustomMySQLContainer getInstance() {
        if (container == null) {
            container = new CustomMySQLContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty(DB_URL, container.getJdbcUrl());
        System.setProperty(DB_USERNAME, container.getUsername());
        System.setProperty(DB_PASSWORD, container.getPassword());
    }

    @Override
    public void stop() {

    }
}