package patienthub.demo.centralLogging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingForPatientApp {
    private static final Logger logger = LogManager.getLogger("Central Logging");
    @Pointcut("execution(* patienthub.demo.*.*.*(..))")
    private void logMethod(){
        // Just a placeholder method for this reusable pointcut expression.
    }
    @Before("logMethod()")
    public void beforeMethodExecution(JoinPoint joinPoint){
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        StringBuilder params = new StringBuilder();
        for (Object arg : args) {
            if (params.length() > 0) {
                params.append(", ");
            }
            params.append(arg.toString());
        }
        logger.info("Executing method: " + methodName + " in class: " + className + " with parameters: " + params);
    }
}
