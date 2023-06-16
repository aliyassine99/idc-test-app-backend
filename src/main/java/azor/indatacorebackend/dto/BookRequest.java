package azor.indatacorebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BookRequest {

    private String title;
    private String author;
    private String genre;
    private int height;
    private String publisher;
}
