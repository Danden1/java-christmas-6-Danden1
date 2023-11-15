package christmas.domain.order;

public enum OrderErrorMessage {
    INVALID_ORDER("[ERROR] 유효하지 않은 주문입니다. 다시 입력해주세요.");

    private final String message;

    OrderErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
