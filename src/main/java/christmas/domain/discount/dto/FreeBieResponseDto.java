package christmas.domain.discount.dto;

import christmas.domain.menu.Menu;

import java.util.Optional;

public class FreeBieResponseDto extends DiscountResponseDto{

    private final Optional<Menu> freeBieMenu;

    public FreeBieResponseDto(Optional<Menu> freeBieMenu, String discountName) {
        super(freeBieMenu.orElse(new FreeMenu()).getPrice(), discountName);
        this.freeBieMenu = freeBieMenu;
    }

    public Optional<Menu> getFreeBieMenu() {
        return freeBieMenu;
    }

    static private class FreeMenu implements Menu {
        

        @Override
        public int getPrice() {
            return 0;
        }

        @Override
        public String getName() {
            return null;
        }

        @Override
        public String getPrintPrice() {
            return null;
        }
    }
}
