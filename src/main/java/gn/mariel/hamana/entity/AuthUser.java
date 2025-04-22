package gn.mariel.hamana.entity;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("users")
@Data
public class AuthUser {

    @PrimaryKey
    private Integer id;
    private String sku;
    private String email;
    private String secret;
}
