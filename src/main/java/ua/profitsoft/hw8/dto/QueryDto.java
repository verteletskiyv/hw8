package ua.profitsoft.hw8.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class QueryDto {
    @Min(0)
    private Integer page = 0;

    @Min(1)
    private Integer size = 10;

}
