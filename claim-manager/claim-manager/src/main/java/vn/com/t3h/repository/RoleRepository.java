package vn.com.t3h.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.com.t3h.entity.RoleEntity;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByCodeAndDeletedIsFalse(String code);

    @Query("select r from RoleEntity r " +
            "join r.users u " +
            "where u.username =:username")
    Set<RoleEntity> findByUsername(String username); //FetchType.LAZY
}
