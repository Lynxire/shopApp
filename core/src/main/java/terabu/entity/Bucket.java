package terabu.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private Long id;
    private Long count;
    private Long sum;

    @ManyToMany
    @JoinTable(name = "bucket",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "goods_id", referencedColumnName = "id"))
    private List<Order> orders = new ArrayList<>();
}
