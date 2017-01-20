package hr.spring.uservice.auth.repository;

import hr.spring.uservice.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, QueryDslPredicateExecutor<Role> {
}
