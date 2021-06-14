package ru.itis.restsemestrovka.services;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import ru.itis.restsemestrovka.models.*;
import ru.itis.restsemestrovka.repositories.*;

@Service
public class MethodCounterService {
	@Autowired private MethodCounterRepository counterRepository;

	public void increment(String className, String methodName) {
		var counterKey = new MethodCounter.Key(className, methodName);
		var counterResult = counterRepository.findById(counterKey);

		var counter = counterResult.orElse(new MethodCounter(counterKey, 0));
		counter.setCount(counter.getCount() + 1);

		counterRepository.save(counter);
	}
}
