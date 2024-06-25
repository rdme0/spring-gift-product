package gift.controller;

import gift.domain.ProductDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private final List<ProductDTO> products = new ArrayList<>();

    @GetMapping("/products")
    public List<Map<Integer, ProductDTO>> getProducts() {
        return listIndexToKey(products); //리스트의 인덱스를 map의 Key + 1로 변환해주는 메서드
    }

    @PostMapping("/products")
    public void addProduct(@RequestBody ProductDTO productDTO) {
        products.add(productDTO);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        System.out.println(products.get(id - 1));
        products.remove(id-1);
    }

    @PutMapping("products/{id}")
    public void updateProduct(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {
        products.set(id-1, productDTO);
    }

    private List<Map<Integer, ProductDTO>> listIndexToKey(List<ProductDTO> products) {
        List<Map<Integer, ProductDTO>> productMaps = new ArrayList<>();
        Iterator<ProductDTO> iterator = products.iterator();
        for (int i = 0; iterator.hasNext(); i++) {
            Map<Integer, ProductDTO> map = new HashMap<>();
            map.put((i + 1), iterator.next());
            productMaps.add(map);
        }
        return productMaps;
    }
}
