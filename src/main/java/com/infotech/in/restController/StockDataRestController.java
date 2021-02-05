package com.infotech.in.restController;

import java.awt.PageAttributes.MediaType;
import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotech.in.ENTITY.StockData;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
public class StockDataRestController {

	/**
	 * RETRIVING DATA BY USING MONO OBJECT
	 * 
	 * @return
	 */
	@GetMapping(value = "/getevent", produces = "application/json")
	public ResponseEntity<Mono<StockData>> getData() {
		StockData stockData = new StockData(121, "HCL", 896.25, new Date());
		Mono<StockData> customermono = Mono.just(stockData);

		return new ResponseEntity<Mono<StockData>>(customermono, HttpStatus.OK);

	}

	/**
	 * Retriving data by using flux object
	 * 
	 */
	@GetMapping(value = "/fluxData")
	public ResponseEntity<Flux<StockData>> getDynamicData() {
		StockData stockData = new StockData(147, "TATA", 9853.258, new Date());
		Stream<StockData> generate = Stream.generate(() -> stockData);
		Flux<StockData> fromStream = Flux.fromStream(generate);
		Flux<Long> interval = Flux.interval(Duration.ofMillis(500));
		Flux<Tuple2<Long, StockData>> zip = Flux.zip(interval, fromStream);
		Flux<StockData> map = zip.map(Tuple2::getT2);
		return new ResponseEntity<Flux<StockData>>(map, HttpStatus.OK);

	}

}
