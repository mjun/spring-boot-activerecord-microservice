package hr.spring.uservice.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import hr.spring.uservice.application.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "auth_user")
public class User extends BaseModel implements UserDetails {

    @Column(name = "first_name")
    @JsonProperty("first_name")
    private String firstName;

    @Column(name = "last_name")
    @JsonProperty("last_name")
    private String lastName;

    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    @Column(name = "username", unique = true, nullable = false, length = 45)
    @JsonProperty("username")
    private String username;

    @Column(name = "password", nullable = false, length = 60)
    @JsonProperty("password")
    private String password;

    @Column(name = "is_enabled", nullable = false)
    @JsonProperty("is_enabled")
    private boolean enabled;

    @OneToMany(mappedBy = "user")
    @JsonProperty("roles")
    private Set<Role> roles;

    @Override
    @Transient
    public Set<Role> getAuthorities() {
        return roles;
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

}