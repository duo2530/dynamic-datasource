package wiki.sponge.dynamicdatasource.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataSourceRouter extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		log.info("当前数据源为：{}",DataSourceConfigHolder.getDataSourceKey());
		return DataSourceConfigHolder.getDataSourceKey();
	}

}
