package terabu.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import terabu.entity.Sales;
import terabu.entity.User;
import terabu.entity.UserData;
import terabu.repository.SalesRepository;
import terabu.repository.UserDataRepository;
import terabu.repository.UserRepositorySpringData;

@Transactional
@Service
@RequiredArgsConstructor
public class SalesService {
    private final SalesRepository salesRepository;
    private final UserDataRepository userDataRepository;
    private final UserRepositorySpringData repositorySpringData;

    public Sales addSales(Sales sales) {
        return salesRepository.save(sales);
    }

    public Long calculatingDiscount(Long userId) {
        UserData userData = userDataRepository.findByUserId(userId);
        Sales sales = salesRepository.findByUserId(userId).orElseGet(() ->
                {
                    Sales newSales = new Sales();
                    newSales.setName("Personal sales: " + userData.getName());
                    User user = repositorySpringData.findById(userId).get();
                    newSales.setUser(user);
                    newSales.setSum(1L);
                    salesRepository.save(newSales);
                    return newSales;
                }
        );
        if (userData.getOrders() == null) {
            userData.setOrders(0L);
        }

        if (userData.getOrders() >= 30) {
            sales.setSum(15L);
            salesRepository.save(sales);
            return sales.getSum();
        }
        if (userData.getOrders() >= 20) {
            sales.setSum(10L);
            salesRepository.save(sales);
            return sales.getSum();
        }
        if (userData.getOrders() >= 10) {
            sales.setSum(5L);
            salesRepository.save(sales);
            return sales.getSum();
        }
        return sales.getSum();
    }
}
