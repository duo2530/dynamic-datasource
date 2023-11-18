package wiki.sponge.dynamicdatasource.config;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataSourceConfigHolder {

	public static final String MASTER = "MASTER";

	public static final String SLAVE = "SLAVE";

	private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

	public static void setDataSourceType(String dataSourceType) throws Exception {
		if (dataSourceType == null) {
			log.error("dataSourceType cannot be null");
			throw new Exception("dataSourceType is null");
		}
		CONTEXT_HOLDER.set(dataSourceType);
	}

	public static String getDataSourceType() {
		return null == CONTEXT_HOLDER.get() ? MASTER : CONTEXT_HOLDER.get();
	}

	public static void removeDataSourceType() {
		CONTEXT_HOLDER.remove();
	}
}
