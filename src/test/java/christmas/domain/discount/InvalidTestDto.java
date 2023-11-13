package christmas.domain.discount;

import christmas.domain.discount.dto.DiscountRequestDto;

import java.time.LocalDate;

public class InvalidTestDto implements DiscountRequestDto {
    private LocalDate orderDate;

    public InvalidTestDto(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }
}
