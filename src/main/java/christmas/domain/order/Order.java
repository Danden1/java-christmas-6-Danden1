package christmas.domain.order;

public interface Order {
    OrderResponseDto order(OrderRequestDto orderRequestDto);
}
