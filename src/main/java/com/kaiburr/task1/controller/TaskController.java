package com.kaiburr.task1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaiburr.task1.model.Task;
import com.kaiburr.task1.model.RequestJSONResponse;
import com.kaiburr.task1.repository.TaskRepository;
import com.kaiburr.task1.service.TaskService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

	@Autowired
	TaskService taskService;

	@Autowired
	TaskRepository taskRepo;

	@GetMapping("/search")
	public ResponseEntity<Object> searchTask(@RequestHeader(value = "id", required = false) String taskID) {
		if (taskID != null && !taskID.isEmpty()) {
			Task foundTask = taskRepo.findById(taskID).orElse(null);

			if (foundTask != null) {
				return new ResponseEntity<>(foundTask, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(
						new RequestJSONResponse().setMessage("Task not found").setStatus(HttpStatus.NOT_FOUND),
						HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<>(taskRepo.findAll(), HttpStatus.OK);
	}

	@GetMapping("/searchbyname")
	public ResponseEntity<Object> searchTaskByName(@RequestHeader("name") String taskName) {
		if (taskName != null && !taskName.isEmpty()) {
			List<Task> foundTask = taskRepo.findAllByName(taskName);

			if (!foundTask.isEmpty()) {
				return new ResponseEntity<>(foundTask, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new RequestJSONResponse().setMessage("Task name NOT found in the Database!")
						.setStatus(HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<>(new RequestJSONResponse()
				.setMessage("Task name is NOT given, Please input the Task name").setStatus(HttpStatus.NOT_FOUND),
				HttpStatus.NOT_FOUND);
	}

	@GetMapping("/searchbyassignee")
	public ResponseEntity<Object> searchTaskByAssignee(@RequestHeader("assignee") String assigneeName) {
		if (assigneeName != null && !assigneeName.isEmpty()) {
			List<Task> foundTask = taskRepo.findAllByAssignee(assigneeName,
					PageRequest.of(0, 10, Sort.by("startTime").descending()));

			if (!foundTask.isEmpty()) {
				return new ResponseEntity<>(foundTask, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new RequestJSONResponse()
						.setMessage("Assignee name NOT found in the Database!").setStatus(HttpStatus.NOT_FOUND),
						HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<>(
				new RequestJSONResponse().setMessage("Assignee name is NOT given, Please input the Assignee name")
						.setStatus(HttpStatus.NOT_FOUND),
				HttpStatus.NOT_FOUND);
	}

	@PutMapping("/create")
	public ResponseEntity<String> createTask(@RequestBody Task body) {
		//System.out.println("Printing the body: " + body.toString());
		String taskID = body.getId();
		if (taskID != null && !taskID.isEmpty()) {
			Task foundTask = taskRepo.findById(taskID).orElse(null);

			if (foundTask == null) {
				taskRepo.save(new Task(body.getName(), taskID, body.getAssignee(), body.getProject(),
						body.getStartTime(), taskService.generateSanjayRajeshProperty()));
				return new ResponseEntity<>("Task creation successfully done", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Task already present in the Database!", HttpStatus.ALREADY_REPORTED);
			}

		} else {
			return new ResponseEntity<>("Task creation details not found", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteTask(@RequestHeader("id") String taskID) {
		if (taskID != null && !taskID.isEmpty()) {
			Task foundTask = taskRepo.findById(taskID).orElse(null);

			if (foundTask != null) {
				taskRepo.deleteById(taskID);
				return new ResponseEntity<>("Task with id " + taskID + " deleted successfully", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Task not found, could not delete!", HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>("Task Id is NOT given, Please input the task Id", HttpStatus.NOT_FOUND);
		}
	}
}
