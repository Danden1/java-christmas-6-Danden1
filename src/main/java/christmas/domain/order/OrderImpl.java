package christmas.domain.order;

import christmas.domain.discount.Discounter;
import christmas.domain.discount.dto.DiscountResponseDto;
import christmas.domain.discount.dto.FreeBieResponseDto;
import christmas.domain.menu.DrinkMenu;
import christmas.domain.menu.Menu;

import java.util.ArrayList;
import java.util.List;

public class OrderImpl implements Order{

    private final List<Discounter> discounters;

    public OrderImpl(List<Discounter> discounters) {
        this.discounters = discounters;
    }

    @Override
    public OrderResponseDto order(OrderRequestDto orderRequestDto) {
        assertValidOrder(orderRequestDto.getMenus());

        List<DiscountResponseDto> discountResult = getDiscountResponses(orderRequestDto);
        int discountPrice = getDiscountPrice(discountResult);
        int priceBeforeDiscount = getPriceBeforeDiscount(orderRequestDto.getMenus());
        int priceAfterDiscount = priceBeforeDiscount - discountPrice;

        OrderResponseDto result = new OrderResponseDto();
        result.setDiscountResults(discountResult);
        result.setDiscountPrice(discountPrice);
        result.setPriceAfterDiscount(priceAfterDiscount);
        result.setPriceBeforeDiscount(priceBeforeDiscount);


        return result;
    }

    private void assertValidOrder(List<Menu> menus) {
        boolean validCheck = false;

        for (Menu menu : menus) {
            if (!(menu instanceof DrinkMenu)) {
                validCheck = true;
                break;
            }
        }

        if (!validCheck) {
            throw new IllegalArgumentException(OrderErrorMessage.INVALID_ORDER.getMessage());
        }
    }


    private List<DiscountResponseDto> getDiscountResponses(OrderRequestDto orderRequestDto){
        List<DiscountResponseDto> result = new ArrayList<>();

        for (Discounter discounter : discounters) {
            result.add(discounter.discount(orderRequestDto));
        }
        return result;
    }


    //증정은 따로 처리 필요.
    private int getDiscountPrice(List<DiscountResponseDto> discounts) {
        int discountPrice = 0;
        for (DiscountResponseDto discount : discounts) {
            discountPrice += discount.getTotalDiscount();
        }

        return discountPrice;
    }

    private int getPriceBeforeDiscount(List<Menu> menus) {
        int priceBeforeDiscount = 0;

        for (Menu menu : menus) {
            priceBeforeDiscount += menu.getPrice();
        }

        return priceBeforeDiscount;
    }
}
