package ru.dev.litvinov.tasklist.backendspringboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dev.litvinov.tasklist.backendspringboot.entity.Priority;

/**
 * @author Litvinov Alexey
 * @link http://litvinov.dev.ru
 */
@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {


}