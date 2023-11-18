package wiki.sponge.dynamicdatasource.aspect;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import wiki.sponge.dynamicdatasource.annotation.ReadOnly;
import wiki.sponge.dynamicdatasource.config.DataSourceConfigHolder;

/**
 * 遇到的问题： 注解里面的参数值不知道要些什么，内容也不知道对不对
 */
@Aspect
@Component
@Slf4j
public class DynamicDataSourceAspect {

	@Before(value = "execution(* *(..))&&@annotation(readOnly)")
	public void before(Joinpoint joinPoint, ReadOnly readOnly) throws Exception {
		log.info("切换数据源");
		DataSourceConfigHolder.setDataSourceKey(DataSourceConfigHolder.SLAVE);
	}

	@After(value = "execution(* *(..))&&@annotation(readOnly)")
	public void after(JoinPoint joinPoint, String args) {
		log.info("移除数据源");
		DataSourceConfigHolder.removeDataSourceKey();
	}

}
