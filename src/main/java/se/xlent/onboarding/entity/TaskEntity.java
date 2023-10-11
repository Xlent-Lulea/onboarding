package se.xlent.onboarding.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.xlent.onboarding.model.Task;
import se.xlent.onboarding.model.TaskType;


@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "TASK")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name="TYPE_ID")
    private TaskTypeEntity type;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "IS_COMPLETED")
    private boolean isCompleted;
    @Column(name = "URL")
    private String url;

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public TaskTypeEntity getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }

    public String getUrl() {
        return this.url;
    }

}