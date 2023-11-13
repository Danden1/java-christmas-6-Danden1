package christmas.domain.discount.dto;

import christmas.domain.menu.Menu;

import java.time.LocalDate;
import java.util.List;

public class FreeBieRequestDto implements DiscountRequestDto {
    private List<Menu> menus;
    private LocalDate orderDate;

    public FreeBieRequestDto(List<Menu> menus, LocalDate orderDate) {
        this.menus = menus;
        this.orderDate = orderDate;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }
}
