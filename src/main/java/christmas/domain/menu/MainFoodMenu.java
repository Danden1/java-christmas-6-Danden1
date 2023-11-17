package christmas.domain.menu;

import christmas.domain.order.OrderErrorMessage;

public enum MainFoodMenu implements Menu {

    T_BONE_STEAK(55_000, "티본스테이크"),
    BARBECUE_RIBS(54_000, "바비큐립"),
    SEAFOOD_PASTA(35_000, "해산물파스타"),
    CHRISTMAS_PASTA(25_000, "크리스마스파스타");


    private final int price;
    private final String name;

    MainFoodMenu(int price, String name) {
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
        for (MainFoodMenu mainFoodMenu : MainFoodMenu.values()) {
            if (mainFoodMenu.getName().equals(menuName)) {
                return mainFoodMenu;
            }
        }
        throw new IllegalArgumentException(OrderErrorMessage.INVALID_ORDER.getMessage());
    }
}
