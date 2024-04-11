package terabu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(schema = "app", name = "client")
@PrimaryKeyJoinColumn(name = "user_id")
public class Client extends User{
    @Column(name = "date_registration")
    private LocalDate dateRegistration;
}
