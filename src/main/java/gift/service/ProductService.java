package gift.service;

import gift.customException.InvalidIdException;
import gift.customException.NoSuchProductIdException;
import gift.customException.NullContentException;
import gift.dao.ProductDao;
import gift.domain.ProductDTO;
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
            throws RuntimeException{ //C

        if(productDTO.getName().isBlank() || productDTO.getPrice() == null || productDTO.getImageUrl().isBlank())
            throw new NullContentException("입력 값에 빈 곳이 있습니다. 다시 제대로 입력해주세요");

        if(productDTO.getId() != null)
            throw new InvalidIdException("id를 입력하지 말아주세요. 저희가 알아서 추가합니다.");

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
            throws RuntimeException { //U

        if (!Objects.equals(product.getId(), id)) {
            throw new InvalidIdException("올바르지 않은 id입니다.");
        }

        if (productDao.updateProduct(product) == 0) {
            throw new NoSuchProductIdException("id가 %d인 상품은 존재하지 않습니다.".formatted(id));
        }
    }

    public void deleteProduct(Integer id) throws RuntimeException { //D
        if(productDao.deleteProduct(id) == 0)
            throw new NoSuchProductIdException("id가 %d인 상품은 존재하지 않습니다.".formatted(id));
    }


}
