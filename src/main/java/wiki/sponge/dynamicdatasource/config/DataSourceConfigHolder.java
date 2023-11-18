package wiki.sponge.dynamicdatasource.config;

import lombok.extern.slf4j.Slf4j;

/**
 * version 1:遇到的问题，写的不清楚，getDataSourceKey里面不知道写什么
 */
@Slf4j
public class DataSourceConfigHolder {
	
	public static String MASTER="MASTER";
	
	public static String SLAVE="SLAVE";
	
	public static final ThreadLocal<String> ss=new ThreadLocal<>();
	
	public static void setDataSourceKey(String key) throws Exception {
		if(key==null) {
			throw new Exception("数据源key不能为空");
		}
		ss.set(key);
	}
	
	public static String getDataSourceKey() {
		return null==ss.get()?MASTER:ss.get();
	}
	
	public static void removeDataSourceKey() {
		ss.remove();
	}
}
