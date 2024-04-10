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

    public User findById(Long id) {
        Optional<User> byId = userRepositorySpringData.findById(id);
        User user = byId.get();
        return user;
    }

    public void deleteById(Long id) {
        userRepositorySpringData.deleteById(id);
    }

    public void deleteAll(){
        userRepositorySpringData.deleteAll();
    }


}
