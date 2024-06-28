package gift.domain;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record ProductDTO(
        @Null(message = "id를 입력하지 말아주세요. 저희가 알아서 추가합니다.") Integer id,
        @NotBlank(message = "상품 이름을 입력해주세요.") String name,
        @NotNull(message = "가격을 입력해주세요") @Min(value = 0, message = "가격은 0 이상이여야 합니다.") Integer price,
        @NotBlank(message = "이미지 URL을 입력해주세요.") String imageUrl
) {}
