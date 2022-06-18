package co.com.poli.taller1.taller1.persistence.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

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


}
