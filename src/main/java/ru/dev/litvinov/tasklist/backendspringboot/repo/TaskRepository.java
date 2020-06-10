package ru.dev.litvinov.tasklist.backendspringboot.repo;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dev.litvinov.tasklist.backendspringboot.entity.Task;

/**
 * @author Litvinov Alexey
 * @link http://litvinov.dev.ru
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByOrderByTitleAsc();

    @Query("SELECT p from Task p where " +
        "(:title is null or :title = '' or lower(p.title) like lower(concat('%', :title, '%'))) and" +
        "(:completed is null or p.completed=:completed) and" +
        "(:priorityId is null or p.priority.id=:priorityId) and" +
        "(:categoryId is null or p.category.id=:categoryId)"
    )
    Page<Task> findByParams(@Param("title") String title,
                            @Param("completed") Integer completed,
                            @Param("priorityId") Long priorityId,
                            @Param("categoryId") Long categoryId,
                            Pageable pageable);
}