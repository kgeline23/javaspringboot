package com.project.todowebservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.project.todowebservice.models.Todo;
import com.project.todowebservice.repositories.TodoRepository;


@SpringBootApplication
public class TodoWebserviceApplication {

	private static final Logger log = LoggerFactory.getLogger(TodoWebserviceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TodoWebserviceApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(TodoRepository repo)
	{
		return (args) ->
		{
			repo.save(new Todo("Project initialization", true));
			repo.save(new Todo("Data model", true));
			repo.save(new Todo("Repository", false));
			repo.save(new Todo("Controller", false));
			repo.save(new Todo("Tests", false));

			//get all todos
			log.info("All todos:");
			log.info("-----------------");
			repo.findAll().forEach(todo -> {log.info(todo.toString());});
			log.info("");
		};

	}

}
