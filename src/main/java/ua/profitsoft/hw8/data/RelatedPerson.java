package ua.profitsoft.hw8.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonAutoDetect
@ToString
@Getter @Setter
public class RelatedPerson {
    private String date_confirmed;
    private String date_established;
    private String date_finished;
    private Boolean is_pep;
    private String person_en;
    private String person_uk;
    private String relationship_type;
    private String relationship_type_en;
}
