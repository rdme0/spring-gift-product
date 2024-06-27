package gift.dao;

import gift.customException.InvalidIdException;
import gift.customException.NoSuchProductIdException;
import gift.customException.NullContentException;
import gift.domain.ProductDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Connection getConnection() throws Exception {
        return DriverManager.getConnection("jdbc:h2:mem:gift", "sa", "");
    }

    public void createProductTable() {
        var sql = """
            CREATE TABLE product (
              id INT PRIMARY KEY AUTO_INCREMENT,
              name VARCHAR(255) NOT NULL,
              price INT NOT NULL,
              imageUrl VARCHAR(4096) NOT NULL,
            )
            """;
        jdbcTemplate.execute(sql);
    }

    public void insertProduct(ProductDTO productDTO) {
        var sql = "INSERT INTO product (id, price, name, imageUrl) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, productDTO.getId(), productDTO.getName(), productDTO.getPrice(), productDTO.getImageUrl());
    }

    public List<ProductDTO> selectProduct() {

        var sql = "SELECT * from product";
        List<ProductDTO> products = jdbcTemplate.query(sql, (rs, rowNum) -> new ProductDTO(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("imageUrl")
        ));
        return products;
    }

    public void updateProduct(ProductDTO productDTO) {
        var sql = "UPDATE product SET name = ?,price = ?, imageUrl = ? WHERE id = ?";
        jdbcTemplate.update(sql, productDTO.getName(), productDTO.getPrice(), productDTO.getImageUrl(), productDTO.getId());
    }

    public void deleteProduct(ProductDTO productDTO) {
        var sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql, productDTO.getId());
    }

    public Integer countProduct(){
        var sql = "SELECT COUNT(id) FROM product";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

}
