package terabu;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import terabu.entity.Order;
import terabu.entity.status.OrderStatus;
import terabu.entity.status.Role;
import terabu.entity.User;
import terabu.service.OrderService;
import terabu.service.UserService;

@Configuration
@ComponentScan("terabu")
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext
                (Main.class);
        UserService userService = ctx.getBean("userService", UserService.class);
        OrderService orderService = ctx.getBean("orderService", OrderService.class);
        User user = new User();
        user.setId(null);
        user.setLogin("Yaroslav");
        user.setPassword("qwerty123");
        user.setEmail("Yaroslav@gmail.com");
        user.setRole(Role.Admin);
//        userService.save(user);
        User user1 = new User();
        user1.setId(null);
        user1.setLogin("UAA");
        user1.setPassword("213123123123");
        user1.setEmail("pipa@gmail.com");
        user1.setRole(Role.Client);
//        userService.save(user1);
//        userService.deleteById(2L);
//        System.out.println(userService.findAll());
//        System.out.println(userService.findUserByIdIn(List.of(1L, 2L)));
//        System.out.println(userService.findUserById(1L));
//        userService.deleteAll();
//        System.out.println(userService.findAll());
//        userService.save(user1);
//        orderService.updateOrderStatus(1L, OrderStatus.CREATE);
        System.out.println(orderService.findAllByOrdersId(1L));
    }
}