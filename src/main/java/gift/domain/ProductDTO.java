package gift.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public class ProductDTO {
    @Null(message = "id를 입력하지 말아주세요. 저희가 알아서 추가합니다.")
    private Integer id;

    @NotBlank(message = "상품 이름을 입력해주세요.")
    private String name;

    @NotNull(message = "가격을 입력해주세요")
    @Min(value = 0, message = "가격은 0 이상이여야 합니다.")
    private Integer price;

    @NotBlank(message = "이미지 URL을 입력해주세요.")
    private String imageUrl;

    public ProductDTO(Integer id, String name, Integer price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
