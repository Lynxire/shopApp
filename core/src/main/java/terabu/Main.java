package terabu;

import terabu.entity.Role;
import terabu.entity.User;
import terabu.repository.impl.UserRepositoryImpl;
import terabu.service.UserService;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService(new UserRepositoryImpl());
        User user = new User();
        user.setId(null);
        user.setLogin("Yaroslav");
        user.setPassword("qwerty123");
        user.setEmail("Yaroslav@gmail.com");
        user.setRole(Role.Admin);
//        userService.add(user);
        User user1 = new User();
        user1.setId(null);
        user1.setLogin("Dima");
        user1.setPassword("1234");
        user1.setEmail("Mem@gmail.com");
        user1.setRole(Role.Client);
//        userService.add(user1);
        System.out.println(userService.allUsers());
    }
}