package co.com.poli.taller1.taller1.persistence.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "backlog")
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "project_identifier")
    private String projectIdentifier;

    @OneToOne
    private Project project;

    @JsonManagedReference
    @OneToMany(mappedBy = "backlog", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<ProjectTask> projectTask;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Backlog backlog = (Backlog) o;
        return id == backlog.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }
}
