package web.minda.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import web.minda.project.entity.LoginMaster;
import web.minda.project.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/insertLoginMaster")
	public ResponseEntity<Object> insertUser(@RequestBody LoginMaster jsonObject) {

		try {
			LoginMaster savedUser = userService.createUser(jsonObject);

			Map<String, Object> response = new HashMap<>();
			response.put("message", "User created successfully");
			response.put("user", savedUser);

			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (RuntimeException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);

		} catch (Exception e) {

			return new ResponseEntity<>("Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}