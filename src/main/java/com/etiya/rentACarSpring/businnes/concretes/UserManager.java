package com.etiya.rentACarSpring.businnes.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.businnes.abstracts.UserService;
import com.etiya.rentACarSpring.businnes.dtos.UserSearchListDto;
import com.etiya.rentACarSpring.businnes.request.UserRequest.CreateUserRequest;
import com.etiya.rentACarSpring.businnes.request.UserRequest.DeleteUserRequest;
import com.etiya.rentACarSpring.businnes.request.UserRequest.UpdateUserRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.ErrorDataResult;
import com.etiya.rentACarSpring.core.utilities.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccesDataResult;
import com.etiya.rentACarSpring.core.utilities.results.SuccesResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.UserDao;
import com.etiya.rentACarSpring.entities.User;

@Service
public class UserManager implements UserService {

	private UserDao userDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public UserManager(UserDao userDao, ModelMapperService modelMapperService) {
		super();
		this.userDao = userDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<UserSearchListDto>> getAll() {
		List<User> result = this.userDao.findAll();
		List<UserSearchListDto> response = result.stream()
				.map(user -> modelMapperService.forDto().map(user, UserSearchListDto.class))
				.collect(Collectors.toList());

		return new SuccesDataResult<List<UserSearchListDto>>(response);
	}

	@Override
	public Result Add(CreateUserRequest createUserRequest) {
		User user = modelMapperService.forRequest().map(createUserRequest, User.class);
//		int findexScore=1000;
//		user.setFindexScore(findexScore);
		this.userDao.save(user);
		return new SuccesResult("Ekleme İslemi Basarili");
	}

	@Override
	public Result Update(UpdateUserRequest updateUserRequest) {
		User user = modelMapperService.forRequest().map(updateUserRequest, User.class);
		this.userDao.save(user);
		return new SuccesResult("Guncelleme İslemi Basarili");
	}

	@Override
	public Result Delete(DeleteUserRequest deleteUserRequest) {
		this.userDao.deleteById(deleteUserRequest.getUserId());
		return new SuccesResult("Silme İslemi Basarili");
	}

	@Override
	public Result existByEmail(String email) {
		if (this.userDao.existsByEmail(email)) {
			return new ErrorResult("Mail mevcut değil");
		}
		return new SuccesResult();
	}

	@Override
	public DataResult<User> getById(int userId) {
		return new SuccesDataResult<User>(this.userDao.getById(userId));
	}

	@Override
	public DataResult<User> getByEmail(String email) {
		if (this.userDao.existsByEmail(email)) {
			return new SuccesDataResult<User>(this.userDao.getByEmail(email));
		}
		return new ErrorDataResult<User>(null);
	}

	@Override
	public User getByUserId(int userId) {
		return userDao.getById(userId);
	}

}
