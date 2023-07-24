package com.ecommerce;
;
import com.ecommerce.application.request.ProductRequest;
import com.ecommerce.application.response.ProductResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;


@SpringBootTest(classes = ECommerceServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ECommerceApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@LocalServerPort
	private Integer localPort;

	@Test
	@DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
	void test_shouldReturnProperPriceData_case1()  {

		int day = 14;
		int hour = 10;
		int year = 2020;
		ProductRequest productRequest = this.generateRequest(day,hour,year);
		ResponseEntity<ProductResponse> response = this.callToController(productRequest);
		ProductResponse productResponse = response.getBody();
		Assertions.assertAll(() -> Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()),
				() -> Assertions.assertEquals(35.5F, productResponse.getPrice()),
				() -> Assertions.assertEquals(Boolean.TRUE,
						this.dateIncludedBetweenDates(
								this.generateDate(day, hour, year), productResponse)),
				() -> Assertions.assertEquals(1, productResponse.getBrandId()),
				() -> Assertions.assertEquals(1, productResponse.getPriceList()));
	}

	@Test
	@DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
	void test_shouldReturnProperPriceData_case2()  {

		int day = 14;
		int hour = 16;
		int year = 2020;
		ProductRequest productRequest = this.generateRequest(day,hour,year);
		ResponseEntity<ProductResponse> response = this.callToController(productRequest);
		ProductResponse productResponse = response.getBody();
		Assertions.assertAll(() -> Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()),
				() -> Assertions.assertEquals(25.45D, productResponse.getPrice()),
				() -> Assertions.assertEquals(Boolean.TRUE,
						this.dateIncludedBetweenDates(
								this.generateDate(day, hour,year), productResponse)),
				() -> Assertions.assertEquals(1, productResponse.getBrandId()),
				() -> Assertions.assertEquals(2, productResponse.getPriceList()));
	}

	@Test
	@DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
	void test_shouldReturnProperPriceData_case3()  {

		int day = 14;
		int hour = 21;
		int year = 2020;
		ProductRequest productRequest = this.generateRequest(day,hour,year);
		ResponseEntity<ProductResponse> response = this.callToController(productRequest);
		ProductResponse productResponse = response.getBody();
		Assertions.assertAll(() -> Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()),
				() -> Assertions.assertEquals(35.5F, productResponse.getPrice()),
				() -> Assertions.assertEquals(Boolean.TRUE,
						this.dateIncludedBetweenDates(
								this.generateDate(day, hour,year), productResponse)),
				() -> Assertions.assertEquals(1, productResponse.getBrandId()),
				() -> Assertions.assertEquals(1, productResponse.getPriceList()));
	}

	@Test
	@DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)")
	void test_shouldReturnProperPriceData_case4()  {

		int day = 15;
		int hour = 10;
		int year = 2020;
		ProductRequest productRequest = this.generateRequest(day,hour,year);
		ResponseEntity<ProductResponse> response = this.callToController(productRequest);
		ProductResponse productResponse = response.getBody();
		Assertions.assertAll(() -> Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()),
				() -> Assertions.assertEquals(30.5F, productResponse.getPrice()),
				() -> Assertions.assertEquals(Boolean.TRUE,
						this.dateIncludedBetweenDates(
								this.generateDate(day, hour,year), productResponse)),
				() -> Assertions.assertEquals(1, productResponse.getBrandId()),
				() -> Assertions.assertEquals(3, productResponse.getPriceList()));
	}

	@Test
	@DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)")
	void shouldReturnProperPriceData_case5()  {

		int day = 16;
		int hour = 21;
		int year = 2020;
		ProductRequest productRequest = this.generateRequest(day,hour,year);
		ResponseEntity<ProductResponse> response = this.callToController(productRequest);
		ProductResponse productResponse = response.getBody();
		Assertions.assertAll(() -> Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()),
				() -> Assertions.assertEquals(38.95D, productResponse.getPrice()),
				() -> Assertions.assertEquals(Boolean.TRUE,
						this.dateIncludedBetweenDates(
								this.generateDate(day, hour,year), productResponse)),
				() -> Assertions.assertEquals(1, productResponse.getBrandId()),
				() -> Assertions.assertEquals(4, productResponse.getPriceList()));
	}



	private ResponseEntity<ProductResponse> callToController(ProductRequest productRequest) {
		UriComponentsBuilder paramBuilder = this.queryParamBuilder(productRequest.getCurrentDate(),
				productRequest.getProductId(), productRequest.getBrandId());

		ResponseEntity<ProductResponse> response = this.doGet(paramBuilder);
		return response;
	}

	private UriComponentsBuilder queryParamBuilder(LocalDateTime currentDate, Long productId, Long brandId) {
		return UriComponentsBuilder.fromHttpUrl("http://localhost:" + localPort + "/api/v1/product")
				.queryParam("currentDate", currentDate)
				.queryParam("productId", productId)
				.queryParam("brandId", brandId);
	}


	private ResponseEntity<ProductResponse> doGet(UriComponentsBuilder builder) {
		return testRestTemplate.exchange(
				builder.build().encode().toUri(),
				HttpMethod.GET, null, ProductResponse.class);
	}

	private ProductRequest generateRequest(int day, int hour, int year) {
		return new ProductRequest(35455L, 1L, this.generateDate(day, hour, year));
	}

	private LocalDateTime generateDate(int day, int hour, int year) {
		return LocalDateTime.of(year, 6, day, hour, 0, 0);
	}

	private boolean dateIncludedBetweenDates(LocalDateTime currenDate, ProductResponse productResponse) {
		return true;
	}

}
