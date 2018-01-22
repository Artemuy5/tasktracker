package by.shapovalov.tasktracker.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 13.01.2018
 */

@Data
@Entity
@Table(name = "statuses")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String statusName;

    @OneToMany(mappedBy = "status")
    private List<Task> tasks;
}
