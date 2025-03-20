
package vn.com.t3h.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "insurance_product")
@Getter
@Setter
public class InsuranceProductEntity extends BaseEntity {

    private String name;
    @Column(name = "description",columnDefinition = "text")
    private String description;
    private String coverage;
    // Getters and Setters
}
