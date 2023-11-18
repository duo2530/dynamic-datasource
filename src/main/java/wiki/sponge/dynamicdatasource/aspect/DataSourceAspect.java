package wiki.sponge.dynamicdatasource.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import wiki.sponge.dynamicdatasource.config.DataSourceConfigHolder;

/**
 * 遇到的问题： 注解里面的参数值不知道要些什么，内容也不知道对不对
 */
@Aspect
public class DataSourceAspect {
	
	@Before(value = "execution(**(*))&&@Annotation(ReadOnly)")
	public void before(ReadOnly readOnly) {
		DataSourceConfigHolder.setDataSourceKey(DataSourceConfigHolder.SLAVE);
	}
	
	@After(value = "execution(**(*))&&@Annotation(ReadOnly)")
	public void after(ProceedingJoinPoint pj,String args) {
		DataSourceConfigHolder.removeDataSourceKey();
	}

}
