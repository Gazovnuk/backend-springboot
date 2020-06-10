package ru.dev.litvinov.tasklist.backendspringboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dev.litvinov.tasklist.backendspringboot.entity.Category;

/**
 * @author Litvinov Alexey
 * @link http://litvinov.dev.ru
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}