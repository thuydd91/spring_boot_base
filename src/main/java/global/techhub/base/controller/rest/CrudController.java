package global.techhub.base.controller.rest;

import global.techhub.base.entity.user.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import global.techhub.base.dto.BaseDTO;
import global.techhub.base.entity.BaseEntity;

import javax.validation.Valid;
import java.util.Optional;

@Log4j2
public abstract class CrudController<DTO extends BaseDTO, Entity extends BaseEntity> extends ReadController<DTO, Entity> {


    protected Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal;

        if (authentication == null || !authentication.isAuthenticated()){
            principal = "anonymousUser";
        }
        else {
            principal = authentication.getPrincipal();
        }

        return principal.equals("anonymousUser") ? Optional.empty() : Optional.of((User) principal);
    }

    @PostMapping
    protected ResponseEntity<DTO> create(@RequestBody @Valid DTO body) {

        log.info(String.format("POST %s - body: %s", this.getBaseUrl(), body.toString()));

        DTO result = service.create(body);
        return ResponseEntity.ok(result);
    }

    @PutMapping("{id}")
    protected ResponseEntity<DTO> update(@PathVariable("id") String id, @RequestBody @Valid DTO body) {

        log.info(String.format("PUT %s - body: %s", this.getBaseUrl(), body.toString()));

        DTO result = service.update(id, body);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("{id}")
    protected ResponseEntity<Boolean> delete(@PathVariable("id") String id) {

        log.info(String.format("DELETE %s/%s", this.getBaseUrl(), id));

        service.delete(id);
        return ResponseEntity.ok(true);
    }

    protected abstract String getBaseUrl();
}
