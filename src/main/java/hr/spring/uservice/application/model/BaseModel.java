package hr.spring.uservice.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import hr.spring.uservice.application.configuration.ApplicationContextProvider;
import hr.spring.uservice.application.repository.BaseModelRepository;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@MappedSuperclass
@EqualsAndHashCode
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    protected Long id;

    @Transient
    public boolean isNew() {
        return (this.id == null);
    }

    @Transient
    public static BaseModelRepository getRepository() {
        return (BaseModelRepository) ApplicationContextProvider.getRepository(
                new Object() { }.getClass().getEnclosingClass()
        );
    }

    @Transient
    public void save() {
        getRepository().save(this);
    }

    @Transient
    public void delete() {
        getRepository().delete(this);
        this.id = null;
    }

}
