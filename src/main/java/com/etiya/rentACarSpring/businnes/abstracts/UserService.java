package com.etiya.rentACarSpring.businnes.abstracts;

import java.util.List;

import com.etiya.rentACarSpring.businnes.dtos.UserSearchListDto;
import com.etiya.rentACarSpring.businnes.request.UserRequest.CreateUserRequest;
import com.etiya.rentACarSpring.businnes.request.UserRequest.DeleteUserRequest;
import com.etiya.rentACarSpring.businnes.request.UserRequest.UpdateUserRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.entities.User;

public interface UserService {
	DataResult<List<UserSearchListDto>> getAll();
	Result add(CreateUserRequest createUserRequest);
	Result update(UpdateUserRequest updateUserRequest);
	Result delete(DeleteUserRequest deleteUserRequest);
	Result existByEmail(String email);
	DataResult<User> getById(int userId);
	DataResult<User> getByEmail(String email);
	User getByUserId(int userId);
	Result existByUserId (int userId);
}
