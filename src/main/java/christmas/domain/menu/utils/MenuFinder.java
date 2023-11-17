package christmas.domain.menu.utils;

import christmas.domain.menu.*;
import christmas.domain.order.OrderErrorMessage;

import java.util.Optional;

public class MenuFinder {
    public Menu findByName(String menuName) throws IllegalArgumentException {
        Optional<Menu> result = Optional.empty();

        result = findByNameAppetizerMenu(menuName);
        if (result.isPresent()) {
            return result.get();
        }

        result = findByNameDrinkMenu(menuName);
        if (result.isPresent()) {
            return result.get();
        }

        result = findByNameDessertMenu(menuName);
        if (result.isPresent()) {
            return result.get();
        }

        result = findByNameMainFoodMenu(menuName);
        if (result.isPresent()) {
            return result.get();
        }

        throw new IllegalArgumentException(OrderErrorMessage.INVALID_ORDER.getMessage());
    }

    private Optional<Menu> findByNameAppetizerMenu(String menuName) throws IllegalArgumentException {
        try {
            Menu menu = AppetizerMenu.findByName(menuName);

            return Optional.of(menu);
        } catch (IllegalArgumentException ex) {
            return Optional.empty();
        }
    }

    private Optional<Menu> findByNameMainFoodMenu(String menuName) throws IllegalArgumentException {
        try {
            Menu menu = MainFoodMenu.findByName(menuName);

            return Optional.of(menu);
        } catch (IllegalArgumentException ex) {
            return Optional.empty();
        }
    }

    private Optional<Menu> findByNameDessertMenu(String menuName) throws IllegalArgumentException {
        try {
            Menu menu = DessertMenu.findByName(menuName);

            return Optional.of(menu);
        } catch (IllegalArgumentException ex) {
            return Optional.empty();
        }
    }

    private Optional<Menu> findByNameDrinkMenu(String menuName) throws IllegalArgumentException {
        try {
            Menu menu = DrinkMenu.findByName(menuName);

            return Optional.of(menu);
        } catch (IllegalArgumentException ex) {
            return Optional.empty();
        }
    }
}
