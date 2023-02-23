package ua.profitsoft.hw8.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PepQueryDto {

    @Min(value = 0, message = "Page number cannot be less than 0")
    private Integer page = 0;

    @Min(value = 1, message = "Page size cannot be less than 1")
    private Integer size = 10;

    private String firstName;
    private String firstNameEn;
    private String patronymic;
    private String patronymicEn;
    private String lastName;
    private String lastNameEn;
}
