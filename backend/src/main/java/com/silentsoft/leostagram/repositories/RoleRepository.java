package com.silentsoft.leostagram.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.silentsoft.leostagram.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
