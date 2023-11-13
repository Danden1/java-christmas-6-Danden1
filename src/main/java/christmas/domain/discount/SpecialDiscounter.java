package christmas.domain.discount;

import christmas.domain.discount.dto.DiscountRequestDto;
import christmas.domain.discount.dto.DiscountResponseDto;
import christmas.domain.discount.dto.SpecialDiscountRequestDto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class SpecialDiscounter implements Discounter{

    private final Set<LocalDate> specialDate;
    private final String DISCOUNT_NAME = "특별 할인";

    public SpecialDiscounter(Set<LocalDate> specialDate) {
        for (LocalDate date : specialDate) {
            if (date.getYear() != 2023 || date.getMonth().getValue() != 12) {
                throw new IllegalArgumentException("23년 12월달만 할인이 가능합니다.");
            }
        }

        this.specialDate = new HashSet<>(specialDate);
    }

    @Override
    public DiscountResponseDto discount(DiscountRequestDto discountRequestDto) {
        if (!(discountRequestDto instanceof SpecialDiscountRequestDto specialDiscountRequestDto)) {
            throw new IllegalArgumentException("올바르지 않은 요청입니다.");
        }
        LocalDate orderDate = specialDiscountRequestDto.getOrderDate();
        int totalDiscount = 0;

        if (specialDate.contains(orderDate)) {
            totalDiscount = 1000;
        }

        return new DiscountResponseDto(totalDiscount, DISCOUNT_NAME);
    }
}
