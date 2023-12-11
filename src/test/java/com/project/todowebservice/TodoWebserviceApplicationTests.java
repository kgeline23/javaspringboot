package com.project.todowebservice;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import com.project.todowebservice.controllers.TodoController;
import com.project.todowebservice.models.Todo;
import com.project.todowebservice.repositories.TodoRepository;

@SpringBootTest
class TodoWebserviceApplicationTests {
	@Mock
	private TodoRepository repos;

	@InjectMocks
	private TodoController controller;

	// Getting all todos
	// expected result is a list of three todos
	@Test
	void testGetAllTodos() {
		// add some mock todos to the repository
		Todo mockTodo1 = new Todo("test get by id", true);
		mockTodo1.setId(Long.parseLong("1"));		
		Todo mockTodo2 = new Todo("Trying to get by id", true);
		mockTodo2.setId(Long.parseLong("2"));
		Todo mockTodo3 = new Todo("Testing", true);
		mockTodo3.setId(Long.parseLong("3"));

		// Mock repository behavior
		when(repos.save(any(Todo.class))).thenReturn(mockTodo1);
		repos.save(mockTodo1);
		when(repos.save(any(Todo.class))).thenReturn(mockTodo2);
		repos.save(mockTodo2);
		when(repos.save(any(Todo.class))).thenReturn(mockTodo3);
		repos.save(mockTodo3);

		// Call the controller method
		ResponseEntity<List<Todo>> result = controller.getAllTodos();
		Assert.state(result.getBody().size() == 3, "Expected 3 todos");
		Assert.isTrue(result.getStatusCode() == HttpStatus.NO_CONTENT, "No content");
	}

	// Search for todos with id 1
	// expected result of one todos
	@Test
	void testGetTodoByID() {
		// add some mock todos to the repository
		Todo mockTodo1 = new Todo("test get by id", true);
		mockTodo1.setId(Long.parseLong("1"));
		when(repos.save(any(Todo.class))).thenReturn(mockTodo1);
		repos.save(mockTodo1);
		Todo mockTodo2 = new Todo("Trying to get by id", true);
		mockTodo2.setId(Long.parseLong("2"));
		when(repos.save(any(Todo.class))).thenReturn(mockTodo2);
		repos.save(mockTodo2);

		ResponseEntity<Todo> result = controller.getTodoByID(1);
		Assert.state(result.getBody().getId() == 1, "Todo wit id 1 found");
		Assert.isTrue(result.getStatusCode() == HttpStatus.OK, "Task(s) found");
	}

	// Search for todos that contain the word "Test"
	// expected result is a list of 2 todos
	@Test
	void testGetTodoByName() {
		// add some mock todos to the repository
		Todo mockTodo1 = new Todo("Test get by name", true);
		mockTodo1.setId(Long.parseLong("1"));
		when(repos.save(any(Todo.class))).thenReturn(mockTodo1);
		repos.save(mockTodo1);

		Todo mockTodo2 = new Todo("Trying to get by name", true);
		mockTodo2.setId(Long.parseLong("2"));
		when(repos.save(any(Todo.class))).thenReturn(mockTodo2);
		repos.save(mockTodo2);

		Todo mockTodo3 = new Todo("Testing", true);
		mockTodo3.setId(Long.parseLong("3"));
		when(repos.save(any(Todo.class))).thenReturn(mockTodo3);
		repos.save(mockTodo3);

		ResponseEntity<List<Todo>> result = controller.getTodosByName("Test");
		Assert.state(result.getBody().size() == 2, "Expected 2 todos");
		Assert.isTrue(result.getStatusCode() == HttpStatus.OK, "Task(s) found");
	}

	// Search for todos that are incomplete (status == false)
	// expected result is one todo (name:"Test incompleted")
	@Test
	void testGetTodoByStatus() {
		// add some mock todos to the repository
		Todo mockTodo1 = new Todo("Test completed", true);
		mockTodo1.setId(Long.parseLong("1"));
		when(repos.save(any(Todo.class))).thenReturn(mockTodo1);
		repos.save(mockTodo1);

		Todo mockTodo2 = new Todo("Test incompleted", false);
		mockTodo2.setId(Long.parseLong("2"));
		when(repos.save(any(Todo.class))).thenReturn(mockTodo2);
		repos.save(mockTodo2);

		ResponseEntity<List<Todo>> result = controller.getTodoByStatus(false);
		Assert.state(result.getBody().size() == 1, "Expected 1 todo");
		Assert.state(result.getBody().get(0).getId().equals(2), "Unexpected name for todo 1");
		Assert.isTrue(result.getStatusCode() == HttpStatus.OK, "Task(s) found");
	}

	// Adding/saving a new todo item
	// expected result is HttpStatus.CREATED
	@Test
	void testAddTodo() 
	{
		// Mock data
		Todo mockTodo = new Todo("New Task", false);
		mockTodo.setId(Long.parseLong("1"));

		// Mock repository behavior
		when(repos.save(any(Todo.class))).thenReturn(mockTodo);

		// Call the controller method
		ResponseEntity<Todo> result = controller.addTodo(mockTodo);
		Assert.state(result.getBody().getId().equals(1), "Unexpected ID for the saved todo");
		Assert.isTrue(result.getStatusCode() == HttpStatus.CREATED, "Task created");
	}

	// Update name and status of an existing todo item
	// expected result change in name and status
	@Test
	void testUpdateTodo() 
	{
		// add some mock todos to the mock repository
		Todo mockTodo1 = new Todo("Test1 completed", true);
		mockTodo1.setId(Long.parseLong("1"));
		Todo mockTodo2 = new Todo("Test2 incompleted", false);
		mockTodo2.setId(Long.parseLong("2"));
		
		// Mock repository behavior
		when(repos.save(any(Todo.class))).thenReturn(mockTodo1);
		repos.save(mockTodo1);
		when(repos.save(any(Todo.class))).thenReturn(mockTodo2);
		repos.save(mockTodo2);

		//new edit
		Todo editTodo = new Todo("Test2 completed", true);

		// Call the controller method
		ResponseEntity<Todo> result = controller.editTodo(2,editTodo);
		Assert.state(result.getBody().getStatus().equals(true), "Status changed");
		Assert.state(result.getBody().getName().equals("Test2 completed"), "Name changed");
		Assert.isTrue(result.getStatusCode() == HttpStatus.OK, "Todo completed");
	}

	// Delete a todo item
	// expected result content not found after deletion
	@Test
	void testDeleteTodo() {
		// add some mock todos to the mock repository
		Todo mockTodo1 = new Todo("Test1 completed", true);
		mockTodo1.setId(Long.parseLong("1"));
		Todo mockTodo2 = new Todo("Test2 incompleted", false);
		mockTodo2.setId(Long.parseLong("2"));
		
		// Mock repository behavior
		when(repos.save(any(Todo.class))).thenReturn(mockTodo1);
		repos.save(mockTodo1);
		when(repos.save(any(Todo.class))).thenReturn(mockTodo2);
		repos.save(mockTodo2);

		ResponseEntity<HttpStatus> result = controller.deleteTodo(2);
		Assert.isTrue(result.getStatusCode() == HttpStatus.NO_CONTENT, "No content / task deleted");
	}

}
