#! /bin/bash

#assigning CLI arguments to variables
psql_host=$1
psql_port=$2 
db_name=$3
psql_user=$4
psql_password=$5

export PGPASSWORD=${psql_password}

if [ "$#" -ne 5 ]; then
    echo "Illegal number of parameters"
    exit 1
fi

#usage
hostname=$(hostname -f) 
timestamp=$(date -u '+%Y-%m-%d %H:%M:%S')
memory_free=$( vmstat | egrep -v 'swap|free' | awk '{ print $4 }')
cpu_idle=$( vmstat | egrep -v 'swap|id' | awk '{ print $15 }')
cpu_kernel=$( vmstat | egrep -v 'swap|sy' | awk '{ print $14 }')
disk_io=$(vmstat -d | egrep -v 'swap|cur' | awk '{ print $10 }' | xargs)
disk_available=$(df -BM / | awk '{print $4}' | sed 's/[A-Za-z]*//g' | xargs)

insert_stmt=$(cat <<-END
INSERT INTO host_usage ("timestamp", host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available) 
VALUES ('$timestamp',(SELECT id FROM host_info WHERE hostname='$(hostname -f)'), $memory_free, '$cpu_idle', '$cpu_kernel', $disk_io, $disk_available);
END
)

#connecting to psql instance
psql -h $psql_host -p $psql_port -U $psql_user -d $db_name -c "$insert_stmt" 

exit 0