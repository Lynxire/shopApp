package terabu.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import terabu.entity.Discount;
import terabu.entity.User;
import terabu.entity.UserData;
import terabu.repository.SalesRepository;
import terabu.repository.UserDataRepository;
import terabu.repository.UserRepositorySpringData;

@Transactional
@Service
@RequiredArgsConstructor
public class DiscountService {
    private final SalesRepository salesRepository;
    private final UserRepositorySpringData userRepository;
    private final UserDataRepository userDataRepository;

    public Long calculatingDiscount(Long userId) {
        UserData userData = userDataRepository.findByUserId(userId);
        Discount discount = salesRepository.findByUserId(userId).orElseGet(() ->
                {
                    Discount newDiscount = new Discount();
                    User user = userRepository.findById(userId).get();
                    newDiscount.setName("Personal sales: " + user.getLogin());
                    newDiscount.setUser(user);
                    newDiscount.setSum(1L);
                    salesRepository.save(newDiscount);
                    return newDiscount;
                }
        );

        if (userData.getOrders() == null) {
            userData.setOrders(0L);
        }

        if (userData.getOrders() >= 30) {
            discount.setSum(15L);
            salesRepository.save(discount);
            return discount.getSum();
        }
        if (userData.getOrders() >= 20) {
            discount.setSum(10L);
            salesRepository.save(discount);
            return discount.getSum();
        }
        if (userData.getOrders() >= 10) {
            discount.setSum(5L);
            salesRepository.save(discount);
            return discount.getSum();
        }
        return discount.getSum();
    }
}
