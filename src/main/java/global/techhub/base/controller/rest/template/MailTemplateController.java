package global.techhub.base.controller.rest.template;

import global.techhub.base.dto.template.MailTemplateDTO;
import global.techhub.base.entity.template.MailTemplate;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import global.techhub.base.controller.rest.CrudController;

@Log4j2
@RestController
@RequestMapping(MailTemplateController.BASE_URL)
@Api(value = "Mail Template", description = "Mail Template Endpoints", tags = { "Mail Template" })
public class MailTemplateController extends CrudController<MailTemplateDTO, MailTemplate> {

    static final String BASE_URL = "/api/mail-template";


    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }
}
