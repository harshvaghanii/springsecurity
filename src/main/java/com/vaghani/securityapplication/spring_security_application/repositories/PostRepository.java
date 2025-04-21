package com.vaghani.securityapplication.spring_security_application.repositories;

import com.vaghani.securityapplication.spring_security_application.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
