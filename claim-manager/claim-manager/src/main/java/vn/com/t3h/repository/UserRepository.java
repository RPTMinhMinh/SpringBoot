package vn.com.t3h.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.com.t3h.entity.UserEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u " +
            "WHERE (:code is null or u.code = :code) " +
            "and (:createDate is null or u.createdDate >= :createDate) " +
            "and (:address is null or u.address =:address)")
    Page<UserEntity> findByCondition(@Param("code") String code,
                                     @Param("createDate") LocalDateTime createDate,
                                     @Param("address") String address,
                                     Pageable pageable);
}
