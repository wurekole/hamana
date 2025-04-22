-- auto-generated definition
create role cassandra
    with superuser = true
    and login = true;


CREATE KEYSPACE ana_kan
    WITH replication = {
        'class': 'NetworkTopologyStrategy',
        'datacenter1': 3
        };
