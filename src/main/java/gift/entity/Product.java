package gift.entity;

import gift.dto.ProductDTO;
import gift.exception.BlankContentException;
import gift.exception.CustomException;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class Product {

    private Integer id;

    @NotBlank(message = "상품 이름을 입력해주세요.")
    private String name;

    @NotNull(message = "가격을 입력해주세요")
    @Min(value = 0, message = "가격은 0 이상이여야 합니다.")
    private Integer price;

    @NotBlank(message = "이미지 URL을 입력해주세요.")
    private String imageUrl;

    public Product(){}

    public Product(Integer id, String name, Integer price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Product(ProductDTO productDTO) {
        blankCatch(productDTO);
        this.id = productDTO.id();
        this.name = productDTO.name();
        this.price = productDTO.price();
        this.imageUrl = productDTO.imageUrl();
    }

    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    private void setPrice(Integer price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    private void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private void blankCatch(ProductDTO productDTO) throws BlankContentException {
        if (productDTO.name().isBlank() || productDTO.price() == null || productDTO.imageUrl()
                .isBlank()) {
            throw new BlankContentException("입력 값에 빈 곳이 있습니다. 다시 입력해주세요");
        }
    }
}
