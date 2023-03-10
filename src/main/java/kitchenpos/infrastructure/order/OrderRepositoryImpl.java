package kitchenpos.infrastructure.order;

import java.util.List;
import java.util.stream.Collectors;
import kitchenpos.domain.order.Order;
import kitchenpos.domain.order.OrderDao;
import kitchenpos.domain.order.OrderLineItem;
import kitchenpos.domain.order.OrderLineItemDao;
import kitchenpos.domain.order.OrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderDao orderDao;
    private final OrderLineItemDao orderLineItemDao;

    public OrderRepositoryImpl(final OrderDao orderDao,
                               final OrderLineItemDao orderLineItemDao) {
        this.orderDao = orderDao;
        this.orderLineItemDao = orderLineItemDao;
    }

    @Override
    public Order add(final Order order) {
        final Order savedOrder = orderDao.save(order);

        final var savedOrderLineItems = saveAllOrderLineItems(order.getOrderLineItems(), savedOrder.getId());

        return new Order(
                savedOrder.getId(),
                savedOrder.getOrderTableId(),
                savedOrder.getOrderStatus(),
                savedOrder.getOrderedTime(),
                savedOrderLineItems
        );
    }

    private List<OrderLineItem> saveAllOrderLineItems(final List<OrderLineItem> orderLineItems, final Long orderId) {
        return orderLineItems.stream()
                .map(orderLineItem -> {
                    final var entity = new OrderLineItem(
                            orderId,
                            orderLineItem.getMenuId(),
                            orderLineItem.getQuantity()
                    );
                    return orderLineItemDao.save(entity);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Order get(final Long id) {
        final var orderLineItems = orderLineItemDao.findAllByOrderId(id);
        final var order = orderDao.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        return new Order(
                id,
                order.getOrderTableId(),
                order.getOrderStatus(),
                order.getOrderedTime(),
                orderLineItems
        );
    }

    @Override
    public List<Order> getByOrderTableId(final Long orderTableId) {
        final var orders = orderDao.findByOrderTableId(orderTableId);

        return fetchOrderLineItems(orders);
    }

    @Override
    public List<Order> getOrderTableIdsIn(final List<Long> orderTableIds) {
        final var orders = orderTableIds.stream()
                .map(orderDao::findByOrderTableId)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        return fetchOrderLineItems(orders);
    }

    private List<Order> fetchOrderLineItems(final List<Order> orders) {
        return orders.stream()
                .map(order -> {
                    final var orderLineItems = orderLineItemDao.findAllByOrderId(order.getId());
                    return new Order(
                            order.getId(),
                            order.getOrderTableId(),
                            order.getOrderStatus(),
                            order.getOrderedTime(),
                            orderLineItems
                    );
                }).collect(Collectors.toList());
    }

    @Override
    public List<Order> getAll() {
        final List<Order> orders = orderDao.findAll();

        return fetchOrderLineItems(orders);
    }
}
