package gift.service;

import gift.exception.InvalidIdException;
import gift.exception.NoSuchProductIdException;
import gift.dao.ProductDao;
import gift.dto.ProductDTO;
import gift.exception.NullContentException;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductDao productDao;

    @Autowired //생성자 주입
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void addProduct(ProductDTO productDTO)
            throws RuntimeException{

        if(productDTO.name().isBlank() || productDTO.price() == null || productDTO.imageUrl().isBlank())
            throw new NullContentException("입력 값에 빈 곳이 있습니다. 다시 입력해주세요");

        try {
            productDao.insertProduct(productDTO);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public List<ProductDTO> getProductList() {//R
        return productDao.selectProduct();
    }

    public void updateProduct(Integer id, ProductDTO product)
            throws RuntimeException {

        if (!Objects.equals(product.id(), id)) {
            throw new InvalidIdException("올바르지 않은 id입니다.");
        }

        if (productDao.updateProduct(product) == 0) {
            throw new NoSuchProductIdException("id가 %d인 상품은 존재하지 않습니다.".formatted(id));
        }
    }

    public void deleteProduct(Integer id) throws RuntimeException {
        if(productDao.deleteProduct(id) == 0)
            throw new NoSuchProductIdException("id가 %d인 상품은 존재하지 않습니다.".formatted(id));
    }


}
