package christmas.domain.discount;

import christmas.domain.discount.dto.DiscountResponseDto;
import christmas.domain.menu.Menu;
import christmas.domain.order.OrderRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class ChristmasDiscounterTest {

    private ChristmasDiscounter christmasDiscounter = new ChristmasDiscounter();
    @Test
    @DisplayName("크리스마스 할인 테스트")
    public void testChristmasTest() {
        DiscountResponseDto responseDto1 = christmasDiscounter.discount(new OrderRequestDto(LocalDate.of(2023,12,11), new ArrayList<Menu>()));
        DiscountResponseDto responseDto2 = christmasDiscounter.discount(new OrderRequestDto(LocalDate.of(2023,12,25),new ArrayList<Menu>()));

        Assertions.assertEquals(2000, responseDto1.getTotalDiscount());
        Assertions.assertEquals(3400, responseDto2.getTotalDiscount());

    }

    @Test
    @DisplayName("크리스마스 전 후 할인 테스트")
    public void testOverChristmasAndBeforeChristmas() {
        DiscountResponseDto responseDto1 = christmasDiscounter.discount(new OrderRequestDto(LocalDate.of(2023,11,30), new ArrayList<Menu>()));
        DiscountResponseDto responseDto2 = christmasDiscounter.discount(new OrderRequestDto(LocalDate.of(2023,12,27), new ArrayList<Menu>()));

        Assertions.assertEquals(0, responseDto1.getTotalDiscount());
        Assertions.assertEquals(0, responseDto2.getTotalDiscount());
    }



}
