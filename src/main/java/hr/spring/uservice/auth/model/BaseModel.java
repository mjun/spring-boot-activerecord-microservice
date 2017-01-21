package hr.spring.uservice.auth.model;

import com.eaio.uuid.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@MappedSuperclass
@EqualsAndHashCode(of = "uuid")
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String uuid;

    public BaseModel() {
        this.uuid = (new UUID()).toString();
    }

    @Transient
    public boolean isNew() {
        return (this.id == null);
    }

    @Transient
    abstract public void save();

    @Transient
    abstract public void delete();

}