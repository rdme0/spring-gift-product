# spring-gift-product
### 기능 요구 사항
##### 상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.

- HTTP 요청과 응답은 JSON 형식으로 주고받는다.
- 현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.

### 구조
1. MVC 패턴과 비슷하게 구현했으며 ProductModel은 ArrayList 형태로 데이터를 저장하는 방식으로 DB와 비슷한 느낌을 내도록 하였다.
2. Controller와 Model간에는 ProductDTO를 전달할 수 있도록 구현하였다.
3. 컨트롤러가 ResponseDTO로 응답하거나, ProductDTO로 응답하도록 구현하였다.

### 요구사항 내 구현 기능
- 조회
- 추가
- 수정
- 삭제
### 추가 구현 기능
- request의 유효성 검사를 하는 기능을 구현
- model이 thread-safe하게 구현
- HttpStatus를 서버가 처리를 성공할 때와 실패할 때 등을 분류하여 리턴하도록 구현