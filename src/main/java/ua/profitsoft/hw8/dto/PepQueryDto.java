package ua.profitsoft.hw8.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PepQueryDto extends QueryDto {
    private String firstName;
    private String firstNameEn;
    private String patronymic;
    private String patronymicEn;
    private String lastName;
    private String lastNameEn;
}
