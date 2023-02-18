package ua.profitsoft.hw8.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import ua.profitsoft.hw8.TestUtil;
import ua.profitsoft.hw8.dto.RestResponse;
import java.io.File;
import java.io.FileInputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * Tests {@link PepController#upload(MultipartFile) method}.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class PepUploadTest extends AbstractControllerTest {

    @Test
    void uploadZipFileSuccessTest() throws Exception {
        File zip = new File("src/test/resources/zip/valid.zip");
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "valid.zip",
                "application/zip",
                new FileInputStream(zip));
        mvc.perform(multipart("/api/pep/upload").file(file))
                .andExpect(status().isOk());
    }

    @Test
    void uploadZipFileInvalidProperties() throws Exception {
        File zip = new File("src/test/resources/zip/invalidProps.zip");
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "invalidProps.zip",
                "application/zip",
                new FileInputStream(zip));
        MockHttpServletResponse response = mvc.perform(multipart("/api/pep/upload").file(file))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse();
        RestResponse res = TestUtil.parseJson(response.getContentAsString(), new TypeReference<>() {});
        assertThat(res.getResult()).isEqualTo("File reading failure: Invalid JSON");
    }

    @Test
    void uploadJsonFileSuccessTest() throws Exception {
        File json = new File("src/test/resources/json/valid.json");
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "valid.json",
                "application/json",
                new FileInputStream(json));
        mvc.perform(multipart("/api/pep/upload").file(file))
                .andExpect(status().isOk());
    }

    @Test
    void uploadJsonFileInvalidProperties() throws Exception {
        File json = new File("src/test/resources/json/invalidProps.json");
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "invalidProps.json",
                "application/json",
                new FileInputStream(json));
        MockHttpServletResponse response = mvc.perform(multipart("/api/pep/upload").file(file))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse();
        RestResponse res = TestUtil.parseJson(response.getContentAsString(), new TypeReference<>() {});
        assertThat(res.getResult()).isEqualTo("File reading failure: Invalid JSON");
    }
}
