package application.onlinebookstore;

import application.onlinebookstore.config.CustomPostgreSQLContainer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
class OnlineBookStoreApplicationTests {

	@Container
	private static final PostgreSQLContainer<?> postgresContainer =
			CustomPostgreSQLContainer.getInstance();

	@Test
	void contextLoads() {
	}
}
