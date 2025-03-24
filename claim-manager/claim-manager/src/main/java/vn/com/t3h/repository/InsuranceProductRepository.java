package vn.com.t3h.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.t3h.entity.InsuranceProductEntity;
import vn.com.t3h.entity.ProductsEntity;

@Repository
public interface InsuranceProductRepository extends JpaRepository<InsuranceProductEntity, Long> {
}
