package com.etiya.rentACarSpring.ws;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.etiya.rentACarSpring.businnes.abstracts.UserService;
import com.etiya.rentACarSpring.businnes.dtos.UserSearchListDto;
import com.etiya.rentACarSpring.businnes.request.UserRequest.CreateUserRequest;
import com.etiya.rentACarSpring.businnes.request.UserRequest.DeleteUserRequest;
import com.etiya.rentACarSpring.businnes.request.UserRequest.UpdateUserRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;


public class UsersController {
	private UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("getAll")
	public DataResult<List<UserSearchListDto>> getAll() {
		return userService.getAll();
	}

	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateUserRequest createUserRequest) {

		return this.userService.add(createUserRequest);
	}

	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
		return this.userService.update(updateUserRequest);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteUserRequest deleteUserRequest) {
		return this.userService.delete(deleteUserRequest);
	}
}
