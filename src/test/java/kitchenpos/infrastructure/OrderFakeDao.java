package kitchenpos.infrastructure;

import java.util.List;
import java.util.stream.Collectors;
import kitchenpos.domain.order.Order;
import kitchenpos.domain.order.OrderDao;

public class OrderFakeDao extends BaseFakeDao<Order> implements OrderDao {

    @Override
    public List<Order> findByOrderTableId(final Long orderTableId) {
        return entities.values()
                .stream()
                .filter(order -> order.getOrderTableId().equals(orderTableId))
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByOrderTableIdAndOrderStatusIn(final Long orderTableId,
                                                        final List<String> orderStatuses) {
        final var found = entities.values()
                .stream()
                .filter(order -> order.getOrderTableId().equals(orderTableId)
                        && orderStatuses.contains(order.getOrderStatus().name()))
                .findAny();
        return found.isPresent();
    }

    @Override
    public boolean existsByOrderTableIdInAndOrderStatusIn(final List<Long> orderTableIds,
                                                          final List<String> orderStatuses) {
        final var found = entities.values()
                .stream()
                .filter(order -> orderTableIds.contains(order.getOrderTableId())
                        && orderStatuses.contains(order.getOrderStatus().name()))
                .findAny();
        return found.isPresent();
    }
}
