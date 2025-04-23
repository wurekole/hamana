package gn.mariel.hamana.configuration;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.data.cassandra.ReactiveSession;
import org.springframework.data.cassandra.ReactiveSessionFactory;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.ReactiveCassandraTemplate;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.cql.ReactiveCqlTemplate;
import org.springframework.data.cassandra.core.cql.session.DefaultBridgedReactiveSession;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class MultiTenantSessionFactory {
    private final CassandraProperties cassandraProperties;

    private final Map<String, ReactiveCassandraTemplate> tenantTemplates = new ConcurrentHashMap<>();


    public ReactiveCassandraTemplate getTemplate(String keyspace) {
        // Create the base CqlSession
        CqlSession session = CqlSession.builder()
                .withKeyspace(CqlIdentifier.fromCql(keyspace))
                .withLocalDatacenter(cassandraProperties.getLocalDatacenter())
                .addContactPoint(new InetSocketAddress(cassandraProperties.getLocalDatacenter(), cassandraProperties.getPort()))
                .build();

        // Wrap it into Spring's ReactiveSession
        ReactiveSession reactiveSession = new DefaultBridgedReactiveSession(session);

        // Prepare Spring Data Cassandra support
        CassandraConverter converter = new MappingCassandraConverter(new CassandraMappingContext());
        return new ReactiveCassandraTemplate(reactiveSession, converter);
    }
}