package pl.java.ebookcase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDto {

    private Long id;
    private String name;
    private Set<Long> bookIds = new HashSet<>();
}
