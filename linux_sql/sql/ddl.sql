Drop DATABASE IF EXISTS host_agent;

CREATE DATABASE host_agent;
/*Creating host_info table if it doesn't exisit*/
CREATE TABLE IF NOT EXISTS host_info (
    id                  SERIAL NOT NULL PRIMARY KEY,
    hostname            VARCHAR NOT NULL UNIQUE,
    cpu_number          INTEGER NOT NULL,
    cpu_architecture    VARCHAR NOT NULL,
    cpu_model           VARCHAR NOT NULL,
    cpu_mhz             INTEGER NOT NULL,           
    L2_cache            INTEGER NOT NULL,   
    total_mem           INTEGER NOT NULL,
    timestamp           TIMESTAMP NOT NULL );
/*Creating host_usage table if it doesn't exisit*/
CREATE TABLE IF NOT EXISTS host_usage (
    timestamp          TIMESTAMP NOT NULL PRIMARY KEY,
    host_id            SERIAL NOT NULL REFERENCES host_info(id),
    memory_free        INTEGER NOT NULL,
    cpu_idle           INTEGER NOT NULL,
    cpu_kernel         INTEGER NOT NULL,
    disk_io            INTEGER NOT NULL,
    disk_available     INTEGER NOT NULL );