package ua.profitsoft.hw8.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@JsonAutoDetect
@ToString
@Document("pep")
@Getter @Setter
@FieldNameConstants
public class Pep {
    private String id;
    private String also_known_as_en;
    private String also_known_as_uk;
    private String date_of_birth;
    private List<Declaration> declarations;
    private Boolean died;
    private String first_name;
    private String first_name_en;
    private String full_name;
    private String full_name_en;
    private Boolean is_pep;
    private String last_job_title;
    private String last_job_title_en;
    private String last_name;
    private String last_name_en;
    private String last_workplace;
    private String last_workplace_en;
    private String names;
    private String patronymic;
    private String patronymic_en;
    private String photo;
    private String reason_of_termination;
    private String reason_of_termination_en;
    private List<RelatedCompany> related_companies;
    private List<RelatedCountry> related_countries;
    private List<RelatedPerson> related_persons;
    private String reputation_assets_en;
    private String reputation_assets_uk;
    private String reputation_convictions_en;
    private String reputation_convictions_uk;
    private String reputation_crimes_en;
    private String reputation_crimes_uk;
    private String reputation_manhunt_en;
    private String reputation_manhunt_uk;
    private String reputation_sanctions_en;
    private String reputation_sanctions_uk;
    private String termination_date_human;
    private String type_of_official;
    private String type_of_official_en;
    private String url;
    private String wiki_en;
    private String wiki_uk;
    private String city_of_birth_uk;
    private String city_of_birth_en;
}