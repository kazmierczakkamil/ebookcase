package pl.java.ebookcase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto {

    private Long id;
    private String title;
    private int amountOfPages;
    private Long categoryId;
    private Long authorId;
}
