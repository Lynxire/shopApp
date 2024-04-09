package terabu;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import terabu.entity.Role;
import terabu.entity.User;
import terabu.repository.impl.UserRepositoryImpl;
import terabu.service.UserService;
@Configuration
@ComponentScan("terabu")
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext
                (Main.class);
        UserService userService = ctx.getBean("userService", UserService.class);
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
        System.out.println(userService.findById(1L));
    }
}