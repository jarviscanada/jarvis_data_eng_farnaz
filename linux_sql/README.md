# Linux Cluster Monitoring Agent

## Introduction
Cluster Monitor Agent is a real-time internal tool that helps Linux Cluster Administration (LCA) team to collect and record the hardware specifications and node resource usages, such as CPU and memory information. The collected data is stored in a locally hosted PostgreSQL database and can be used for generating technical report. <br />
Based on this generated report, better troubleshooting or resource planning would be performed by infrastructure team.

## Architecture and Design
The diagram below illustrates the architecture design of three nodes: <br />
![my image](./assets/Design.png =200x)

- The `postgreSQL` instance is used to persist all the data <br />
- The `Bash Scripts`, which is installed inside each node is consisted of two scripts <br />
    - `host_info.sh` collects the host hardware info and insert it into the database. It will be run only once at the installation time. <br />
    - `host_usage.sh` collects the current host usage (CPU and Memory) and then insert into the database. It will be triggered by the `crontab` job every minute. <br />

## Usage

psql_docker.sh will set up the PostgreSQL database by using docker and acts as a switch to start/stop the psql instance.
### Starting psql docker instance
```./scripts/psql_docker.sh start [db_passwd]```
### Stopping psql docker instance
```./scripts/psql_docker.sh stop```

ddl.sql will set up the database and creates host_info and host_usage tables
### Creating tables
```psql -h localhost -U postgres -W -f sql/ddl.sql``` 

### Collecting Server Data and Inserting into tables
```bash -x ./scripts/host_info.sh localhost 5432 host_agent postgres password```
```bash -x ./scripts/host_usage.sh localhost 5432 host_agent postgres password```

Since the host_usage.sh should run every minute, crontab should be executed.
### Forcing host_usage to be executed once in a minute using crontab
```crontab -e```
```* * * * * * bash [server's local pathway]/host_usage.sh localhost 5432 host_agent postgres password > /tmp/host_usage.log```

queries.sql consists of two business question examples regarding hardware info and memory usage
### Executing .sql file using psql CLI tool
```psql -h localhost -U postgres -d host_agent -f sql/queries.sql```

## Improvements 
1) Handle hardware update 
2) Create weekly memory usage as well
3) Autoremove the previous months server data in order to have sufficient number of rows in host_usage table
