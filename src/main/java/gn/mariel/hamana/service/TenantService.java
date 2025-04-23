package gn.mariel.hamana.service;

import gn.mariel.hamana.configuration.MultiTenantSessionFactory;
import gn.mariel.hamana.dto.TenantInputDto;
import gn.mariel.hamana.entity.Tenant;
import gn.mariel.hamana.repository.TenantRepository;
import gn.mariel.hamana.utility.TenantUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.cassandra.core.ReactiveCassandraTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TenantService {

    private final MultiTenantSessionFactory multiTenantSessionFactory;
    private final TenantRepository tenantRepository;

/*     create a tenant
    do some studd*/
    public Mono<Tenant> createTenant(TenantInputDto dto) {
        String keyspace = "tenant_" + TenantUtil.createKeySpace(dto);

        String createKeyspaceQuery = String.format("""
        CREATE KEYSPACE IF NOT EXISTS %s 
        WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};
        """, keyspace);

        return Mono.defer(() -> {
            log.info("🚀 Starting tenant creation for keyspace: {}", keyspace);

            ReactiveCassandraTemplate template = multiTenantSessionFactory.getTemplate(keyspace);

            Tenant tenant = new Tenant();
            tenant.setId(UUID.randomUUID().toString());
            tenant.setName(dto.getName());

            return template.getReactiveCqlOperations()
                    .execute(createKeyspaceQuery)
                    .doOnSuccess(ok -> log.info("✅ Created keyspace: {}", keyspace))
                    .doOnError(err -> log.error("❌ Failed to create keyspace {}: {}", keyspace, err.getMessage()))

                    .flatMap(ignore -> {
                        ReactiveCassandraTemplate registryTemplate = multiTenantSessionFactory.getTemplate("tenants");

                        return registryTemplate.insert(tenant)
                                .doOnSuccess(t -> log.info("📝 Tenant '{}' registered successfully.", keyspace))
                                .doOnError(err -> log.error("❌ Failed to insert tenant registry entry: {}", err.getMessage()));
                    })

                    .onErrorResume(err -> {
                        log.error("💥 Tenant creation failed for '{}': {}", keyspace, err.toString());
                        return Mono.error(new IllegalStateException("Failed to create tenant: " + keyspace, err));
                    })

                    .thenReturn(tenant); // Return Mono<Tenant>
        });
    }
}
