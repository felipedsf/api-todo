package com.felipe.todo.service;

import com.felipe.todo.domain.TaskList;
import com.felipe.todo.repository.TaskListRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

//@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TaskListServiceImplTest {

    @InjectMocks
    TaskListServiceImpl taskListService;

    @Mock
    TaskListRepository taskListRepository;

    @Before
    public void setup() {
        when(taskListRepository.findAll()).thenReturn(new ArrayList<>());
        taskListService = new TaskListServiceImpl(taskListRepository);
    }

    @Test
    public void saveOrUpdate() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void listAll() {
        List<TaskList> taskLists = taskListService.listAll();
        assertThat(taskLists.size(), Matchers.is(0));
    }

    @Test
    public void deleteById() {
    }
}