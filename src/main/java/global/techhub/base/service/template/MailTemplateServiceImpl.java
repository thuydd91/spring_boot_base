package global.techhub.base.service.template;

import global.techhub.base.repository.template.MailTemplateRepository;
import org.springframework.stereotype.Service;
import global.techhub.base.dto.template.MailTemplateDTO;
import global.techhub.base.entity.template.MailTemplate;
import global.techhub.base.mapper.template.MailTemplateMapper;
import global.techhub.base.service.BaseService;

@Service
public class MailTemplateServiceImpl extends BaseService<MailTemplateDTO, MailTemplate> implements MailTemplateService {

    public MailTemplateServiceImpl(MailTemplateMapper mailTemplateMapper,
                                   MailTemplateRepository mailTemplateRepository) {

        this.mapper = mailTemplateMapper;
        this.repository = mailTemplateRepository;
    }

}
