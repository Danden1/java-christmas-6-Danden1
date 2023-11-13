package christmas.domain.discount.dto;

import java.time.LocalDate;
public class ChristmasDiscountRequestDto implements DiscountRequestDto {
    private LocalDate orderDate;

    public ChristmasDiscountRequestDto(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
}
