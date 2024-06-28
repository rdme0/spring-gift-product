# spring-gift-product
### 기능 요구 사항
##### 자바 컬렉션 프레임워크를 사용하여 메모리에 저장하던 상품 정보를 데이터베이스에 저장한다.

### 프로그래밍 요구 사항
##### 메모리에 저장하고 있던 모든 코드를 제거하고 H2 데이터베이스를 사용하도록 변경한다.
##### 사용하는 테이블은 애플리케이션이 실행될 때 구축되어야 한다.

### 구조
1. MVC 패턴으로 구현하였으며 모델의 책임을 Dao와 Service로 분리하였다.
2. Controller - Service - Dao 간에는 ProductDTO를 전달할 수 있도록 구현하였다.
3. Controller가 ResponseDTO 또는 ProductDTO로 응답하도록 구현하였다.

### 요구사항 내 구현 기능
- 조회
- 추가
- 수정
- 삭제
### 추가 구현 기능
- request의 유효성 검사를 하는 기능을 구현

~~-  model이 thread-safe하게 구현~~ -> h2 데이터베이스로 대체
- HttpStatus를 서버가 처리를 성공할 때와 실패할 때 등을 분류하여 리턴하도록 구현

### 튜토리얼
https://github.com/rdme0/spring-gift-product/assets/71927381/4a828082-bba2-46fd-8ab7-57363b67baa8

