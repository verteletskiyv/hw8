package ua.profitsoft.hw8.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonAutoDetect
@ToString
@Getter @Setter
public class RelatedCountry {
    private String date_confirmed;
    private String date_established;
    private String date_finished;
    private String relationship_type;
    private String to_country_en;
    private String to_country_uk;
}
