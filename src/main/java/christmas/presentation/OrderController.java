package christmas.presentation;

import christmas.application.OrderService;
import christmas.domain.menu.Menu;
import christmas.domain.order.OrderImpl;
import christmas.domain.order.OrderResponseDto;
import christmas.domain.user.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class OrderController {
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void gameStart() {
        LocalDate orderDate = intiGame();
        List<Menu> menus = order();
        User user = orderService.makeUser();
        outputView.printOrderDate(orderDate);

        OrderResponseDto orderResponseDto = orderService.order(user, menus, orderDate);
        outputView.printOrder(menus);
        outputView.printOrderResult(orderResponseDto);
        outputView.printBadge(user);
    }

    private LocalDate intiGame() {
        while(true) {
            try {
                outputView.printStart();
                return inputView.inputDate();
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private List<Menu> order() throws IllegalArgumentException {
        while (true) {
            try {
                Map<String, Integer> result = inputView.inputMenus();
                return orderService.parsingMenu(result);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
