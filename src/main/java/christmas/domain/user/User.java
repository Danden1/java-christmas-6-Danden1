package christmas.domain.user;

import christmas.domain.order.Order;
import christmas.domain.order.OrderRequestDto;
import christmas.domain.order.OrderResponseDto;

import java.util.Optional;

public class User {

    private Optional<Badge> badge;
    private final Order order;

    public User(Order order) {
        this.order = order;
    }

    public OrderResponseDto order(OrderRequestDto request) {
        OrderResponseDto response = order.order(request);
        this.badge = Badge.findByPrice(response.getDiscountPrice());

        return response;
    }

    public Optional<Badge> getBadge() {
        return badge;
    }
}
