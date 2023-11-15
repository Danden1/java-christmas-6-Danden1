package christmas.domain.menu;


import christmas.domain.order.OrderErrorMessage;

public enum DessertMenu implements Menu {
    CHOCO_CAKE(15_000, "15,000", "초코케이크"),
    ICE_CREAM(5_000, "5,000", "아이스크림");

    private final int price;
    private final String printPrice;
    private final String name;

    DessertMenu(int price, String printPrice, String name) {
        this.price = price;
        this.printPrice = printPrice;
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

    @Override
    public String getPrintPrice() {
        return printPrice;
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
