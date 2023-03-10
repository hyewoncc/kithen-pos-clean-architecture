package kitchenpos.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import kitchenpos.application.FakeSpringContext;
import kitchenpos.domain.table.OrderTable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTableTest extends FakeSpringContext {

    @DisplayName("손님의 수는 음수가 될 수 없다")
    @Test
    void numberOfGuestUnderZero_throwsException() {
        final var invalidNumberOfGuests = -1;

        assertThatThrownBy(
                () -> new OrderTable(null, null, invalidNumberOfGuests, true)
        );
    }

    @DisplayName("그룹 id를 갖고 있다면, 비어있는지 여부를 true로 변경할 수 없다")
    @Test
    void hasTableGroupId_changeEmptyToTrue_throwsException() {
        final var table = new OrderTable(null, 1L, 2, true);

        assertThatThrownBy(
                () -> table.changeEmptyTo(orderTableValidator, true)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
