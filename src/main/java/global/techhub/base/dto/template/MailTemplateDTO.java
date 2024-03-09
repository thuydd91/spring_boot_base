package global.techhub.base.dto.template;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import global.techhub.base.dto.BaseDTO;

@Data
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper=true)
public class MailTemplateDTO extends BaseDTO {

    String name;

    String template;
}
