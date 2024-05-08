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
    private final UserDataRepository userDataRepository;
    private final UserRepositorySpringData repositorySpringData;

    public Discount addSales(Discount discount) {
        return salesRepository.save(discount);
    }

    public Long calculatingDiscount(Long userId) {
        UserData userData = userDataRepository.findByUserId(userId);
        Discount discount = salesRepository.findByUserId(userId).orElseGet(() ->
                {
                    Discount newDiscount = new Discount();
                    newDiscount.setName("Personal sales: " + userData.getName());
                    User user = repositorySpringData.findById(userId).get();
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
