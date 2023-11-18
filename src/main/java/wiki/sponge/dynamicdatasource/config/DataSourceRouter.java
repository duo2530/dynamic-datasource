package wiki.sponge.dynamicdatasource.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataSourceRouter extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		String dataSourceType = DataSourceConfigHolder.getDataSourceType();
		log.info("current datasource: {}",dataSourceType);
		return dataSourceType;
	}

}
