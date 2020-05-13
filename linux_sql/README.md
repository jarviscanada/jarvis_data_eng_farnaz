# Linux Cluster Monitoring Agent

## Introduction
Cluster Monitor Agent is a real-time internal tool that helps Linux Cluster Administration (LCA) team to collect and record the hardware specifications and node resource usages, such as CPU and memory information. The collected data is stored in a locally hosted PostgreSQL database and can be used for generating technical report. <br />
Based on this generated report, better troubleshooting or resource planning would be performed by infrastructure team.

## Architecture and Design
The diagram below illustrates the architecture design of three nodes: <br /> <br /> 
<img src="./assets/Design.png" width="550">

#### Scripts
The `Bash Scripts`, which is installed inside each node is consisted of two scripts: <br /> <br />
    - `host_info.sh` collects the host hardware info and insert it into the database. It will be run only once at the installation time. <br />
    - `host_usage.sh` collects the current host usage (CPU and Memory) and then insert into the database. It will be triggered by the `crontab` job every minute. <br />

### Database
The `postgreSQL` instance is used to persist all the data and consists of two tables: <br />  <br /> 

Name | Description | Data Columns
------------ | ------------- | -----------------------------
host_info | Hardware Specifications Data| id,hostname,cpu_number,cpu_architecture,cpu_model,cpu_mhz,L2_cache,timestamp
host_usage | Linux Resource Usage Data | timestamp,host_id,memory_free,cpu_idle,cpu_kernel,disk_io,disk_available


## Usage

### Starting or stopping psql docker instance
psql_docker.sh will set up the PostgreSQL database by using docker and acts as a switch to start/stop the psql instance. <br /> <br /> 
```./scripts/psql_docker.sh [start|stop] [db_passwd]```

### Creating tables
ddl.sql will set up the database and creates host_info and host_usage tables <br /> <br />
```psql -h localhost -U postgres -W -f sql/ddl.sql``` 

### Collecting Server Data and Inserting into tables
```bash -x ./[host_info.sh path way] localhost 5432 host_agent postgres password``` <br /> <br />
```bash -x ./[host_usage.sh path way] localhost 5432 host_agent postgres password```

### Forcing host_usage to be triggered once in a minute using crontab
Editing crontab jobs: <br /> <br /> 
```crontab -e``` <br /> <br /> 
Since the host_usage.sh should run every minute, this line should be added to crontab: <br /> <br /> 
```* * * * * * bash [server's local pathway]/host_usage.sh localhost 5432 host_agent postgres password > /tmp/host_usage.log```

### Verifying the results
The command line to connect to psql instance: <br /> <br /> 
```psql -h localhost -U postgres host_agent``` <br /> <br /> 
Connecting to the host_agent database: <br /> <br /> 
```\c host_agent;``` <br /> <br /> 
Viewing the table: <br /> <br /> 
```select * from [host_info|host_usage];```

### Executing .sql file using psql CLI tool
queries.sql consists of two business question examples regarding hardware info and memory usage <br /> <br /> 
```psql -h localhost -U postgres -d host_agent -f sql/queries.sql```

## Improvements 
1) Handle hardware update 
2) Create weekly memory usage as well
3) Autoremove the previous months server data in order to have sufficient number of rows in host_usage table
