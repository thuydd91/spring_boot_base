package global.techhub.base.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @Column(name = "id")
    String id;

    boolean enabled = true;
}
