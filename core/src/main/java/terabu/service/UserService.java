package terabu.service;

import terabu.entity.User;
import terabu.repository.UserRepository;
import terabu.repository.impl.UserRepositoryImpl;

public class UserService {
    UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void add(User user){
        repository.add(user);
    }
}
