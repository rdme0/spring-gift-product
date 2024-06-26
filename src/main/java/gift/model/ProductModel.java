package gift.model;

import gift.customException.DuplicatedProductIdException;
import gift.customException.NotFoundSuchIndexException;
import gift.domain.ProductDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ProductModel {

    private final List<ProductDTO> products = new ArrayList<>(); //DB 시뮬레이션

    public void addProduct(ProductDTO product) throws DuplicatedProductIdException { //C
        checkDuplicate(product);
        products.add(product);
    }

    public List<ProductDTO> getProducts() { //R
        return products;
    }

    public void updateProduct(Integer index, ProductDTO product)
            throws NotFoundSuchIndexException, DuplicatedProductIdException { //U

        if (products.size() < index + 1) {
            throw new NotFoundSuchIndexException("%d번째 상품은 존재하지 않습니다.".formatted(index + 1));
        }
        checkDuplicate(product);
        products.set(index, product);

    }

    public void deleteProduct(Integer index) throws NotFoundSuchIndexException { //D
        if (products.size() < index + 1) {
            throw new NotFoundSuchIndexException("%d번째 상품은 존재하지 않습니다.".formatted(index + 1));
        }
        products.remove(index.intValue());
    }


    private void checkDuplicate(ProductDTO product) {
        for (ProductDTO productDTO : products) {
            if (productDTO.getId() == product.getId()) {
                throw new DuplicatedProductIdException("이미 있는 상품 id 입니다.");
            }
        }
    }


}
