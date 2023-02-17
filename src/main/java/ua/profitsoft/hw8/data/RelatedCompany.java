package ua.profitsoft.hw8.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonAutoDetect
@ToString
@Getter @Setter
public class RelatedCompany {
    private String date_confirmed;
    private String date_established;
    private String date_finished;
    private String relationship_type_en;
    private String relationship_type_uk;
    private String share;
    private String to_company_edrpou;
    private String to_company_en;
    private String to_company_founded;
    private Boolean to_company_is_state;
    private String to_company_short_en;
    private String to_company_short_uk;
    private String to_company_uk;
}
