package com.panchek.wp.readmore.repository;

import com.panchek.wp.readmore.model.Role;
import com.panchek.wp.readmore.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleName roleName);
}
