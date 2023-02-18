package ua.profitsoft.hw8.dto;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class PepInfoDto {
    private String full_name;
    private String full_name_en;
    private String date_of_birth;
    private Boolean died;
    private String url;
}
