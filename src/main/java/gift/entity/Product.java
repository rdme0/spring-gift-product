package gift.entity;

import gift.dto.ProductDTO;
import gift.exception.BlankContentException;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Product {

    private Integer id;

    private String name;

    private Integer price;

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
