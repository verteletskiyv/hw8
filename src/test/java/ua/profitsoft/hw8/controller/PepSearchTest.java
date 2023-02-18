package ua.profitsoft.hw8.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import ua.profitsoft.hw8.TestUtil;
import ua.profitsoft.hw8.dto.PageDto;
import ua.profitsoft.hw8.dto.PepInfoDto;
import ua.profitsoft.hw8.dto.PepQueryDto;
import ua.profitsoft.hw8.repo.PepRepository;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Tests {@link PepController#findPeps(PepQueryDto) method}.
 */
@SpringBootTest
public class PepSearchTest extends AbstractControllerTest {
    @Autowired
    PepRepository repository;

    @AfterEach
    void afterEach() {
        repository.deleteAll();
    }

    @Test
    void searchPepsByFirstNameAndLastNameTest() throws Exception {
        pushSomePeps();
        String body = """
                  {
                      "firstName":"%s",
                      "lastName":"%s",
                      "size":"10"
                  }
                """.formatted("Єгор", "Шроль");
        MockHttpServletResponse response = mvc.perform(post("/api/pep/_search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        PageDto<PepInfoDto> result = TestUtil.parseJson(response.getContentAsString(StandardCharsets.UTF_8), new TypeReference<>(){});
        assertThat(result.getList()).hasSize(1);
        assertThat(result.getList().get(0).getFull_name()).isEqualTo("Єгор Валентинович Шроль");
        assertThat(result.getList().get(0).getFull_name_en()).isEqualTo("Yehor Valentynovych Shrol");
        assertThat(result.getList().get(0).getDate_of_birth()).isEqualTo("06.06.2000");
        assertThat(result.getList().get(0).getDied()).isEqualTo(false);
        assertThat(result.getList().get(0).getUrl()).isEqualTo("https://pep.org.ua/p/gAAAAABgWvnRaCz0qOYF97buosh7VJGj2IiF_edZmGUjwBIx9w8DWOv0uo0qwU_XYusEPkeV3wYTVXGsPCnWSlMiTyTi_EvuEA==");
        assertThat(result.getTotalPages()).isEqualTo(1);
        assertThat(result.getTotalSize()).isEqualTo(1);
    }

    @Test
    void searchPepByPatronymicEnTest() throws Exception {
        pushSomePeps();
        String body = """
                  {
                      "patronymic_en":"%s",
                      "size":"5",
                      "page":"3"
                  }
                """.formatted("Yevhenovych");
        MockHttpServletResponse response = mvc.perform(post("/api/pep/_search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        PageDto<PepInfoDto> result = TestUtil.parseJson(response.getContentAsString(StandardCharsets.UTF_8), new TypeReference<>(){});
        assertThat(result.getList()).hasSize(5);
        assertThat(result.getTotalPages()).isEqualTo(6);
        assertThat(result.getTotalSize()).isEqualTo(27);
    }

    @Test
    void searchPepInvalidSize() throws Exception {
        pushSomePeps();
        String body = """
                  {
                      "patronymic_en":"%s",
                      "size":"0"
                  }
                """.formatted("Yevhenovych");
        Exception thrown = assertThrows(Exception.class, () ->
                mvc.perform(post("/api/pep/_search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)));
        assertThat(thrown.getMessage()).isNotNull();
        assertThat(thrown.getMessage().endsWith("Page size must not be less than one")).isTrue();
    }

    private void pushSomePeps() throws Exception {
        mvc.perform(multipart("/api/pep/upload").file(new MockMultipartFile(
                "file",
                "valid.json",
                "application/json",
                new FileInputStream("src/test/resources/json/valid.json"))));
    }

}
