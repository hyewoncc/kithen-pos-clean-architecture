package kitchenpos.domain.table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import kitchenpos.domain.Entity;

public class TableGroup implements Entity {

    private Long id;
    private LocalDateTime createdDate;
    private List<OrderTable> orderTables;

    private TableGroup() {
    }

    public TableGroup(final Long id,
                      final LocalDateTime createdDate) {
        this(id, createdDate, new ArrayList<>());
    }

    public TableGroup(final LocalDateTime createdDate,
                      final List<OrderTable> orderTables) {
        this(null, createdDate, orderTables);
    }

    public TableGroup(final Long id, final LocalDateTime createdDate, final List<OrderTable> orderTables) {
        this.id = id;
        this.createdDate = createdDate;
        this.orderTables = orderTables;
        if (isNew()) {
            validateOnCreate();
        }
    }

    public void ungroup(final TableGroupValidator tableGroupValidator) {
        tableGroupValidator.validateOnUngroup(this);
        orderTables.forEach(OrderTable::ungroup);
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

    @Override
    public void validateOnCreate() {
        validateAllTablesCanBeGrouped();
        validateTablesNotEmptyAndLeastSizeTwo();
    }

    private void validateAllTablesCanBeGrouped() {
        final var canAllTablesBeGrouped = orderTables.stream()
                .allMatch(OrderTable::canBeGrouped);
        if (!canAllTablesBeGrouped) {
            throw new IllegalArgumentException();
        }
    }

    private void validateTablesNotEmptyAndLeastSizeTwo() {
        if (orderTables.isEmpty() || orderTables.size() < 2) {
            throw new IllegalArgumentException();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public List<OrderTable> getOrderTables() {
        return orderTables;
    }
}
