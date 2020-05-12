#!/bin/bash

#assigning CLI arguments to variables
psql_host=$1
psql_port=$2
db_name=$3 
psql_user=$4
psql_password=$5

export PGPASSWORD=${psql_password}

#saving the hostname to a variable
hostname=$(hostname -f)

#saving the number of CPU to a variable
lscpu_out=`lscpu`

#hardware
hostname=$(hostname -f) 
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs) 
cpu_architecture=$(echo "$lscpu_out" | egrep "Architecture:" | awk '{print $2}' | xargs) 
cpu_model=$($lscpu_out | egrep 'Model name:' | sed -n 's/^Model name: //p' | x)
cpu_mhz=$(echo "$lscpu_out" | grep "CPU MHz:" | sed -r 's/CPU MHz:\s{1,}//g') 
l2_cache=$(echo "$lscpu_out" | grep "L2 cache:" | sed -r 's/L2 cache:\s{1,}//g')
total_mem=$(grep MemTotal /proc/meminfo | awk '{print $2}')
timestamp=$(date +%F" "%T) 

insert_stmt=$(cat <<-END
INSERT INTO host_info (hostname,cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, total_mem, "timestamp") 
VALUES ('$hostname', $cpu_number, '$cpu_architecture', '$cpu_model', '$cpu_mhz', ${l2_cache%?}, '$total_mem', '$timestamp');
END
)

#connecting to psql instance 
psql -h $psql_host -p $psql_port -U $psql_user -d $db_name -c "$insert_stmt" 

exit 0