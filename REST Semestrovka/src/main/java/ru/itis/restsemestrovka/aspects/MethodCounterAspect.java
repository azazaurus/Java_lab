package ru.itis.restsemestrovka.aspects;

import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import ru.itis.restsemestrovka.services.*;

@Component
@Aspect
public class MethodCounterAspect {
	@Autowired private MethodCounterService counterService;

	@Before("within(ru.itis.restsemestrovka.controllers.*)")
	public void incrementMethodCounter(JoinPoint joinPoint) {
		var methodSignature = joinPoint.getSignature();
		var controllerName = methodSignature.getDeclaringType().getSimpleName();
		var methodName = methodSignature.getName();

		counterService.increment(controllerName, methodName);
	}
}
