package global.techhub.base.mapper.template;

import global.techhub.base.dto.template.MailTemplateDTO;
import global.techhub.base.entity.template.MailTemplate;
import global.techhub.base.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MailTemplateMapper extends BaseMapper<MailTemplateDTO, MailTemplate> {

    MailTemplateMapper INSTANCE = Mappers.getMapper(MailTemplateMapper.class);
}
