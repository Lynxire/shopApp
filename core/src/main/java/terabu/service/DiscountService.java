package terabu.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import terabu.dto.data.UserDataDTO;
import terabu.dto.users.UserDTO;
import terabu.entity.Discount;
import terabu.repository.SalesRepository;

@Transactional
@Service
@RequiredArgsConstructor
public class DiscountService {
    private final SalesRepository salesRepository;
    private final UserService userService;
    private final UserDataService userDataService;

    public Long calculatingDiscount(Long userId) {
        UserDataDTO dataDTO = userDataService.findDataByUserId(userId);
        Discount discount = salesRepository.findByUserId(userId).orElseGet(() ->
                {
                    Discount newDiscount = new Discount();
                    UserDTO user = userService.findUserById(userId);
                    newDiscount.setName("Personal sales: " + user.getLogin());
                    newDiscount.setUserId(user.getId());
                    newDiscount.setSum(1L);
                    salesRepository.save(newDiscount);
                    return newDiscount;
                }
        );

        if (dataDTO.getOrders() == null) {
            dataDTO.setOrders(0L);
        }

        if (dataDTO.getOrders() >= 30) {
            discount.setSum(15L);
            salesRepository.save(discount);
            return discount.getSum();
        }
        if (dataDTO.getOrders() >= 20) {
            discount.setSum(10L);
            salesRepository.save(discount);
            return discount.getSum();
        }
        if (dataDTO.getOrders() >= 10) {
            discount.setSum(5L);
            salesRepository.save(discount);
            return discount.getSum();
        }
        return discount.getSum();
    }
}
