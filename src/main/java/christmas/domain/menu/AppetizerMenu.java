package christmas.domain.menu;


import christmas.domain.order.OrderErrorMessage;

public enum AppetizerMenu implements Menu {

    BUTTON_MUSHROOM_SOUP(6_000, "양송이수프"),
    TAPAS(5_500, "타파스"),
    CAESAR_SALAD(8_000, "시저샐러드");

    private final int price;
    private final String name;

    AppetizerMenu(int price, String name) {
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
        for (AppetizerMenu appetizerMenu : AppetizerMenu.values()) {
            if (appetizerMenu.getName().equals(menuName)) {
                return appetizerMenu;
            }
        }
        throw new IllegalArgumentException(OrderErrorMessage.INVALID_ORDER.getMessage());
    }
}
