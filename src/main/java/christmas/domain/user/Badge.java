package christmas.domain.user;

import java.util.Optional;

public enum Badge {
    STAR(5_000, "별"),
    TREE(10_000, "트리"),
    SANTA(20_000, "산타");

    private final int minPrice;
    private final String message;

    Badge(int minPrice, String message) {
        this.minPrice = minPrice;
        this.message = message;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public String getMessage() {
        return message;
    }

    public static Optional<Badge> findByPrice(int discountPrice) {
        Badge result = null;

        for (Badge badge : Badge.values()) {
            if (result == null && badge.getMinPrice() <= discountPrice) {
                result = badge;
            }
            else if (badge.getMinPrice() <= discountPrice && badge.getMinPrice() >= result.getMinPrice() ) {
                result = badge;
            }
        }

        if (result == null) {
            return Optional.empty();
        }
        return Optional.of(result);
    }
}
