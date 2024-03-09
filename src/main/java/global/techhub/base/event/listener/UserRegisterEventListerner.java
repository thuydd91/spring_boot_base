package global.techhub.base.event.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import global.techhub.base.dto.user.UserDTO;

@Log4j2
@Component
public class UserRegisterEventListerner {

    @EventListener
    public void handleUserRegisterEvent(UserDTO userDTO) {
        log.info("handleUserRegisterEvent: " + userDTO.toString());
    }
}
