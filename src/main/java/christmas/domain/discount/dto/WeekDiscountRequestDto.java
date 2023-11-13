package christmas.domain.discount.dto;

import christmas.domain.menu.Menu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WeekDiscountRequestDto implements DiscountRequestDto{
    private LocalDate orderDate;
    private List<Menu> menus = new ArrayList<>();

    public WeekDiscountRequestDto(LocalDate orderDate, List<Menu> menus) {
        this.orderDate = orderDate;
        this.menus = menus;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Menu> getMenus() {
        return menus;
    }
}
