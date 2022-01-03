package com.etiya.rentACarSpring.businnes.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACarSpring.businnes.abstracts.message.LanguageWordService;
import com.etiya.rentACarSpring.businnes.constants.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
    private Environment environment;
    private LanguageWordService languageWordService;

    @Autowired
    public UserManager(UserDao userDao, ModelMapperService modelMapperService, Environment environment,
                       LanguageWordService languageWordService) {
        super();
        this.userDao = userDao;
        this.modelMapperService = modelMapperService;
        this.environment = environment;
        this.languageWordService = languageWordService;
    }

    @Override
    public DataResult<List<UserSearchListDto>> getAll() {
        List<User> result = this.userDao.findAll();
        List<UserSearchListDto> response = result.stream()
                .map(user -> modelMapperService.forDto().map(user, UserSearchListDto.class))
                .collect(Collectors.toList());

        return new SuccesDataResult<List<UserSearchListDto>>(response, languageWordService.getByLanguageAndKeyId(Messages.UserListed));
    }

    @Override
    public Result add(CreateUserRequest createUserRequest) {
        User user = modelMapperService.forRequest().map(createUserRequest, User.class);
        this.userDao.save(user);
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.UserAdded));
    }

    @Override
    public Result update(UpdateUserRequest updateUserRequest) {
        User user = modelMapperService.forRequest().map(updateUserRequest, User.class);
        this.userDao.save(user);
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.UserUpdated));
    }

    @Override
    public Result delete(DeleteUserRequest deleteUserRequest) {
        this.userDao.deleteById(deleteUserRequest.getUserId());
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.UserDeleted));
    }

    @Override
    public Result existByEmail(String email) {
        if (this.userDao.existsByEmail(email)) {
            return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.MailNotValid));
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

    public Result existByUserId (int userId) {
        if (!this.userDao.existsById(userId)){
            return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.UserNotExist));
        }
        return new SuccesResult();
    }
}
