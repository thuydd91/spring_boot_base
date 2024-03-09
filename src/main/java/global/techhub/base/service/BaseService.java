package global.techhub.base.service;

import global.techhub.base.exception.NotFoundException;
import global.techhub.base.mapper.BaseMapper;
import global.techhub.base.repository.BaseRepository;
import org.springframework.data.domain.PageRequest;
import global.techhub.base.dto.BaseDTO;
import global.techhub.base.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.*;

public abstract class BaseService<DTO extends BaseDTO, Entity extends BaseEntity> {

    protected BaseRepository<Entity, String> repository;
    protected BaseMapper<DTO, Entity> mapper;

    public DTO create(DTO dto) {
        Entity savedEntity = repository.save(mapper.toEntity(dto));
        return mapper.toDTO(savedEntity);
    }

    public DTO update(String id, DTO dto) {
        Optional<Entity> optionalEntity = repository.findById(id);

        return optionalEntity.map(entity -> {
            entity = mapper.toEntity(dto);
            Entity savedEntity = repository.save(entity);
            return mapper.toDTO(savedEntity);
        }).orElseThrow(NotFoundException::new);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public void disable(String id) {
        Optional<Entity> optionalEntity = repository.findById(id);

        if (optionalEntity.isPresent()) {
            Entity entity = optionalEntity.get();
            entity.setEnabled(false);
            repository.save(entity);
        } else {
            throw new NotFoundException();
        }
    }

    public List<DTO> getAll(int page, int size) {
        return repository.findByEnabledIsTrue(PageRequest.of(page, size))
                .stream()
                .map(mapper::toDTO)
                .collect(toList());
    }

    public List<DTO> getAll() {
        return repository.findByEnabledIsTrue()
                .parallelStream()
                .map(mapper::toDTO)
                .collect(toList());
    }

    public DTO get(String id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(NotFoundException::new);
    }
}
