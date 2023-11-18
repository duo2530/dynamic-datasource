package wiki.sponge.dynamicdatasource.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import wiki.sponge.dynamicdatasource.annotation.ReadOnly;
import wiki.sponge.dynamicdatasource.config.DataSourceConfigHolder;

@Aspect
@Component
@Slf4j
public class DynamicDataSourceAspect {

	@Before(value = "execution(* *(..))&&@annotation(readOnly)")
	public void before(JoinPoint joinPoint, ReadOnly readOnly) throws Exception {
		log.info(joinPoint.getSignature().getName() + " from the slave");
		DataSourceConfigHolder.setDataSourceType(DataSourceConfigHolder.SLAVE);
	}

	@After(value = "execution(* *(..))&&@annotation(readOnly)")
	public void after(JoinPoint joinPoint, ReadOnly readOnly) {
		log.info(joinPoint.getSignature().getName() + " clear the datasource flag");
		DataSourceConfigHolder.removeDataSourceType();
	}

}
