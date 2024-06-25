package gift.controller;

import gift.domain.ProductDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ProductUtil {
    public List<Map<Integer, ProductDTO>> listIndexToKey(List<ProductDTO> products) {
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
