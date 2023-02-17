package ua.profitsoft.hw8.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ua.profitsoft.hw8.data.Pep;
import ua.profitsoft.hw8.dto.PageDto;
import ua.profitsoft.hw8.dto.PepInfoDto;
import ua.profitsoft.hw8.dto.PepQueryDto;
import ua.profitsoft.hw8.dto.PepTopNamesDto;
import ua.profitsoft.hw8.repo.PepRepository;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PepService {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private final PepRepository repository;

    public void upload(File file) {
        repository.deleteAll();
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             JsonParser jsonParser = JSON_MAPPER.getFactory().createParser(reader)) {
            if (jsonParser.nextToken() != JsonToken.START_ARRAY)
                throw new IllegalArgumentException("Expected content to be an array");
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                Pep pep = JSON_MAPPER.readValue(jsonParser, Pep.class);
                pep.setId(UUID.randomUUID().toString());
                repository.save(pep);
            }
        } catch (IOException e) {
            throw new RuntimeException("File reading failure: " + e.getMessage());
        }
        file.delete();
    }

    public PageDto<PepInfoDto> search(PepQueryDto query) {
        Page<Pep> page = repository.search(query);
        return PageDto.fromPage(page, this::toPepDto);
    }

    public List<PepTopNamesDto> getTopTenNames() {
        return repository.findTopTenNames();
    }

    private PepInfoDto toPepDto(Pep pep) {
        return PepInfoDto.builder()
                .full_name(pep.getFull_name())
                .full_name_en(pep.getFull_name_en())
                .date_of_birth(pep.getDate_of_birth())
                .died(pep.getDied())
                .url(pep.getUrl())
                .build();
    }
}
