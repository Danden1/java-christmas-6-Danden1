package christmas.domain.menu;


public enum DrinkMenu implements Menu {

    ZERO_COKE(3_000, "3,000", "제로콜라"),
    RED_WINE(60_000, "60,000", "레드와인"),
    CHAMPAGNE(25_000, "25,000", "샴페인");

    private final int price;
    private final String printPrice;
    private final String name;

    DrinkMenu(int price, String printPrice, String name) {
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
        for (DrinkMenu drinkMenu : DrinkMenu.values()) {
            if (drinkMenu.getName().equals(menuName)) {
                return drinkMenu;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 주문입니다.");
    }
}
