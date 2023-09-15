package application.onlinebookstore;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineBookStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineBookStoreApplication.class, args);
    }

    public static String modifyList(String list) {
        return Arrays.stream(list.split(";"))
                .filter(entry -> entry.split(":").length > 1)
                .map(entry -> entry.split(":"))
                .map(parts -> "(" + parts[1].toUpperCase() + ", " + parts[0].toUpperCase() + ")")
                .sorted()
                .collect(Collectors.joining());
    }
}
