package ua.profitsoft.hw8.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import ua.profitsoft.hw8.TestUtil;
import ua.profitsoft.hw8.dto.PepTopNamesDto;
import ua.profitsoft.hw8.repo.PepRepository;
import java.io.FileInputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Tests {@link PepController#getTopTen() method}.
 */
@SpringBootTest
public class PepTopTenNamesTest extends AbstractControllerTest{
    @Autowired
    PepRepository repository;

    @Test
    void getTop10NamesTest() throws Exception {
        mvc.perform(multipart("/api/pep/upload").file(new MockMultipartFile(
                "file",
                "valid.json",
                "application/json",
                new FileInputStream("src/test/resources/json/valid.json"))));
        MvcResult mvcResult = mvc.perform(get("/api/pep/top10"))
                .andExpect(status().isOk())
                .andReturn();
        List<PepTopNamesDto> top = TestUtil.parseJsonArray(mvcResult);
        assertThat(top).isNotEmpty();
        assertThat(top).doesNotHaveDuplicates();
        assertThat(top).hasSize(10);
    }
}
