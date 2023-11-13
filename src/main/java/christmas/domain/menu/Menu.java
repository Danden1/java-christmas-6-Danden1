package christmas.domain.menu;

public interface Menu {

    Menu findByName(String menuName);
    int getPrice();
    String getName();
    String getPrintPrice();
}
