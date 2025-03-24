package vn.com.t3h.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.t3h.entity.ClaimStatusEntity;

@Repository
public interface StatusRepository extends JpaRepository<ClaimStatusEntity, Long> {
}
