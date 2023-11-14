package christmas.domain.order;

import christmas.domain.menu.Menu;

import java.time.LocalDate;
import java.util.List;

public class OrderRequestDto {
    private List<Menu> menus;
    private LocalDate orderDate;

    public OrderRequestDto(LocalDate orderDate, List<Menu> menus) {
        this.menus = menus;
        this.orderDate = orderDate;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
}
