package com.ecommerce.application.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
/**
 *
 * @author jfvaldez
 */
@Getter
@Setter
@AllArgsConstructor
public class ProductRequest {

    private Long productId;
    private Long brandId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime currentDate;
}
