package ua.profitsoft.hw8.service;

import ua.profitsoft.hw8.dto.PageDto;
import ua.profitsoft.hw8.dto.PepInfoDto;
import ua.profitsoft.hw8.dto.PepQueryDto;
import ua.profitsoft.hw8.dto.PepTopNamesDto;
import java.io.File;
import java.util.List;

public interface PepService {
    void upload(File file);
    PageDto<PepInfoDto> search(PepQueryDto query);
    List<PepTopNamesDto> getTopTenNames();
}
