package global.techhub.base.controller.rest;

import global.techhub.base.service.BaseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import global.techhub.base.dto.BaseDTO;
import global.techhub.base.entity.BaseEntity;

import java.util.List;

@Log4j2
public abstract class ReadController<DTO extends BaseDTO, Entity extends BaseEntity> {

    protected BaseService<DTO, Entity> service;

    @GetMapping
    protected ResponseEntity<List<DTO>> getAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "1000") int size) {

        log.info(String.format("GET %s?page=%s&size=%s", this.getBaseUrl(), page, size));

        List<DTO> results = service.getAll(page, size);
        return ResponseEntity.ok(results);
    }

    @GetMapping("{id}")
    protected ResponseEntity<DTO> get(@PathVariable("id") String id) {

        log.info(String.format("GET %s/%s", this.getBaseUrl(), id));

        DTO result = service.get(id);
        return ResponseEntity.ok(result);
    }

    protected abstract String getBaseUrl();
}
