package christmas.domain.discount;

import christmas.domain.discount.dto.DiscountResponseDto;
import christmas.domain.menu.*;
import christmas.domain.order.Order;
import christmas.domain.order.OrderRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WeekDiscounterTest {

    private WeekDiscounter weekDiscounter = new WeekDiscounter();

    @Test
    @DisplayName("주말 할인 테스트")
    public void testWeekendDiscount() {
        DiscountResponseDto result = weekDiscounter.discount(new OrderRequestDto(LocalDate.of(2023,12,8), makeMenus()));

        Assertions.assertEquals(2023 * 4, result.getTotalDiscount());
    }

    @Test
    @DisplayName("평일 할인 테스트")
    public void testWeekDayDiscount() {
        DiscountResponseDto result = weekDiscounter.discount(new OrderRequestDto(LocalDate.of(2023,12,7), makeMenus()));

        Assertions.assertEquals(2023 * 2, result.getTotalDiscount());
    }

    @Test
    @DisplayName("23년 12월 아닌 날짜 할인 테스트")
    public void testInvalidDiscountDateRange() {
        DiscountResponseDto result1 = weekDiscounter.discount(new OrderRequestDto(LocalDate.of(2023,11,30), makeMenus()));
        DiscountResponseDto result2 = weekDiscounter.discount(new OrderRequestDto(LocalDate.of(2023,11,25), makeMenus()));

        Assertions.assertEquals(0, result1.getTotalDiscount());
        Assertions.assertEquals("평일 할인", result1.getDiscountName());

        Assertions.assertEquals(0, result2.getTotalDiscount());
        Assertions.assertEquals("주말 할인", result2.getDiscountName());
    }


    private List<Menu> makeMenus() {
        List<Menu> result = new ArrayList<>();
        result.add(DessertMenu.ICE_CREAM);
        result.add(DessertMenu.CHOCO_CAKE);

        result.add(MainFoodMenu.BARBECUE_RIBS);
        result.add(MainFoodMenu.SEAFOOD_PASTA);
        result.add(MainFoodMenu.CHRISTMAS_PASTA);
        result.add(MainFoodMenu.T_BONE_STEAK);

        result.add(DrinkMenu.CHAMPAGNE);
        result.add(AppetizerMenu.BUTTON_MUSHROOM_SOUP);

        return result;
    }

}
