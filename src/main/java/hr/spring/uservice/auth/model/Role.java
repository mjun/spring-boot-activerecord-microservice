package hr.spring.uservice.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import hr.spring.uservice.application.configuration.ApplicationContextProvider;
import hr.spring.uservice.auth.repository.RoleRepository;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.repository.support.Repositories;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(of = "uuid", callSuper = false)
@Table(name = "auth_role",
        uniqueConstraints = @UniqueConstraint(
                columnNames = { "role", "username_id" }))
public class Role extends BaseModel implements GrantedAuthority {

    @ManyToOne
    @JoinColumn(name = "username_id", nullable = false)
    @JsonProperty("user")
    private User user;

    @Column(name = "role", nullable = false, length = 45)
    @JsonProperty("role")
    private String role;

    @Override
    @Transient
    public String getAuthority() {
        return role;
    }

    @Transient
    public static RoleRepository getRepository() {
        return (RoleRepository) (new Repositories(ApplicationContextProvider.getApplicationContext())).getRepositoryFor(Role.class);
    }

    @Override
    @Transient
    public void save() {
        getRepository().save(this);
    }

    @Override
    @Transient
    public void delete() {
        getRepository().delete(this);
    }
}
