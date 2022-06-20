package co.com.poli.taller1.taller1;

import co.com.poli.taller1.taller1.mapper.ProjectDTOtoProject;
import co.com.poli.taller1.taller1.persistence.entity.Project;
import co.com.poli.taller1.taller1.persistence.repository.ProjectRepository;
import co.com.poli.taller1.taller1.services.ProjectService;
import co.com.poli.taller1.taller1.services.ProjectServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class ProjectServiceMockTest {

    @Mock
    private ProjectRepository projectRepository;

    private ProjectService projectService;

    @Mock
    private ProjectDTOtoProject projectDTOtoProject;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);

        projectService = new ProjectServiceImpl(projectRepository, projectDTOtoProject);

        Project project = new Project();

        Date today = new Date();

        project.setProjectName("Test1");
        project.setProjectIdentifier("test1");
        project.setDescription("test1");
        project.setStartDate(today);
        project.setEndDate(today);

        Mockito.when(projectRepository.findByProjectIdentifier("test1"))
                .thenReturn(project);
    }

    @Test
    public void whenFindByProjectIdentifier_returnProject(){
        Project project = this.projectService.findByProjectIdentifier("test1");

        Assertions.assertThat(project.getProjectIdentifier()).isEqualTo("test1");

    }
}
