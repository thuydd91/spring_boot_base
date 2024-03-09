package global.techhub.base.dto.user;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true) @Getter
public class UserUpdatePasswordCommandDTO {

    private final @NonNull String oldPassword;

    private final @NonNull String password;
}
