package gn.mariel.hamana.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class AddTenantRequest {
    @NotBlank(message = "principal is required.")
    private String principal;

    @NotBlank(message = "secret is required.")
    private String secret;

    private String ou;
    private List<String> dc;

    private String name;
}
