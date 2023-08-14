package bagusadinugroho.restful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookRequest {

    @JsonIgnore
    @NotBlank
    private String id;

    @NotBlank
    @Size(max = 20)
    private String price;

    @NotBlank
    @Size(max = 100)
    private String title;

    @Size(max = 100)
    private String writter;
}
