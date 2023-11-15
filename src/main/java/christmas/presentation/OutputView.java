package christmas.presentation;

import christmas.domain.discount.Discounter;
import christmas.domain.discount.dto.DiscountResponseDto;
import christmas.domain.discount.dto.FreeBieResponseDto;
import christmas.domain.menu.Menu;
import christmas.domain.order.OrderResponseDto;
import christmas.domain.user.User;

import java.sql.SQLOutput;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutputView {

    private final NumberFormat numberFormat = NumberFormat.getInstance();


    public void printStart() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printOrderResult(OrderResponseDto orderResponseDto) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(numberToString(orderResponseDto.getPriceBeforeDiscount()) + "원");
        System.out.println();
        System.out.println("<증정 메뉴>");
        printFreebie(orderResponseDto);
        System.out.println("<혜택 내역>");
        printDiscount(orderResponseDto);
        System.out.println("<총혜택 금액>");
        System.out.println("-" + numberToString(orderResponseDto.getDiscountPrice()) + "원");
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(numberToString(orderResponseDto.getPriceAfterDiscount())+"원");
        System.out.println();
    }

    public void printBadge(User user) {
        System.out.println("<12월 이벤트 배지>");
        if (user.getBadge().isEmpty()) {
            System.out.println("없음");
            return;
        }
        System.out.println(user.getBadge().get().getMessage());
    }

    public void printOrderDate(LocalDate date) {
        System.out.println(date.getMonthValue() +"월 " + date.getDayOfMonth() +"일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();
    }

    private void printDiscount(OrderResponseDto orderResponseDto) {
        boolean noneFlag = true;
        for (DiscountResponseDto tmp : orderResponseDto.getDiscountResults()) {
            if (tmp.getTotalDiscount() == 0) {
                continue;
            }
            noneFlag = false;
            System.out.println(tmp.getDiscountName() + ": -" + numberToString(tmp.getTotalDiscount()) + "원");
        }

        if (noneFlag) {
            System.out.println("없음");
        }

        System.out.println();
    }

    private void printFreebie(OrderResponseDto orderResponseDto) {
        for (DiscountResponseDto discount : orderResponseDto.getDiscountResults()) {
            if (discount instanceof FreeBieResponseDto freeBieResponseDto) {
                printFreebie(freeBieResponseDto);
            }
        }
        System.out.println();
    }

    private void printFreebie(FreeBieResponseDto freeBie) {
        if (freeBie.getFreeBieMenu().isEmpty()) {
            System.out.println("없음");
            return;
        }

        System.out.println(freeBie.getFreeBieMenu().get().getName()+ " 1개");
    }


    public void printOrder(List<Menu> menus) {
        System.out.println("<주문 메뉴>");
        printFoods(menus);
        System.out.println();
    }

    private void printFoods(List<Menu> menus) {
        Map<String, Integer> foods = new HashMap<>();

        for (Menu menu : menus) {
            if (foods.containsKey(menu.getName())) {
                foods.put(menu.getName(), foods.get(menu.getName()) + 1);
                continue;
            }
            foods.put(menu.getName(), 1);
        }

        for (String name : foods.keySet()) {
            System.out.println(name + ' ' + foods.get(name) + '개');
        }
    }

    private String numberToString(int num) {
        return numberFormat.format(num);
    }
}
