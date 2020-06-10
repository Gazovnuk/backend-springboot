package ru.dev.litvinov.tasklist.backendspringboot.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dev.litvinov.tasklist.backendspringboot.entity.Category;

/**
 * @author Litvinov Alexey
 * @link http://litvinov.dev.ru
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByOrderByTitleAsc();

    @Query("SELECT c from Category c where " +
    "(:title is null or :title='' or lower(c.title) like lower(concat('%', :title, '%'))) " +
        "order by c.title asc")
    List<Category> findByTitle(@Param("title") String title);
}