package kitchenpos.domain.menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import kitchenpos.domain.Entity;

public class Menu implements Entity {

    private Long id;
    private String name;
    private BigDecimal price;
    private Long menuGroupId;
    private List<MenuProduct> menuProducts;

    public Menu(final Long id,
                final String name,
                final BigDecimal price,
                Long menuGroupId) {
        this(id, name, price, menuGroupId, new ArrayList<>());
    }

    public Menu(final String name,
                final BigDecimal price,
                final Long menuGroupId,
                final List<MenuProduct> menuProducts) {
        this(null, name, price, menuGroupId, menuProducts);
    }

    public Menu(final Long id,
                final String name,
                final BigDecimal price,
                final Long menuGroupId,
                final List<MenuProduct> menuProducts) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.menuGroupId = menuGroupId;
        this.menuProducts = menuProducts;
        if (isNew()) {
            validateOnCreate();
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

    public Long getMenuGroupId() {
        return menuGroupId;
    }

    public List<MenuProduct> getMenuProducts() {
        return menuProducts;
    }

    public void setMenuProducts(final List<MenuProduct> menuProducts) {
        this.menuProducts = menuProducts;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

    @Override
    public void validateOnCreate() {
        validatePrice();
    }

    private void validatePrice() {
        final var total = menuProducts.stream()
                .map(MenuProduct::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (price.compareTo(total) > 0) {
            throw new IllegalArgumentException();
        }
    }
}
