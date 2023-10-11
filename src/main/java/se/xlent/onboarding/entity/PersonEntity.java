package se.xlent.onboarding.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "PERSON")
public class PersonEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name="NAME")
    private String name;
    @Column(name="EMAIL")
    private String email;

    @Column(name="IS_ACTIVE")
    private boolean isActive;

    public long getId() {
        return this.id;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setIsActive(boolean value) {
        this.isActive = value;
    }
}


