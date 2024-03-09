package global.techhub.base.entity.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "authority")
    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }

    @ManyToMany(mappedBy = "roles")
    Set<User> users;
}
