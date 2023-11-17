package christmas.domain.menu;


import christmas.domain.order.OrderErrorMessage;

public enum DrinkMenu implements Menu {

    ZERO_COKE(3_000, "제로콜라"),
    RED_WINE(60_000, "레드와인"),
    CHAMPAGNE(25_000, "샴페인");

    private final int price;
    private final String name;

    DrinkMenu(int price, String name) {
        this.price = price;
        this.name = name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return name;
    }



    public static Menu findByName(String menuName) {
        for (DrinkMenu drinkMenu : DrinkMenu.values()) {
            if (drinkMenu.getName().equals(menuName)) {
                return drinkMenu;
            }
        }
        throw new IllegalArgumentException(OrderErrorMessage.INVALID_ORDER.getMessage());
    }
}
