package wiki.sponge.dynamicdatasource.config;

/**
 * 遇到的问题，写的不清楚，getDataSourceKey里面不知道写什么
 */
public class DataSourceConfigHolder {
	
	public static String MASTER="MASTER";
	
	public static String SLAVE="SLAVE";
	
	public static final ThreadLocal<String> ss=new ThreadLocal<>();
	
	public static void setDataSourceKey(String key) {
		ss.set(key);
	}
	
	public static String getDataSourceKey() {
		return null;
	}
	
	public static void removeDataSourceKey() {
		ss.remove();
	}
}
