package christmas.domain.menu;

import christmas.domain.food.Dessert;

public enum DessertMenu implements Dessert, Menu {
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

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getPrintPrice() {
        return printPrice;
    }

    @Override
    public Menu findByName(String menuName) {
        for (DessertMenu dessertMenu : DessertMenu.values()) {
            if (dessertMenu.getName().equals(menuName)) {
                return dessertMenu;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 주문입니다.");
    }
}
