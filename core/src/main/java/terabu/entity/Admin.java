package terabu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(schema = "app", name = "admin")
@Entity
@PrimaryKeyJoinColumn(name = "admin_id")
public class Admin extends User{
    private String log;
}
