package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    @Test
    public void testShowTask() throws Exception {
        // Создаем задачу в репозитории
        var task = new Task();
        task.setTitle(faker.lorem().sentence(3));
        task.setDescription(faker.lorem().sentence(5));
        task = taskRepository.save(task);

        // Выполняем GET-запрос для просмотра задачи
        var result = mockMvc.perform(get("/tasks/" + task.getId()))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        Task finalTask = task;
        assertThatJson(body).and(
                a -> a.node("id").isEqualTo(finalTask.getId()),
                a -> a.node("title").isEqualTo(finalTask.getTitle()),
                a -> a.node("description").isEqualTo(finalTask.getDescription())
        );
    }

    @Test
    public void testCreateTask() throws Exception {
        // Создаем данные для новой задачи
        var data = new HashMap<String, String>();
        data.put("title", faker.lorem().sentence(3));
        data.put("description", faker.lorem().sentence(5));

        // Отправляем POST-запрос
        var result = mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(data)))
                .andExpect(status().isCreated())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).and(
                a -> a.node("title").isEqualTo(data.get("title")),
                a -> a.node("description").isEqualTo(data.get("description"))
        );

        // Проверяем, что задача добавлена в репозиторий
        var createdTask = taskRepository.findAll().stream()
                .filter(t -> t.getTitle().equals(data.get("title")))
                .findFirst()
                .orElseThrow();
        assertThat(createdTask.getDescription()).isEqualTo(data.get("description"));
    }

    @Test
    public void testUpdateTask() throws Exception {
        // Создаем задачу в репозитории
        var task = new Task();
        task.setTitle(faker.lorem().sentence(3));
        task.setDescription(faker.lorem().sentence(5));
        task = taskRepository.save(task);

        // Обновляем задачу
        var data = new HashMap<String, String>();
        data.put("title", faker.lorem().sentence(4));
        data.put("description", faker.lorem().sentence(6));

        var result = mockMvc.perform(put("/tasks/" + task.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(data)))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).and(
                a -> a.node("title").isEqualTo(data.get("title")),
                a -> a.node("description").isEqualTo(data.get("description"))
        );

        // Проверяем обновление в репозитории
        var updatedTask = taskRepository.findById(task.getId()).orElseThrow();
        assertThat(updatedTask.getTitle()).isEqualTo(data.get("title"));
        assertThat(updatedTask.getDescription()).isEqualTo(data.get("description"));
    }

    @Test
    public void testDeleteTask() throws Exception {
        // Создаем задачу в репозитории
        var task = new Task();
        task.setTitle(faker.lorem().sentence(3));
        task.setDescription(faker.lorem().sentence(5));
        task = taskRepository.save(task);

        // Удаляем задачу
        mockMvc.perform(delete("/tasks/" + task.getId()))
                .andExpect(status().isOk());

        // Проверяем, что задача удалена из репозитория
        var exists = taskRepository.findById(task.getId()).isPresent();
        assertThat(exists).isFalse();
    }
    // END
}
