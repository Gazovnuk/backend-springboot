package ru.dev.litvinov.tasklist.backendspringboot.controller;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dev.litvinov.tasklist.backendspringboot.entity.Category;
import ru.dev.litvinov.tasklist.backendspringboot.entity.Priority;
import ru.dev.litvinov.tasklist.backendspringboot.repo.PriorityRepository;
import ru.dev.litvinov.tasklist.backendspringboot.search.CategorySearchValues;
import ru.dev.litvinov.tasklist.backendspringboot.search.PrioritySearchValues;

/**
 * @author Litvinov Alexey
 * @link http://litvinov.dev.ru
 */
@RestController
@RequestMapping("/priority")
public class PriorityController {
    private PriorityRepository priorityRepository;

    public PriorityController(final PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @GetMapping("/all")
    public List<Priority> findAll() {
        return priorityRepository.findAllByOrderByIdAsc();
    }

    @PostMapping("/add")
    public ResponseEntity<Priority> add(@RequestBody Priority priority) {
        if (priority.getId() != null && priority.getId() != 0) {
            return new ResponseEntity("redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityRepository.save(priority));
    }

    @PutMapping("update")
    public ResponseEntity<Priority> update(@RequestBody Priority priority) {
        if (priority.getId() == null || priority.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }
        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        if (priority.getColor() == null || priority.getColor().trim().length() == 0) {
            return new ResponseEntity("missed param: color", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(priorityRepository.save(priority));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Priority> findById(@PathVariable Long id) {
        Priority priority;
        try {
            priority = priorityRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(priority);
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        Priority priority;
        try {
            priorityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(id + " was deleted");
    }
    @PostMapping("/search")
    public ResponseEntity<List<Priority>> search(@RequestBody PrioritySearchValues prioritySearchValues) {
        return ResponseEntity.ok(priorityRepository.findByTitle(prioritySearchValues.getText()));
    }
}