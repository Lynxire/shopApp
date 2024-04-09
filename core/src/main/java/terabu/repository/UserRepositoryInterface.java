package terabu.repository;

import terabu.entity.User;

import java.util.List;

public interface UserRepositoryInterface {
    public void add (User user);
    public void deleteById(Long id);
    public List<User> viewAllUsers();
}
