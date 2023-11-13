package christmas.domain.discount.dto;

import java.time.LocalDate;

public class SpecialDiscountRequestDto implements DiscountRequestDto{
    private LocalDate orderDate;

    public SpecialDiscountRequestDto(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
}
