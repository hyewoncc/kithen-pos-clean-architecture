# 🧑‍🍳 키친포스

## 요구 사항  

### 상품  
- 상품을 등록한다  
  - 상품 가격이 올바르지 않으면 등록할 수 없다  
    - 가격은 `null` 또는 `0 미만`이 될 수 없다
- 모든 상품의 목록을 조회한다  

### 메뉴  
- 메뉴를 등록한다  
  - 메뉴 가격이 올바르지 않으면 등록할 수 없다  
    - 가격은 `null` 또는 `0 미만`이 될 수 없다  
    - 가격은 각각의 상품 총 가격(가격*수량)보다 클 수 없다  
  - 메뉴 그룹이 올바르지 않으면 등록할 수 없다  
    - 메뉴 그룹이 존재하지 않는다면 등록할 수 없다   
- 모든 메뉴의 목록을 조회한다   

### 메뉴 그룹
- 메뉴 그룹을 등록한다    
- 모든 메뉴 그룹의 목록을 조회한다  

### 주문
- 주문을 등록한다  
  - 주문 항목이 올바르지 않으면 등록할 수 없다  
    - 주문 항목은 비어서는 안 된다  
    - 주문 항목의 메뉴는 중복될 수 없다  
  - 주문 테이블이 올바르지 않으면 등록할 수 없다  
    - 주문 테이블이 존재하지 않는다면 등록할 수 없다  
    - 주문 테이블의 상태는 빈 테이블이면 안된다    
- 모든 주문의 목록을 조회한다   
- 주문 상태를 변경한다  
  - 주문 상태가 `COMPLETION`이라면 변경할 수 없다  

### 테이블
- 주문 테이블을 등록한다   
- 모든 주문 테이블의 목록을 조회한다  
- 주문 테이블의 정보를 변경한다  
  - 빈 테이블인지 여부를 변경한다  
    - 단체 지정 된 주문 테이블은 변경할 수 없다  
    - 주문 상태가 `COMPLETION`이 아니라면 변경할 수 없다
  - 고객 수를 변경한다  
    - 고객 수는 `0 미만`으로 변경할 수 없다  
    - 상태가 빈 테이블이라면 변경할 수 없다  

### 단체 지정
- 단체 지정을 등록한다  
  - 주문 테이블 목록이 올바르지 않으면 등록할 수 없다  
    - 목록의 주문 테이블 수는 `0 또는 1 이하`가 될 수 없다  
    - id가 중복되는 주문 테이블이 있을 시 등록할 수 없다  
    - 주문 테이블 중 하나라도 빈 테이블 상태여서는 안된다  
    - 주문 테이블 중 하나라도 이미 단체 지정에 속해있어서는 안된다  
- 단체 지정을 삭제한다  
  - 주문 테이블 목록의 상태가 올바르지 않으면 삭제할 수 없다  
    - 모든 주문 테이블이 `COMPLETION` 상태여야 한다  

## 용어 사전

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 상품 | product | 메뉴를 관리하는 기준이 되는 데이터 |
| 메뉴 그룹 | menu group | 메뉴 묶음, 분류 |
| 메뉴 | menu | 메뉴 그룹에 속하는 실제 주문 가능 단위 |
| 메뉴 상품 | menu product | 메뉴에 속하는 수량이 있는 상품 |
| 금액 | amount | 가격 * 수량 |
| 주문 테이블 | order table | 매장에서 주문이 발생하는 영역 |
| 빈 테이블 | empty table | 주문을 등록할 수 없는 주문 테이블 |
| 주문 | order | 매장에서 발생하는 주문 |
| 주문 상태 | order status | 주문은 조리 ➜ 식사 ➜ 계산 완료 순서로 진행된다. |
| 방문한 손님 수 | number of guests | 필수 사항은 아니며 주문은 0명으로 등록할 수 있다. |
| 단체 지정 | table group | 통합 계산을 위해 개별 주문 테이블을 그룹화하는 기능 |
| 주문 항목 | order line item | 주문에 속하는 수량이 있는 메뉴 |
| 매장 식사 | eat in | 포장하지 않고 매장에서 식사하는 것 |
