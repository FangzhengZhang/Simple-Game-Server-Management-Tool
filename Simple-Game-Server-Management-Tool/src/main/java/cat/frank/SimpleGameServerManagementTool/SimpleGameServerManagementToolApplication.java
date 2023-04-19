package cat.frank.SimpleGameServerManagementTool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableCaching
public class SimpleGameServerManagementToolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleGameServerManagementToolApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void startJobs() {
		//System.out.println("hello world, I have just started up");
	}
}
