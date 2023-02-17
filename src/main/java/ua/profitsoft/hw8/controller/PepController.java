package ua.profitsoft.hw8.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.profitsoft.hw8.dto.*;
import ua.profitsoft.hw8.service.PepService;
import ua.profitsoft.hw8.util.FileUtils;
import java.util.List;


@RestController
@RequestMapping("/api/pep")
public class PepController {
    private final PepService service;

    public PepController(PepService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public RestResponse uploadZipFile(@RequestParam("file") MultipartFile file) {
        service.upload(FileUtils.unzipFile(file));
        return new RestResponse(file.getName()+", "+Math.floor(file.getSize()/1e+6)+"MB: Uploaded!");

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
