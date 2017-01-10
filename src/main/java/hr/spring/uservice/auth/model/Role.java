package hr.spring.uservice.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import hr.spring.uservice.application.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "auth_role",
        uniqueConstraints = @UniqueConstraint(
                columnNames = { "role", "username_id" }))
public class Role extends BaseModel implements GrantedAuthority {

    @ManyToOne(fetch = FetchType.LAZY)
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

}
