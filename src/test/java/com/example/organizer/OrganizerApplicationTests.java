package com.example.organizer;

import com.example.organizer.model.Project;
import com.example.organizer.model.Title;
import com.example.organizer.model.Worker;
import com.example.organizer.repository.ProjectRepository;
import com.example.organizer.repository.WorkerRepository;
import com.example.organizer.service.ProjectService;
import com.example.organizer.service.WorkerService;
import com.example.organizer.viewmodel.ProjectResponse;
import com.example.organizer.viewmodel.WorkerResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrganizerApplicationTests {

    @Autowired
    private WorkerService workerService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WorkerRepository workerRepository;
    @MockBean
    private ProjectRepository projectRepository;

    @Test
    public void addWorker() {

        Worker worker = new Worker(Title.HR, "Alex6", "Papa", 33);

        Mockito.when(workerRepository.save(any()))
                .thenReturn(new Worker(Title.HR, "Alex6", "Papa", 33));

        WorkerResponse workerResponse = workerService.createUpdateWorker(worker);
        Worker dbWorker = workerResponse.getWorker();

        Assertions.assertEquals("Successful", workerResponse.getMsg());
        Assertions.assertEquals(worker, dbWorker);

        verify(workerRepository, times(1)).save(worker);
        verify(workerRepository, times(1))
                .findByFirstNameAndSecondName(worker.getFirstName(), worker.getSecondName());
    }

    @Test
    public void addProject() {

        Project project = new Project("Project one", new Date(2020, 3, 13), new Date(2020, 4, 23));
        Mockito.when(projectRepository.save(any()))
                .thenReturn(new Project("Project one", new Date(2020, 3, 13), new Date(2020, 4, 23)));

        ProjectResponse projectResponse = projectService.createProject(project);
        Project dbProject = projectResponse.getProject();

        Assertions.assertEquals("Project created", projectResponse.getMsg());
        Assertions.assertEquals(project, dbProject);

        verify(projectRepository, times(1)).save(project);
        verify(projectRepository, times(1))
                .findByName(project.getName());
    }

    @Test
    public void getWorkerList() throws Exception {
        Worker alex = new Worker(Title.HR, "Alex6", "Papa", 43);
        Worker alex2 = new Worker(Title.CODER, "Alex2", "Papa2", 44);

        List<Worker> allWorkers = Arrays.asList(alex, alex2);

        given(workerService.findAll()).willReturn(allWorkers);

        mockMvc.perform(get("/worker")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", is(alex.getFirstName())))
                .andExpect(jsonPath("$[1].firstName", is(alex2.getFirstName())))
                .andExpect(jsonPath("$[0].title", is(alex.getTitle().name())));
    }
}
