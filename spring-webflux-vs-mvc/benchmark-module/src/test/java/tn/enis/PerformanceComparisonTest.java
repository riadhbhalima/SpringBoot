package tn.enis;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PerformanceComparisonTest {

	private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:8081").build();

	private final RestTemplate restTemplate = new RestTemplate();

	@Test
	void testConcurrentRequests() throws Exception {
		System.out.println("=== TEST: 10000 requêtes concurrentes ===");

		// Test WebFlux
		System.out.println("\n1. Test WebFlux (port 8081):");
		testWebFluxConcurrent(1000);

		// Test MVC
		System.out.println("\n2. Test Spring MVC (port 8082):");
		testMvcConcurrent(1000);
	}

	private void testWebFluxConcurrent(int requestCount) {
		long startTime = System.currentTimeMillis();
		AtomicInteger successCount = new AtomicInteger();

		Flux.range(1, requestCount).flatMap(i -> webClient.get().uri("/api/clients").retrieve().bodyToFlux(Object.class)
				.count().doOnNext(count -> successCount.incrementAndGet()).onErrorResume(e -> {
					System.err.println("Erreur WebFlux: " + e.getMessage());
					return Mono.just(0L);
				}), 100) // Concurrency level
				.blockLast(Duration.ofSeconds(30));

		long duration = System.currentTimeMillis() - startTime;
		System.out.println(
				"WebFlux: " + successCount.get() + "/" + requestCount + " requêtes réussies en " + duration + "ms");
		System.out.println("Requêtes par seconde: " + (successCount.get() * 1000.0 / duration));
	}

	private void testMvcConcurrent(int requestCount) throws Exception {
		long startTime = System.currentTimeMillis();
		AtomicInteger successCount = new AtomicInteger();

		ExecutorService executor = Executors.newFixedThreadPool(100);

		for (int i = 0; i < requestCount; i++) {
			executor.submit(() -> {
				try {
					restTemplate.getForObject("http://localhost:8082/api/clients", String.class);
					successCount.incrementAndGet();
				} catch (Exception e) {
					System.err.println("Erreur MVC: " + e.getMessage());
				}
			});
		}

		executor.shutdown();
		executor.awaitTermination(30, TimeUnit.SECONDS);

		long duration = System.currentTimeMillis() - startTime;
		System.out.println(
				"Spring MVC: " + successCount.get() + "/" + requestCount + " requêtes réussies en " + duration + "ms");
		System.out.println("Requêtes par seconde: " + (successCount.get() * 1000.0 / duration));
	}

	
}