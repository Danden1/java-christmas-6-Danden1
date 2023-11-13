package christmas.domain.discount;

import christmas.domain.discount.dto.DiscountRequestDto;
import christmas.domain.discount.dto.DiscountResponseDto;

public interface Discounter {
    DiscountResponseDto discount(DiscountRequestDto discountRequestDto);
}
