package christmas.domain.menu;


import christmas.domain.order.OrderErrorMessage;

public enum DessertMenu implements Menu {
    CHOCO_CAKE(15_000, "초코케이크"),
    ICE_CREAM(5_000, "아이스크림");

    private final int price;
    private final String name;

    DessertMenu(int price, String name) {
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
        for (DessertMenu dessertMenu : DessertMenu.values()) {
            if (dessertMenu.getName().equals(menuName)) {
                return dessertMenu;
            }
        }
        throw new IllegalArgumentException(OrderErrorMessage.INVALID_ORDER.getMessage());
    }
}
