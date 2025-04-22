package gn.mariel.hamana.repository;

import gn.mariel.hamana.entity.Tenant;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

public interface TenantRepository extends ReactiveCassandraRepository<Tenant, String> {
}
