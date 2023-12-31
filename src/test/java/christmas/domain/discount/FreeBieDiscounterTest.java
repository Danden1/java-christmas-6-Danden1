package christmas.domain.discount;

import christmas.domain.discount.dto.FreeBieResponseDto;
import christmas.domain.menu.DrinkMenu;
import christmas.domain.menu.Menu;
import christmas.domain.order.OrderRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FreeBieDiscounterTest {

    private FreeBieDiscounter freeBieDiscounter = new FreeBieDiscounter();
    @Test
    @DisplayName("증정 이벤트 테스트. 샴페인 증정하는 지 테스트.")
    public void testFreeBie() {
        OrderRequestDto freeBieRequestDto = new OrderRequestDto(LocalDate.of(2023,12,1),makeMenuOver120_000Won());
        FreeBieResponseDto freeBieResponseDto = (FreeBieResponseDto) freeBieDiscounter.discount(freeBieRequestDto);

        Assertions.assertEquals(DrinkMenu.CHAMPAGNE, freeBieResponseDto.getFreeBieMenu().get());
        Assertions.assertEquals(DrinkMenu.CHAMPAGNE.getPrice(), freeBieResponseDto.getTotalDiscount());
    }

    @Test
    @DisplayName("증정 이벤트 테스트. 기준 가격 이하로 주문했을 경우 테스트.")
    public void testNotFreeBie() {
        OrderRequestDto freeBieRequestDto = new OrderRequestDto(LocalDate.of(2023,12,1), makeMenuUnder120_000Won());
        FreeBieResponseDto freeBieResponseDto = (FreeBieResponseDto) freeBieDiscounter.discount(freeBieRequestDto);

        Assertions.assertEquals(Optional.empty(), freeBieResponseDto.getFreeBieMenu());
        Assertions.assertEquals(0, freeBieResponseDto.getTotalDiscount());
    }

    @Test
    @DisplayName("23년 12월이 아닌 경우 할인 안 하는지 테스트")
    public void testInvalidFreeBieDateRange() {
        OrderRequestDto freeBieRequestDto1 = new OrderRequestDto(LocalDate.of(2024,1,1), makeMenuOver120_000Won());
        FreeBieResponseDto freeBieResponseDto1 = (FreeBieResponseDto) freeBieDiscounter.discount(freeBieRequestDto1);

        OrderRequestDto freeBieRequestDto2 = new OrderRequestDto(LocalDate.of(2023,11,30), makeMenuOver120_000Won());
        FreeBieResponseDto freeBieResponseDto2 = (FreeBieResponseDto) freeBieDiscounter.discount(freeBieRequestDto2);

        Assertions.assertEquals(Optional.empty(), freeBieResponseDto1.getFreeBieMenu());
        Assertions.assertEquals(0, freeBieResponseDto1.getTotalDiscount());

        Assertions.assertEquals(Optional.empty(), freeBieResponseDto2.getFreeBieMenu());
        Assertions.assertEquals(0, freeBieResponseDto2.getTotalDiscount());
    }

    public static enum TestMenu implements Menu {
        THIRTY_THOUSAND(30_000, "테스트");

        private int price;
        private String name;

        TestMenu(int price, String name) {
            this.price = price;
            this.name = name;
        }


        @Override
        public int getPrice() {
            return price;
        }

        @Override
        public String getName() {
            return name;
        }

    }
    private List<Menu> makeMenuOver120_000Won() {
        int totalPrice = 0;
        List<Menu> menus = new ArrayList<>();

        while (totalPrice < 120_000) {
            menus.add(TestMenu.THIRTY_THOUSAND);
            totalPrice += TestMenu.THIRTY_THOUSAND.getPrice();
        }

        return menus;
    }

    private List<Menu> makeMenuUnder120_000Won() {
        int totalPrice = 0;
        List<Menu> menus = new ArrayList<>();

        while (totalPrice +TestMenu.THIRTY_THOUSAND.getPrice() < 120_000) {
            menus.add(TestMenu.THIRTY_THOUSAND);
            totalPrice += TestMenu.THIRTY_THOUSAND.getPrice();
        }

        return menus;
    }
}
