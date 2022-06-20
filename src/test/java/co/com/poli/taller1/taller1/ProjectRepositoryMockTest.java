package co.com.poli.taller1.taller1;

import co.com.poli.taller1.taller1.persistence.entity.Project;
import co.com.poli.taller1.taller1.persistence.repository.ProjectRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class ProjectRepositoryMockTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void findAll_return_ListProject(){
        Project project = new Project();

        Date today = new Date();

        project.setProjectName("Test");
        project.setProjectIdentifier("test");
        project.setDescription("test");
        project.setStartDate(today);
        project.setEndDate(today);

        this.projectRepository.save(project);

        List<Project> projects = this.projectRepository.findAll();

        Assertions.assertThat(projects.size()).isEqualTo(1);
    }
}
