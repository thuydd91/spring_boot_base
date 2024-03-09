package global.techhub.base.controller.rest.user;

import global.techhub.base.entity.user.User;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import global.techhub.base.dto.user.UserDTO;
import global.techhub.base.service.user.UserService;

import javax.validation.Valid;
import java.util.Optional;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(UserController.BASE_URL)
@Api(value = "User", description = "User Endpoints", tags = { "User" })
public class UserController {

    static final String BASE_URL = "/api/users";

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserDTO userDTO) {
        log.info("POST /api/users/register - body:" + userDTO.toString());

        UserDTO result = userService.register(userDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/me")
    public ResponseEntity getUser() {

        log.info("GET /api/users/me");

        User currentUser = getCurrentUser().get();
        return ResponseEntity.ok(userService.getUserById(currentUser.getId()));
    }

    protected Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getPrincipal().equals("anonymousUser") ? Optional.empty() : Optional.of((User) authentication.getPrincipal());
    }
}
