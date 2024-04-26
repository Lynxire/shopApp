package terabu.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@lombok.Data
@Entity
@Table(schema = "app", name = "data")
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;


}
