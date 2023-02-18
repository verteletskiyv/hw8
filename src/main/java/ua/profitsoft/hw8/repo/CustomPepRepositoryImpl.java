package ua.profitsoft.hw8.repo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import ua.profitsoft.hw8.data.Pep;
import ua.profitsoft.hw8.dto.PepQueryDto;
import org.springframework.data.mongodb.core.query.Query;
import org.apache.commons.lang3.StringUtils;
import ua.profitsoft.hw8.dto.PepTopNamesDto;
import java.util.List;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
@RequiredArgsConstructor
public class CustomPepRepositoryImpl implements CustomPepRepository {
    private final MongoTemplate mongoTemplate;

    public List<PepTopNamesDto> findTopTenNames() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("is_pep").is(true)),
                Aggregation.group("first_name").count().as("count"),
                Aggregation.sort(Sort.Direction.DESC, "count"),
                Aggregation.limit(10),
                Aggregation.project("count").and("_id").as("name"));
        return mongoTemplate
                .aggregate(aggregation, "pep", PepTopNamesDto.class)
                .getMappedResults();
    }

    @Override
    public Page<Pep> search(PepQueryDto query) {
        PageRequest pageRequest = PageRequest.of(
                query.getPage(), query.getSize(),
                Sort.by(Sort.Direction.ASC, Pep.Fields.id));
        Query mongoQuery = new Query().with(pageRequest);

        if (StringUtils.isNotBlank(query.getFirstName()))
            mongoQuery.addCriteria(where(Pep.Fields.first_name).is(query.getFirstName()));
        if (StringUtils.isNotBlank(query.getFirstNameEn()))
            mongoQuery.addCriteria(where(Pep.Fields.first_name_en).is(query.getFirstNameEn()));
        if (StringUtils.isNotBlank(query.getPatronymic()))
            mongoQuery.addCriteria(where(Pep.Fields.patronymic).is(query.getPatronymic()));
        if (StringUtils.isNotBlank(query.getPatronymicEn()))
            mongoQuery.addCriteria(where(Pep.Fields.patronymic_en).is(query.getPatronymicEn()));
        if (StringUtils.isNotBlank(query.getLastName()))
            mongoQuery.addCriteria(where(Pep.Fields.last_name).is(query.getLastName()));
        if (StringUtils.isNotBlank(query.getLastNameEn()))
            mongoQuery.addCriteria(where(Pep.Fields.last_name_en).is(query.getLastNameEn()));

        final List<Pep> peps = mongoTemplate.find(mongoQuery, Pep.class);

        return PageableExecutionUtils.getPage(peps, pageRequest,
                () -> mongoTemplate.count((Query.of(mongoQuery).limit(-1).skip(-1)), Pep.class));
    }
}
