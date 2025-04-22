package gn.mariel.hamana.model;

import lombok.Data;

@Data
public class ApiResponse {
    private int status;
    private String message;
    private Object result;
}
