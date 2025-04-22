package gn.mariel.hamana.configuration;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CassandraSessionFactory {

    private final Map<String, CqlSession> tenantSessions = new ConcurrentHashMap<>();

    public CqlSession getSessionForTenant(String tenantKeyspace) {
        return tenantSessions.computeIfAbsent(tenantKeyspace, this::createSession);
    }

    private CqlSession createSession(String keyspace) {
        return CqlSession.builder()
                .withKeyspace(CqlIdentifier.fromCql(keyspace))
                .withLocalDatacenter("datacenter1")
                .addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
                .build();
    }
}