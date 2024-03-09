package global.techhub.base.entity.template;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import global.techhub.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mail_template")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class MailTemplate extends BaseEntity {

    @Column(name = "name")
    String name;

    @Column(name = "template")
    String template;
}
