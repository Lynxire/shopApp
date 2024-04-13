package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import terabu.entity.User;
import terabu.repository.UserRepositorySpringData;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepositorySpringData userRepositorySpringData;

//    public void add(User user){
//        repository.add(user);
//    }
//    public void deleteById(Long id){
//        repository.deleteById(id);
//    }

    public void save(User user) {
        userRepositorySpringData.save(user);
    }

    public List<User> findAll() {
        return userRepositorySpringData.findAll();
    }

    public User findUserById(Long id) {
        User userById = userRepositorySpringData.findUserById(id);
        return userById;
    }

    public void deleteById(Long id) {
        userRepositorySpringData.deleteById(id);
    }

    public void deleteAll() {
        userRepositorySpringData.deleteAll();
    }

    public List<User> findUserByIdIn(List<Long> id) {
        return userRepositorySpringData.findUserByIdIn(id);
    }


}
