package christmas.domain.discount;

import christmas.domain.discount.dto.DiscountResponseDto;
import christmas.domain.order.OrderRequestDto;

public interface Discounter {
    DiscountResponseDto discount(OrderRequestDto orderRequest);
}
