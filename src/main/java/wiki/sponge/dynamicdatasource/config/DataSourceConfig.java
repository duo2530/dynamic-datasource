package wiki.sponge.dynamicdatasource.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

import lombok.Data;

@Configuration
public class DataSourceConfig {

	@Bean
	@ConfigurationProperties("spring.datasource.master")
	public DataSourceProperties masterDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public DataSource masterDataSource() {
		return masterDataSourceProperties().initializeDataSourceBuilder()
				.type(HikariDataSource.class).build();
	}

	@Bean
	@ConfigurationProperties("spring.datasource.slave")
	public DataSourceProperties slaveDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public DataSource slaveDataSource() {
		return slaveDataSourceProperties().initializeDataSourceBuilder()
				.type(HikariDataSource.class).build();
	}

	@Bean
	@Primary
	public DataSourceRouter routingDataSource() {
		DataSourceRouter dataSourceRouter = new DataSourceRouter();
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(DataSourceConfigHolder.MASTER, masterDataSource());
		targetDataSources.put(DataSourceConfigHolder.SLAVE, slaveDataSource());
		dataSourceRouter.setTargetDataSources(targetDataSources);
		dataSourceRouter.setDefaultTargetDataSource(masterDataSource());

		return dataSourceRouter;
	}

	@Bean
	public PlatformTransactionManager platformTransactionManager() {
		return new DataSourceTransactionManager(routingDataSource());
	}
}
