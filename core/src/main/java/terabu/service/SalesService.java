package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import terabu.entity.Sales;
import terabu.repository.SalesRepository;

@Service
@RequiredArgsConstructor
public class SalesService {
    private final SalesRepository salesRepository;
    public Sales addSales(Sales sales) {
        return salesRepository.save(sales);
    }
}
