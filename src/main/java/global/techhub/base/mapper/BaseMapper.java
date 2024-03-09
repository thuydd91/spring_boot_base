package global.techhub.base.mapper;

import global.techhub.base.dto.BaseDTO;
import global.techhub.base.entity.BaseEntity;

public interface BaseMapper<DTO extends BaseDTO, Entity extends BaseEntity> {

    Entity toEntity(DTO dto);

    DTO toDTO(Entity entity);
}
