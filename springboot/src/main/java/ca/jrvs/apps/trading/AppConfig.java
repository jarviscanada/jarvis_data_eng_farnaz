package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

@Configuration
@EnableTransactionManagement
public class AppConfig {

    private Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    public MarketDataConfig marketDataConfig() {
        MarketDataConfig marketDataConfig = new MarketDataConfig();
        marketDataConfig.setHost("https://cloud.iexapis.com/v1/");
        marketDataConfig.setToken(System.getenv("IEX_PUB_TOKEN"));

        return marketDataConfig;
    }

    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(50);
        cm.setDefaultMaxPerRoute(50);
        return cm;
    }

    //@Bean
    /*public DataSource dataSource() {
        String url = System.getenv("PSQL_URL");
        String user = System.getenv("PSQL_USER");
        String password = System.getenv("PSQL_PASSWORD");
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(password);
        return basicDataSource;*/
    @Bean
    public DataSource dataSource() {

        String jdbcUrl = new String();
        String user = null;
        String password = null;
        if (!StringUtils.isEmpty(System.getenv("RDS_HOSTNAME"))){
            //set up variables
        } else {
            jdbcUrl = System.getenv("PSQL_URL");
            user = System.getenv("PSQL_USER");
            password = System.getenv("PSQL_PASSWORD");
        }


        logger.debug("JDBC" + jdbcUrl);
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(jdbcUrl);
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(password);
        return basicDataSource;
    }
}

