package christmas.domain.discount;

import christmas.domain.discount.dto.DiscountRequestDto;
import christmas.domain.discount.dto.ChristmasDiscountRequestDto;
import christmas.domain.discount.dto.DiscountResponseDto;

import java.time.LocalDate;

public class ChristmasDiscounter implements Discounter{

    private final LocalDate START_DATE = LocalDate.of(2023,12,1);
    private final LocalDate END_DATE = LocalDate.of(2023,12,25);

    private final String DISCOUNT_NAME = "크리스마스 디데이 할인";

    @Override
    public DiscountResponseDto discount(DiscountRequestDto discountRequestDto) {
        if (!(discountRequestDto instanceof ChristmasDiscountRequestDto discountRequest)) {
            throw new IllegalArgumentException("올바르지 않은 요청입니다.");
        }

        LocalDate orderDate = discountRequest.getOrderDate();
        int totalDiscount = 0;

        if (isValidDateRange(orderDate)) {
            totalDiscount = calculateDiscountPrice(orderDate);
        }

        return new DiscountResponseDto(totalDiscount, DISCOUNT_NAME);
    }

    private boolean isValidDateRange(LocalDate orderDate) {
        return !orderDate.isAfter(END_DATE) && !orderDate.isBefore(START_DATE);
    }

    private int calculateDiscountPrice(LocalDate orderDate) {
        return (orderDate.getDayOfMonth() -1 ) * 100 + 1000;
    }
}
