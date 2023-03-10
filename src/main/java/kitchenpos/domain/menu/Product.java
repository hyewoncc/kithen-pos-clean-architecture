package kitchenpos.domain.menu;

import java.math.BigDecimal;
import kitchenpos.domain.Entity;

public class Product implements Entity {

    private Long id;
    private String name;
    private BigDecimal price;

    public Product(final String name,
                   final BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
        if (isNew()) {
            validateOnCreate();
        }
    }

    public Product(final Long id,
                   final String name,
                   final BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
        if (isNew()) {
            validateOnCreate();
        }
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

    @Override
    public void validateOnCreate() {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
