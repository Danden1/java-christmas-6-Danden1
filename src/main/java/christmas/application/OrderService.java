package christmas.application;

import christmas.domain.discount.*;
import christmas.domain.menu.Menu;
import christmas.domain.menu.utils.MenuFinder;
import christmas.domain.order.Order;
import christmas.domain.order.OrderImpl;
import christmas.domain.order.OrderRequestDto;
import christmas.domain.order.OrderResponseDto;
import christmas.domain.user.User;

import java.time.LocalDate;
import java.util.*;

public class OrderService {
    private final Order order;
    private final MenuFinder menuFinder;


    public OrderService(Order order, MenuFinder menuFinder) {
        this.order = order;
        this.menuFinder = menuFinder;
    }

    public OrderResponseDto order(User user, List<Menu> menus, LocalDate orderDate) {
        return user.order(new OrderRequestDto(orderDate, menus));
    }

    public User makeUser() {
        return new User(order);
    }

    public List<Menu> parsingMenu(Map<String, Integer> orderMenus) throws IllegalArgumentException {
        List<Menu> menus = new ArrayList<>();

        for (String key : orderMenus.keySet()) {
            Menu menu = menuFinder.findByName(key);
            addMenu(menus, menu, orderMenus.get(key));
        }

        return menus;
    }



    private void addMenu(List<Menu> menus, Menu menu, int count) {
        for (int i = 0; i < count; i++) {
            menus.add(menu);
        }
    }
}
