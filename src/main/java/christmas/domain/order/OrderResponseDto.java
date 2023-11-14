package christmas.domain.order;

import christmas.domain.discount.dto.DiscountResponseDto;

import java.util.List;

public class OrderResponseDto {
    private List<DiscountResponseDto> discountResults;
    private int priceBeforeDiscount;
    private int priceAfterDiscount;
    private int discountPrice;

    public List<DiscountResponseDto> getDiscountResults() {
        return discountResults;
    }

    public void setDiscountResults(List<DiscountResponseDto> discountResults) {
        this.discountResults = discountResults;
    }

    public int getPriceBeforeDiscount() {
        return priceBeforeDiscount;
    }

    public void setPriceBeforeDiscount(int priceBeforeDiscount) {
        this.priceBeforeDiscount = priceBeforeDiscount;
    }

    public int getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public void setPriceAfterDiscount(int priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }
}
