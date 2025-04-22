package gn.mariel.hamana.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserAuthenticationRequest {
    @NotBlank(message = "principal is required.")
    private String principal;

    @NotBlank(message = "secret is required.")
    private String secret;
}
