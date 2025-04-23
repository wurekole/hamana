package gn.mariel.hamana.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TenantInputDto {
    private String uid;
    private String ou;
    private List<String> dc;
    private String name;
}
