package christmas.domain.discount;

import christmas.domain.discount.dto.DiscountRequestDto;
import christmas.domain.discount.dto.DiscountResponseDto;
import christmas.domain.discount.dto.FreeBieRequestDto;
import christmas.domain.discount.dto.FreeBieResponseDto;
import christmas.domain.menu.DrinkMenu;
import christmas.domain.menu.Menu;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class FreeBieDiscounter implements Discounter {

    private final String DISCOUNT_NAME = "증정 할인";
    private final Menu FREEBIE_MENU = DrinkMenu.CHAMPAGNE;
    private final int FREEBIE_MIN_PRICE = 120_000;

    @Override
    public DiscountResponseDto discount(DiscountRequestDto discountRequestDto) {
        if (!(discountRequestDto instanceof FreeBieRequestDto freeBieRequestDto)) {
            throw new IllegalArgumentException("올바르지 않은 요청입니다.");
        }
        List<Menu> menus = freeBieRequestDto.getMenus();
        LocalDate orderDate = freeBieRequestDto.getOrderDate();

        if (isFreeBie(menus, orderDate)) {
            return new FreeBieResponseDto(Optional.of(FREEBIE_MENU), DISCOUNT_NAME);
        }

        return new FreeBieResponseDto(Optional.empty(), DISCOUNT_NAME);
    }

    private boolean isFreeBie(List<Menu> menus, LocalDate orderDate) {
        int totalPrice = 0;

        if (orderDate.getMonth().getValue() != 12 || orderDate.getYear() != 2023) {
            return false;
        }

        for (Menu menu : menus) {
            totalPrice += menu.getPrice();
        }

        return totalPrice >= FREEBIE_MIN_PRICE;
    }
}
