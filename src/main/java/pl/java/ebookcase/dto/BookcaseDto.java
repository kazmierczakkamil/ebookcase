package pl.java.ebookcase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookcaseDto {

    private Long id;
    private Long userId;
    private Set<Long> bookIds = new HashSet<>();
}
