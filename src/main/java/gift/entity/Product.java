package gift.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Product {

    private Integer id;

    @NotBlank(message = "상품 이름을 입력해주세요.")
    private String name;

    @NotNull(message = "가격을 입력해주세요")
    @Min(value = 0, message = "가격은 0 이상이여야 합니다.")
    private Integer price;

    @NotBlank(message = "이미지 URL을 입력해주세요.")
    private String imageUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
