package christmas.domain.discount;

import christmas.domain.discount.dto.DiscountResponseDto;
import christmas.domain.order.OrderRequestDto;

import java.time.LocalDate;

public class ChristmasDiscounter implements Discounter{

    private final LocalDate START_DATE = LocalDate.of(2023,12,1);
    private final LocalDate END_DATE = LocalDate.of(2023,12,25);

    private final String DISCOUNT_NAME = "크리스마스 디데이 할인";

    @Override
    public DiscountResponseDto discount(OrderRequestDto orderRequest) {

        LocalDate orderDate = orderRequest.getOrderDate();
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
