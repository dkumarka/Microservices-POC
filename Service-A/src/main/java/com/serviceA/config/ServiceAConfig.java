package com.serviceA.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ServiceAConfig {

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/*
	 * @Bean CircuitBreakerConfig getCircuitBreakerConfig() { return
	 * CircuitBreakerConfig.custom() .failureRateThreshold(50)
	 * .slowCallRateThreshold(50) .waitDurationInOpenState(Duration.ofMillis(1000))
	 * .slowCallDurationThreshold(Duration.ofSeconds(2))
	 * .permittedNumberOfCallsInHalfOpenState(3) .minimumNumberOfCalls(10)
	 * .slidingWindowType(SlidingWindowType.TIME_BASED) .slidingWindowSize(5) //
	 * .recordException(e -> InternalServerException //
	 * .equals(getResponse().getStatus()))
	 * .recordExceptions(RestClientException.class)
	 * //.ignoreExceptions(BusinessException.class, OtherBusinessException.class)
	 * .build(); }
	 */
}
