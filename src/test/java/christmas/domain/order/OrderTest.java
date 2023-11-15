package christmas.domain.order;

import christmas.domain.discount.Discounter;
import christmas.domain.discount.dto.DiscountResponseDto;
import christmas.domain.menu.DrinkMenu;
import christmas.domain.menu.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderTest {

    private Order order = new OrderImpl(List.of(new TestDiscounter()));

    @Test
    @DisplayName("주문 테스트")
    public void testOrder() {
        int testMenuCount = 3;
        OrderRequestDto orderRequestDto = new OrderRequestDto(LocalDate.of(2023,12,3), makeMenus(testMenuCount));
        OrderResponseDto result = order.order(orderRequestDto);

        Assertions.assertEquals(1000* testMenuCount, result.getDiscountPrice());
        Assertions.assertEquals(TestMenu.TEST_MENU.getPrice() * testMenuCount, result.getPriceBeforeDiscount());
        Assertions.assertEquals(TestMenu.TEST_MENU.getPrice() * testMenuCount - 1000 * testMenuCount, result.getPriceAfterDiscount());

        for (DiscountResponseDto discount : result.getDiscountResults()) {
            Assertions.assertEquals(1000 * testMenuCount, discount.getTotalDiscount());
            Assertions.assertEquals("테스트 할인", discount.getDiscountName());
        }
    }

    @Test
    @DisplayName("마실 것만 주문 할 때 테스트. 오류 발생해야 함.")
    public void testOnlyDrinkOrder() {
        int testMenuCount = 3;
        OrderRequestDto orderRequestDto = new OrderRequestDto(LocalDate.of(2023,12,3), makeOnlyDrinkMenus(testMenuCount));
        Assertions.assertThrows(IllegalArgumentException.class, () -> order.order(orderRequestDto));


    }

    private List<Menu > makeOnlyDrinkMenus(int size) {
        List<Menu> menus = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            menus.add(DrinkMenu.ZERO_COKE);
        }

        return menus;
    }

    private List<Menu> makeMenus(int size) {
        List<Menu> menus = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            menus.add(TestMenu.TEST_MENU);
        }

        return menus;
    }

    public static class TestDiscounter implements Discounter {
        @Override
        public DiscountResponseDto discount(OrderRequestDto orderRequest) {
            int totalDiscount = 0;
            for (Menu menu : orderRequest.getMenus()){
                totalDiscount += 1000;
            }
            DiscountResponseDto result = new DiscountResponseDto(totalDiscount,"테스트 할인");
            return result;
        }
    }

    public static enum TestMenu implements Menu {

        TEST_MENU(30_000, "테스트 메뉴");
        private int price;
        private String name;

        TestMenu(int price, String name) {
            this.price = price;
            this.name = name;
        }

        @Override
        public Menu findByName(String menuName) {
            return null;
        }

        @Override
        public int getPrice() {
            return price;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getPrintPrice() {
            return null;
        }
    }
}
