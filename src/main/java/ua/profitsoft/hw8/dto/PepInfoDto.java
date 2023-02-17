package ua.profitsoft.hw8.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class PepInfoDto {
    private String full_name;
    private String full_name_en;
    private String date_of_birth;
    private Boolean died;
    private String url;
}
