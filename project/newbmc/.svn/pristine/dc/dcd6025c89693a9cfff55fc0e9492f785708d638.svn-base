package egovframework.portal.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component("methodTracker")
public class MethodTracker {

	private static final Logger LOGGER = LogManager.getLogger();

	public Object print(ProceedingJoinPoint method) throws Throwable {
		LOGGER.error(method.getSignature());
		return method.proceed();
	}
}
