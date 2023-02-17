package ua.profitsoft.hw8.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonAutoDetect
@ToString
@Getter @Setter
public class Declaration {
    private Double family_income;
    private Double income;
    private String office_en;
    private String office_uk;
    private String position_en;
    private String position_uk;
    private String region_en;
    private String region_uk;
    private String url;
    private String year;
}
