package vn.com.t3h.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class ProductsEntity extends BaseEntity{

    private String name;
    private String description;
    private String pathAvatar;


}
