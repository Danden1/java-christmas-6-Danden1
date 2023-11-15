package christmas;

import christmas.application.OrderService;
import christmas.domain.discount.*;
import christmas.domain.menu.utils.MenuFinder;
import christmas.domain.order.Order;
import christmas.domain.order.OrderImpl;
import christmas.presentation.OrderController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Application {
    public static void main(String[] args) {
        Order order = new OrderImpl(discounters());
        OrderService orderService = new OrderService(order, new MenuFinder());
        OrderController orderController = new OrderController(orderService);
        orderController.gameStart();
    }

    private static List<Discounter> discounters() {
        List<Discounter> discounters = new ArrayList<>();
        discounters.add(new ChristmasDiscounter());
        discounters.add(new WeekDiscounter());
        discounters.add(new SpecialDiscounter(specialDates()));
        discounters.add(new FreeBieDiscounter());

        return discounters;
    }

    private static Set<LocalDate> specialDates() {
        Set<LocalDate> specialDates = new HashSet<>();
        specialDates.add(LocalDate.of(2023,12,3));
        specialDates.add(LocalDate.of(2023,12,10));
        specialDates.add(LocalDate.of(2023,12,17));
        specialDates.add(LocalDate.of(2023,12,24));
        specialDates.add(LocalDate.of(2023,12,25));
        specialDates.add(LocalDate.of(2023,12,31));

        return specialDates;
    }
}
