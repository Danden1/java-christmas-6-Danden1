package christmas.domain.discount.dto;

public class DiscountResponseDto {
    private int totalDiscount = 0;
    private final String discountName;

    public DiscountResponseDto(int totalDiscount, String discountName) {
        this.totalDiscount = totalDiscount;
        this.discountName = discountName;
    }

    public String getDiscountName() {
        return discountName;
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }
}
