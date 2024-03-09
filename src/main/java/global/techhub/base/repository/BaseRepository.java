package global.techhub.base.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    void deleteById(ID id);

    Page<T> findByEnabledIsTrue(Pageable pageable);

    List<T> findByEnabledIsTrue();
}
