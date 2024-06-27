package gift.model;

import gift.customException.InvalidIdException;
import gift.customException.NoSuchProductIdException;
import gift.domain.ProductDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository //DB 시뮬레이션
public class ProductModel {

    private final List<ProductDTO> productList = new ArrayList<>(); //DB 시뮬레이션

    public synchronized void addProduct(ProductDTO product)
            throws InvalidIdException { //C

        if(product.getName().isBlank() || product.getPrice() == null || product.getImageUrl().isBlank())
            throw new InvalidIdException("입력 값에 빈 곳이 있습니다. 다시 제대로 입력해주세요");

        if(product.getId() != null)
            throw new InvalidIdException("id를 입력하지 말아주세요.\n저희가 알아서 추가합니다.");

        if(productList.isEmpty()){
            product.setId(1);
            productList.add(product);
            return;
        }

        int lastIndexId = productList.getLast().getId();
        product.setId(lastIndexId + 1); //auto increment
        productList.add(product);
    }

    public synchronized List<ProductDTO> getProductList() { //R
        return new ArrayList<>(productList); //방어적 복사
    }

    public synchronized void updateProduct(Integer id, ProductDTO product)
            throws NoSuchProductIdException, InvalidIdException { //U

        if (!Objects.equals(product.getId(), id)) {
            throw new InvalidIdException("올바르지 않은 id입니다.");
        }

        Optional<ProductDTO> optionalProduct = productList.stream()
                .filter(p -> id.equals(p.getId()))
                .findFirst();

        if (optionalProduct.isEmpty()) {
            throw new NoSuchProductIdException("id가 %d인 상품은 존재하지 않습니다.".formatted(id));
        }
        int index = productList.indexOf(optionalProduct.get());
        productList.set(index, product);
    }

    public synchronized void deleteProduct(Integer id) throws NoSuchProductIdException { //D
        boolean removed = productList.removeIf(productDTO -> productDTO.getId().equals(id));

        if (!removed) {
            throw new NoSuchProductIdException("id가 %d인 상품은 존재하지 않습니다.".formatted(id));
        }
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
