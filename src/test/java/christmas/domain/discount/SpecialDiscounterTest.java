package christmas.domain.discount;

import christmas.domain.discount.dto.DiscountResponseDto;
import christmas.domain.discount.dto.SpecialDiscountRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class SpecialDiscounterTest {

    private SpecialDiscounter specialDiscounter = new SpecialDiscounter(getSpecialDate());

    @Test
    @DisplayName("특별한 날 할인 테스트")
    public void testSpecialDiscountTest() {
        DiscountResponseDto result = specialDiscounter.discount(new SpecialDiscountRequestDto(LocalDate.of(2023, 12,3)));

        Assertions.assertEquals(1000, result.getTotalDiscount());
    }

    @Test
    @DisplayName("일반 날 테스트")
    public void testNormalDiscountTest() {
        DiscountResponseDto result = specialDiscounter.discount(new SpecialDiscountRequestDto(LocalDate.of(2023, 12,4)));

        Assertions.assertEquals(0, result.getTotalDiscount());
    }

    @Test
    @DisplayName("특별한 날에 23년 12월이 아닌 경우 테스트")
    public void testInvalidDiscountDateRange() {
        Set<LocalDate> testDate1 = new HashSet<>();
        testDate1.add(LocalDate.of(2023,11,30));

        Set<LocalDate> testDate2 = new HashSet<>();
        testDate2.add(LocalDate.of(2024,12,30));

        Assertions.assertThrows(IllegalArgumentException.class, () -> new SpecialDiscounter(testDate1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SpecialDiscounter(testDate2));
    }

    private Set<LocalDate> getSpecialDate() {
        Set<LocalDate> specialDate = new HashSet<>();

        specialDate.add(LocalDate.of(2023,12,3));
        specialDate.add(LocalDate.of(2023,12,10));
        specialDate.add(LocalDate.of(2023,12,17));
        specialDate.add(LocalDate.of(2023,12,24));
        specialDate.add(LocalDate.of(2023,12,25));
        specialDate.add(LocalDate.of(2023,12,31));

        return specialDate;
    }

}
