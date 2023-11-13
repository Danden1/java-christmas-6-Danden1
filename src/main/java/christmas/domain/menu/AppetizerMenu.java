package christmas.domain.menu;


public enum AppetizerMenu implements Menu {

    BUTTON_MUSHROOM_SOUP(6_000, "6,000", "양송이수프"),
    TAPAS(5_500, "5,500", "타파스"),
    CAESAR_SALAD(8_000, "8,000", "시저샐러드");

    private final int price;
    private final String printPrice;
    private final String name;

    AppetizerMenu(int price, String printPrice, String name) {
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

    @Override
    public Menu findByName(String menuName) {
        for (AppetizerMenu appetizerMenu : AppetizerMenu.values()) {
            if (appetizerMenu.getName().equals(menuName)) {
                return appetizerMenu;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 주문입니다.");
    }
}
