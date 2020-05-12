/* Group hosts by CPU number and sort by their memory size in descending order */
SELECT 
  cpu_number, 
  id AS "host_id", 
  total_mem 
FROM 
  host_info 
GROUP BY 
  cpu_number, 
  host_id 
ORDER BY 
  cpu_number ASC, 
  total_mem DESC;
  /*Average used memory in percentage over 5 mins interval for each host. */
  select
	host_usage.host_id AS id_host,
	host_info.hostname AS host, 
	host_info.total_mem AS total_mem,
    (
		avg(
			(host_info.total_mem - host_usage.memory_free)/host_info.total_mem
		) * 100
	) as avg_mem_used
from 
	host_usage
	inner join host_info on host_usage.host_id = host_usage.host_id
group by 
	host_usage.host_id, 
	host_info.hostname, 
	host_info.total_mem,
	date_trunc('hour', host_usage.timestamp) + INTERVAL '5 minutes' * round(
		DATE_PART('minutes', host_usage.timestamp) / 5.0
	)
order by host_usage.host_id;
