package gn.mariel.hamana.entity;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;


@Data
public class Tenant {
    @PrimaryKey
    private String id;
    private String SKU;
    private String name;
}
