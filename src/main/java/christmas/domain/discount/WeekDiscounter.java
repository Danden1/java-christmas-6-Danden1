package christmas.domain.discount;

import christmas.domain.discount.dto.DiscountResponseDto;
import christmas.domain.menu.DessertMenu;
import christmas.domain.menu.MainFoodMenu;
import christmas.domain.menu.Menu;
import christmas.domain.order.OrderRequestDto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class WeekDiscounter implements Discounter{

    private final String WEEKEND_DISCOUNT_NAME = "주말 할인";
    private final String WEEKDAY_DISCOUNT_NAME = "평일 할인";
    @Override
    public DiscountResponseDto discount(OrderRequestDto orderRequest) {
        List<Menu> menus = orderRequest.getMenus();
        LocalDate orderDate = orderRequest.getOrderDate();

        if (orderDate.getMonth().getValue() != 12 || orderDate.getYear() != 2023) {
            return getNoDiscount(orderDate);
        }

        if (isWeekendDiscount(orderDate)) {
            return new DiscountResponseDto(calculateWeekendDiscount(menus), WEEKEND_DISCOUNT_NAME);
        }

        return new DiscountResponseDto(calculateWeekDayDiscount(menus), WEEKDAY_DISCOUNT_NAME);
    }

    private DiscountResponseDto getNoDiscount(LocalDate orderDate) {
        if (isWeekendDiscount(orderDate)) {
            return new DiscountResponseDto(0, WEEKEND_DISCOUNT_NAME);
        }

        return new DiscountResponseDto(0, WEEKDAY_DISCOUNT_NAME);
    }

    private boolean isWeekendDiscount(LocalDate orderDate) {
        if (orderDate.getDayOfWeek().equals(DayOfWeek.FRIDAY) || orderDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            return true;
        }

        return false;
    }

    private int calculateWeekDayDiscount(List<Menu> menus){
        int totalDiscount = 0;

        for (Menu menu : menus) {
            if (menu instanceof DessertMenu) {
                totalDiscount += 2023;
            }
        }

        return totalDiscount;
    }

    private int calculateWeekendDiscount(List<Menu> menus){
        int totalDiscount = 0;

        for (Menu menu : menus) {
            if (menu instanceof MainFoodMenu) {
                totalDiscount += 2023;
            }
        }

        return totalDiscount;
    }

}
