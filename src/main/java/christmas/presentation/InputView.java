package christmas.presentation;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.menu.Menu;
import christmas.domain.order.OrderErrorMessage;

import java.time.LocalDate;
import java.util.*;

public class InputView {

    public LocalDate inputDate() throws IllegalArgumentException {

        try {
            System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
            int date = Integer.parseInt(Console.readLine());

            if (date < 1 || date > 31) {
                throw new IllegalArgumentException(ViewErrorMessage.INVALID_DATE_FORMAT.getMessage());
            }

            return LocalDate.of(2023,12,date);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(ViewErrorMessage.INVALID_DATE_FORMAT.getMessage());
        }
    }

    public Map<String, Integer> inputMenus() throws IllegalArgumentException {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String userInput = Console.readLine();
        try {
            Map<String, Integer> result = parsingMenus(userInput);
            if (result.keySet().size() == 0) {
                throw new IllegalArgumentException(OrderErrorMessage.INVALID_ORDER.getMessage());
            }

            return result;
        } catch (IllegalArgumentException ex) {
            throw ex;
        }
    }



    private Map<String, Integer> parsingMenus(String userOrder) throws IllegalArgumentException {
        Map<String,Integer> menus = new HashMap<>();
        Set<String> orderMenu = new HashSet<>();

        List<String> splitMenus = List.of(userOrder.split(","));
        for (String splitMenu : splitMenus) {
            Map<String, Integer> tmpResult = parsingMenu(splitMenu);
            String tmpMenuName = tmpResult.keySet().iterator().next();

            if (orderMenu.contains(tmpMenuName)) {
                throw new IllegalArgumentException(OrderErrorMessage.INVALID_ORDER.getMessage());
            }
            orderMenu.add(tmpMenuName);
            menus.put(tmpMenuName, tmpResult.get(tmpMenuName));
        }
        return menus;
    }

    private Map<String, Integer> parsingMenu(String menu) {
        Map<String, Integer> parsingResult = new HashMap<>();
        int i = menu.lastIndexOf("-");

        if (i == -1) {
            throw new IllegalArgumentException(OrderErrorMessage.INVALID_ORDER.getMessage());
        }

        try {
            parsingResult.put(menu.substring(0,i), Integer.parseInt(menu.substring(i + 1)));
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(OrderErrorMessage.INVALID_ORDER.getMessage());
        } catch (Exception ex) {
            throw new IllegalArgumentException(OrderErrorMessage.INVALID_ORDER.getMessage());
        }

        return parsingResult;
    }
}
