package ru.dev.litvinov.tasklist.backendspringboot.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dev.litvinov.tasklist.backendspringboot.entity.Category;
import ru.dev.litvinov.tasklist.backendspringboot.entity.Priority;

/**
 * @author Litvinov Alexey
 * @link http://litvinov.dev.ru
 */
@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
    List<Priority> findAllByOrderByIdAsc();

    @Query("SELECT c from Priority c where " +
        "(:title is null or :title='' or lower(c.title) like lower(concat('%', :title, '%'))) " +
        "order by c.title asc")
    List<Priority> findByTitle(@Param("title") String title);
}