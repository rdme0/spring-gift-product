package gift.model;

import gift.customException.DuplicatedProductIdException;
import gift.customException.InvalidIdException;
import gift.customException.NotFoundSuchProductIdException;
import gift.domain.ProductDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository //DB 시뮬레이션
public class ProductModel {

    private final List<ProductDTO> productList = new ArrayList<>(); //DB 시뮬레이션

    public synchronized void addProduct(ProductDTO product) throws DuplicatedProductIdException { //C
        if(isDuplicateId(product))
            throw new DuplicatedProductIdException("이미 있는 상품 id 입니다.");
        productList.add(product);
    }

    public synchronized  List<ProductDTO> getProductList() { //R
        return new ArrayList<>(productList); //방어적 복사
    }

    public synchronized void updateProduct(Integer id, ProductDTO product)
            throws NotFoundSuchProductIdException, InvalidIdException { //U

        if (!Objects.equals(product.getId(), id)) {
            throw new InvalidIdException("올바르지 않은 id입니다.");
        }

        Optional<ProductDTO> optionalProduct = productList.stream()
                .filter(p -> id.equals(p.getId()))
                .findFirst();

        if (optionalProduct.isEmpty()) {
            throw new NotFoundSuchProductIdException("id가 %d인 상품은 존재하지 않습니다.".formatted(id));
        }
        int index = productList.indexOf(optionalProduct.get());
        productList.set(index, product);
    }

    public synchronized void deleteProduct(Integer id) throws NotFoundSuchProductIdException { //D
        boolean removed = productList.removeIf(productDTO -> productDTO.getId().equals(id));

        if(!removed)
            throw new NotFoundSuchProductIdException("id가 %d인 상품은 존재하지 않습니다.".formatted(id));
    }


    private synchronized boolean isDuplicateId(ProductDTO product) {
        for (ProductDTO productDTO : productList) {
            if (Objects.equals(productDTO.getId(), product.getId())) {
                return true;
            }
        }
        return false;
    }


}
