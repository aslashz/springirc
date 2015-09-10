package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		log.info("Create dummy SQL table");
		jdbcTemplate.execute("DROP TABLE chats IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE chats(id SERIAL, user VARCHAR(255), message VARCHAR(255))");
		// seed //
		jdbcTemplate.update("INSERT INTO chats(user, message) VALUES(?,?)", "Admin", "First Blood !");
	
	}
}