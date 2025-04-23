package gn.mariel.hamana.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse {
    private int status;
    private String message;
    private Object result;
}
