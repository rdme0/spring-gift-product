package gift.controller;

import gift.customException.CustomException;
import gift.domain.ProductDTO;
import gift.model.ProductModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class ProductController {

    private final ProductUtil productUtil;
    private final ProductModel productModel;

    @Autowired //생성자 주입
    public ProductController(ProductUtil productUtil, ProductModel productModel) {
        this.productUtil = productUtil;
        this.productModel = productModel;
    }

    @GetMapping("/products")
    public List<Map<Integer, ProductDTO>> getProducts() {
        return productUtil.listIndexToKey(
                productModel.getProducts()); //리스트의 인덱스를 map의 Key + 1로 변환해주는 메서드
    }

    @PostMapping("/products")
    public ResponseEntity<String> addProduct(@RequestBody @Valid ProductDTO productDTO) {
        try {
            productModel.addProduct(productDTO);
        } catch (RuntimeException e) {
            if(e instanceof CustomException) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("확인되지 않은 에러입니다. 관리자에게 문의 주세요", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable @Min(1) @NotNull Integer id) {
        try{
            productModel.deleteProduct(id-1);
        } catch (RuntimeException e) {
            if (e instanceof CustomException) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("확인되지 않은 에러입니다. 관리자에게 문의 주세요", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }


    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable  @Min(1) @NotNull Integer id, @RequestBody @Valid ProductDTO productDTO) {
        try {
            productModel.updateProduct(id - 1, productDTO);

        } catch (RuntimeException e) {
            if (e instanceof CustomException) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("확인되지 않은 에러입니다. 관리자에게 문의 주세요", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}
