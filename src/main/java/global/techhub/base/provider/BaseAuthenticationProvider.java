package global.techhub.base.provider;

import global.techhub.base.entity.user.Role;
import global.techhub.base.entity.user.User;
import global.techhub.base.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class BaseAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();

        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String password = authentication.getCredentials().toString();
            if (passwordEncoder.matches(password, user.getPassword())) {
                Set<Role> roles = user.getRoles();
                return new UsernamePasswordAuthenticationToken(user, password, roles);
            } else {
                throw new AuthenticationCredentialsNotFoundException("Password is incorrect");
            }

        } else {
            throw new AuthenticationCredentialsNotFoundException("Username not found");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication == UsernamePasswordAuthenticationToken.class;
    }
}
