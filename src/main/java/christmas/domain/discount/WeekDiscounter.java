package christmas.domain.discount;

import christmas.domain.discount.dto.DiscountRequestDto;
import christmas.domain.discount.dto.DiscountResponseDto;
import christmas.domain.discount.dto.WeekDiscountRequestDto;
import christmas.domain.menu.DessertMenu;
import christmas.domain.menu.MainFoodMenu;
import christmas.domain.menu.Menu;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class WeekDiscounter implements Discounter{

    private final String WEEKEND_DISCOUNT_NAME = "주말 할인";
    private final String WEEKDAY_DISCOUNT_NAME = "평일 할인";
    @Override
    public DiscountResponseDto discount(DiscountRequestDto discountRequestDto) {
        if (!(discountRequestDto instanceof WeekDiscountRequestDto weekDiscountRequest)) {
            throw new IllegalArgumentException("올바르지 않은 요청입니다.");
        }
        List<Menu> menus = weekDiscountRequest.getMenus();
        LocalDate orderDate = weekDiscountRequest.getOrderDate();

        if (isWeekendDiscount(orderDate)) {
            return new DiscountResponseDto(calculateWeekendDiscount(menus), WEEKEND_DISCOUNT_NAME);
        }
        else if (isWeekDayDiscount(orderDate)) {
            return new DiscountResponseDto(calculateWeekDayDiscount(menus), WEEKDAY_DISCOUNT_NAME);
        }
        else {
            throw new IllegalArgumentException("올바르지 않은 할인입니다.");
        }
    }

    private boolean isWeekendDiscount(LocalDate orderDate) {
        if (orderDate.getDayOfWeek().equals(DayOfWeek.FRIDAY) || orderDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            return true;
        }

        return false;
    }

    private boolean isWeekDayDiscount(LocalDate orderDate) {
        if (!(orderDate.getDayOfWeek().equals(DayOfWeek.FRIDAY) || orderDate.getDayOfWeek().equals(DayOfWeek.SATURDAY))) {
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
