package gn.mariel.hamana.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenInputDto {
    private String subject;
    private String issuer;
}
