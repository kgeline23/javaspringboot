package com.project.todowebservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.todowebservice.controllers.TodoController;

@SpringBootTest
class TodoWebserviceApplicationTests {
	TodoController contr;
	@Test
	void contextLoads() {
	}

	@Test
	void expectErrorOnEmptyRepo() {
		// Mock controller
		// set contr.repo = null
		// expect to throw 500
	}

}
