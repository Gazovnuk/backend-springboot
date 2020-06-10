package ru.dev.litvinov.tasklist.backendspringboot.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Litvinov Alexey
 * @link http://litvinov.dev.ru
 */
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class TaskSearchValues {
    private String title;
    private Integer completed;
    private Long priorityId;
    private Long categoryId;
    private Integer pageNumber;
    private Integer pageSize;
    private String sortColumn;
    private String sortDirection;

}
