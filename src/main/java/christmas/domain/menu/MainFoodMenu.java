package christmas.domain.menu;

import christmas.domain.food.MainFood;

public enum MainFoodMenu implements MainFood, Menu {

    T_BONE_STEAK(55_000, "55,000", "티본스테이크"),
    BARBECUE_RIBS(54_000, "54,000", "바비큐립"),
    SEAFOOD_PASTA(35_000, "35,000", "해산물파스타"),
    CHRISTMAS_PASTA(25_000,"25,000", "크리스마스파스타");


    private final int price;
    private final String printPrice;
    private final String name;

    MainFoodMenu(int price, String printPrice, String name) {
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
        for (MainFoodMenu mainFoodMenu : MainFoodMenu.values()) {
            if (mainFoodMenu.getName().equals(menuName)) {
                return mainFoodMenu;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 주문입니다.");
    }
}
