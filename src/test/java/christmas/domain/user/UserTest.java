package christmas.domain.user;

import christmas.domain.menu.Menu;
import christmas.domain.order.Order;
import christmas.domain.order.OrderRequestDto;
import christmas.domain.order.OrderResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserTest {

    @Test
    @DisplayName("주문 후, 배지 설정 되는 지 테스트(트리)")
    public void testOrderAndSetTreeBadge() {
        List<Menu> menus = new ArrayList<>();
        menus.add(TestMenu.TREE);
        OrderRequestDto request = new OrderRequestDto(LocalDate.of(2023,12,11), menus);
        User user = new User(new TestOrder());

        OrderResponseDto response = user.order(request);

        Assertions.assertEquals(Badge.TREE, user.getBadge().get());
    }

    @Test
    @DisplayName("주문 후, 배지 설정 되는 지 테스트(별)")
    public void testOrderAndSetStarBadge() {
        List<Menu> menus = new ArrayList<>();
        menus.add(TestMenu.STAR);
        OrderRequestDto request = new OrderRequestDto(LocalDate.of(2023,12,11), menus);
        User user = new User(new TestOrder());

        OrderResponseDto response = user.order(request);

        Assertions.assertEquals(Badge.STAR, user.getBadge().get());
    }

    @Test
    @DisplayName("주문 후, 배지 설정 되는 지 테스트(산타)")
    public void testOrderAndSetSantaBadge() {
        List<Menu> menus = new ArrayList<>();
        menus.add(TestMenu.SANTA);
        OrderRequestDto request = new OrderRequestDto(LocalDate.of(2023,12,11), menus);
        User user = new User(new TestOrder());

        OrderResponseDto response = user.order(request);

        Assertions.assertEquals(Badge.SANTA, user.getBadge().get());
    }

    @Test
    @DisplayName("주문 후, 배지 설정 안되는 지  테스트)")
    public void testOrderAndSetNoneBadge() {
        List<Menu> menus = new ArrayList<>();
        menus.add(TestMenu.NONE);
        OrderRequestDto request = new OrderRequestDto(LocalDate.of(2023,12,11), menus);
        User user = new User(new TestOrder());

        OrderResponseDto response = user.order(request);

        Assertions.assertEquals(Optional.empty(), user.getBadge());
    }

    public static class TestOrder implements Order {

        @Override
        public OrderResponseDto order(OrderRequestDto orderRequestDto) {
            Menu menu = orderRequestDto.getMenus().get(0);
            OrderResponseDto response = new OrderResponseDto();
            response.setDiscountPrice(menu.getPrice());

            return response;
        }
    }

    public static enum TestMenu implements Menu {

        NONE(100),
        TREE(Badge.TREE.getMinPrice()),
        STAR(Badge.STAR.getMinPrice()),
        SANTA(Badge.SANTA.getMinPrice());

        private final int price;

        TestMenu(int price) {
            this.price = price;
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
            return null;
        }

        @Override
        public String getPrintPrice() {
            return null;
        }
    }
}
