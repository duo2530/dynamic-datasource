package wiki.sponge.dynamicdatasource.config;

import lombok.extern.slf4j.Slf4j;

/**
 * version 1:遇到的问题，写的不清楚，getDataSourceKey里面不知道写什么
 */
@Slf4j
public class DataSourceConfigHolder {

	public static String MASTER = "MASTER";

	public static String SLAVE = "SLAVE";

	public static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

	public static void setDataSourceKey(String key) throws Exception {
		if (key == null) {
			throw new Exception("数据源key不能为空");
		}
		log.info("设置数据源为:{}",key);
		CONTEXT_HOLDER.set(key);
	}

	public static String getDataSourceKey() {
		return null == CONTEXT_HOLDER.get() ? MASTER : CONTEXT_HOLDER.get();
	}

	public static void removeDataSourceKey() {
		CONTEXT_HOLDER.remove();
	}
}
