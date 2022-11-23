package com.backend.feign;

import com.backend.data.dto.response.OrderDTO;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="2022pmds-backend-order", fallback = FallbackFactory.Default.class)
public interface OrderServiceClient {
    @GetMapping("/order/{userId}/orders")
    List<OrderDTO> getOrders(@PathVariable String userId);

}
