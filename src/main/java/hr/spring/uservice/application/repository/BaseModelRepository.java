package hr.spring.uservice.application.repository;

import hr.spring.uservice.application.model.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseModelRepository<T extends BaseModel> extends JpaRepository<T, Long> {
}
