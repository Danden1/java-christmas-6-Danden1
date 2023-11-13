package christmas.domain.discount;

import christmas.domain.discount.dto.ChristmasDiscountRequestDto;
import christmas.domain.discount.dto.DiscountRequestDto;
import christmas.domain.discount.dto.DiscountResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ChristmasDiscounterTest {

    private ChristmasDiscounter christmasDiscounter = new ChristmasDiscounter();
    @Test
    @DisplayName("크리스마스 할인 테스트")
    public void testChristmasTest() {
        DiscountResponseDto responseDto1 = christmasDiscounter.discount(new ChristmasDiscountRequestDto(LocalDate.of(2023,12,11)));
        DiscountResponseDto responseDto2 = christmasDiscounter.discount(new ChristmasDiscountRequestDto(LocalDate.of(2023,12,25)));

        Assertions.assertEquals(2000, responseDto1.getTotalDiscount());
        Assertions.assertEquals(3400, responseDto2.getTotalDiscount());

    }

    @Test
    @DisplayName("잘못된 request 타입 테스트")
    public void testInvalidRequestType() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> christmasDiscounter.discount(new InvalidTestDto(LocalDate.of(2023,12,11))));
    }

    @Test
    @DisplayName("크리스마스 전 후 할인 테스트")
    public void testOverChristmasAndBeforeChristmas() {
        DiscountResponseDto responseDto1 = christmasDiscounter.discount(new ChristmasDiscountRequestDto(LocalDate.of(2023,11,30)));
        DiscountResponseDto responseDto2 = christmasDiscounter.discount(new ChristmasDiscountRequestDto(LocalDate.of(2023,12,27)));

        Assertions.assertEquals(0, responseDto1.getTotalDiscount());
        Assertions.assertEquals(0, responseDto2.getTotalDiscount());
    }


    public static class InvalidTestDto implements DiscountRequestDto {
        private LocalDate orderDate;

        public InvalidTestDto(LocalDate orderDate) {
            this.orderDate = orderDate;
        }

        public LocalDate getOrderDate() {
            return orderDate;
        }
    }

}
