package ua.profitsoft.hw8.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import ua.profitsoft.hw8.dto.*;
import ua.profitsoft.hw8.exception.InvalidFileTypeException;
import ua.profitsoft.hw8.service.PepService;
import ua.profitsoft.hw8.util.FileUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/api/pep")
public class PepController {
    private final PepService service;

    public PepController(PepService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public RestResponse upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) throw new FileNotFoundException();
        switch (Objects.requireNonNull(file.getContentType())) {
            case "application/json" -> service.upload(FileUtils.getFile(file).toFile());
            case "application/zip" -> service.upload(FileUtils.getUnzippedFile(file));
            default -> throw new InvalidFileTypeException();
        }
        return new RestResponse(file.getOriginalFilename()+", "+Math.floor(file.getSize()/1e+6)+"MB: Uploaded!");
    }

    @PostMapping("/_search")
    public PageDto<PepInfoDto> findPeps(@RequestBody PepQueryDto query) {
        return service.search(query);
    }

    @GetMapping("/top10")
    public List<PepTopNamesDto> getTopTen() {
        return service.getTopTenNames();
    }
}
