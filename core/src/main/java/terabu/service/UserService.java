package terabu.service;

import terabu.entity.User;
import terabu.repository.UserRepositoryInterface;

import java.util.List;

public class UserService {
    UserRepositoryInterface repository;

    public UserService(UserRepositoryInterface repository) {
        this.repository = repository;
    }

    public void add(User user){
        repository.add(user);
    }
    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public List<User> allUsers(){
        return repository.viewAllUsers();
    }
}
