package terabu;

import terabu.entity.Role;
import terabu.entity.User;
import terabu.repository.impl.UserRepositoryImpl;
import terabu.service.UserService;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        user.setId(1L);
        user.setLogin("Yaroslav");
        user.setPassword("qwerty123");
        user.setEmail("Yaroslav@gmail.com");
        user.setRole(Role.Admin);

        UserService userService = new UserService(new UserRepositoryImpl());
        userService.add(user);
    }
}