package ua.profitsoft.hw8.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.function.Function;

@Getter
@Builder
@Jacksonized
public class PageDto<T> {

    private List<T> list;

    private long totalPages;

    private long totalSize;

    public static <T, D> PageDto<D> fromPage(Page<T> page, Function<T, D> convertFunction) {
        List<D> list = page.stream()
                .map(convertFunction)
                .toList();

        return PageDto.<D>builder()
                .list(list)
                .totalPages(page.getTotalPages())
                .totalSize(page.getTotalElements())
                .build();
    }

}
