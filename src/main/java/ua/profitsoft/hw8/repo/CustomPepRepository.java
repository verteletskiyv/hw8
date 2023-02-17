package ua.profitsoft.hw8.repo;

import org.springframework.data.domain.Page;
import ua.profitsoft.hw8.data.Pep;
import ua.profitsoft.hw8.dto.PepQueryDto;
import ua.profitsoft.hw8.dto.PepTopNamesDto;

import java.util.List;

public interface CustomPepRepository {
     Page<Pep> search(PepQueryDto query);
     List<PepTopNamesDto> findTopTenNames();
}
