package wiki.sponge.dynamicdatasource.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.CollectionUtils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class DataSourceConfig {
	
	private Map<String,String> master;
	
	private List<Map<String,String>> slaves;
	
	@Bean
	public DataSource masterDataSource() throws Exception {
		if(CollectionUtils.isEmpty(master)) {
			throw new Exception("主库数据源不能为空");
		}
		return DruidDataSourceFactory.createDataSource(master);
	}
	
	@Bean
	public List<DataSource> slaveDataSources() throws Exception{
		if(CollectionUtils.isEmpty(slaves)) {
			throw new Exception("从库数据源不能为空");
		}
		final List<DataSource> dataSources = new ArrayList<>();
		for (Map<String, String> slave : slaves) {
			dataSources.add(DruidDataSourceFactory.createDataSource(slave));
		}
		return dataSources;
	}
	
	@Bean
	@Primary
	@DependsOn({"masterDataSource","slaveDataSources"})
	public DataSourceRouter routingDataSource() throws Exception {
		
		Map<Object,Object> hashMap = new HashMap<>(1+slaveDataSources().size());
		
		hashMap.put(DataSourceConfigHolder.MASTER, masterDataSource());
		
		for(int i=0;i < slaveDataSources().size();i++) {
			hashMap.put(DataSourceConfigHolder.SLAVE+i, slaveDataSources().get(i));			
		}
		DataSourceRouter dataSourceRouter = new DataSourceRouter();
		dataSourceRouter.setTargetDataSources(hashMap);
		dataSourceRouter.setDefaultTargetDataSource(masterDataSource());
		return dataSourceRouter;
	}
	
	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager() throws Exception {
		return new DataSourceTransactionManager(routingDataSource());
	}
	

}
