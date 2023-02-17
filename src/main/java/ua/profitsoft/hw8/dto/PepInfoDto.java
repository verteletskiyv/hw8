package ua.profitsoft.hw8.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class PepDto {
    private String full_name;
    private String full_name_en;
    private String date_of_birth;
    private String photo;
    private String url;
    private String wiki_en;
    private String wiki_uk;
}
