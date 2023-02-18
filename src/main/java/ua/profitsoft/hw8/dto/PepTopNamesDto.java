package ua.profitsoft.hw8.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode
public class PepTopNamesDto {
    private String name;
    private long count;
}
