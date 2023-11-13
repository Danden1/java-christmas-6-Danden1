package christmas.domain.discount;

import christmas.domain.discount.dto.DiscountResponseDto;
import christmas.domain.discount.dto.WeekDiscountRequestDto;
import christmas.domain.menu.*;
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
        DiscountResponseDto result = weekDiscounter.discount(new WeekDiscountRequestDto(LocalDate.of(2023,12,8), makeMenus()));

        Assertions.assertEquals(2023 * 4, result.getTotalDiscount());
    }

    @Test
    @DisplayName("평일 할인 테스트")
    public void testWeekDayDiscount() {
        DiscountResponseDto result = weekDiscounter.discount(new WeekDiscountRequestDto(LocalDate.of(2023,12,7), makeMenus()));

        Assertions.assertEquals(2023 * 2, result.getTotalDiscount());
    }

    @Test
    @DisplayName("잘못된 request 타입 테스트")
    public void testInvalidRequestType() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> weekDiscounter.discount(new InvalidTestDto(LocalDate.of(2023,12,7))));
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
