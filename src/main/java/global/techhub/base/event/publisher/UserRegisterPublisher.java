package global.techhub.base.event.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import global.techhub.base.dto.user.UserDTO;

@RequiredArgsConstructor
@Component
public class UserRegisterPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishUserRegisterEvent(UserDTO userDTO) {
        applicationEventPublisher.publishEvent(userDTO);
    }
}
