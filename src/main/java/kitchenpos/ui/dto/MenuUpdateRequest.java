package kitchenpos.ui.dto;

import java.math.BigDecimal;

public class MenuUpdateRequest {

    private String name;
    private BigDecimal price;

    private MenuUpdateRequest() {
    }

    public MenuUpdateRequest(final String name, final BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
