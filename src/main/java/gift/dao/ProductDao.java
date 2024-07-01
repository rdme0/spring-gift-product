package gift.dao;


import gift.config.DatabaseProperties;
import gift.dto.ProductDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    private final DatabaseProperties databaseProperties;

    @Autowired
    public ProductDao(JdbcTemplate jdbcTemplate, DatabaseProperties databaseProperties) {
        this.jdbcTemplate = jdbcTemplate;
        this.databaseProperties = databaseProperties;
    }

    @Override
    public void run(String... args) throws Exception {
        var sqlDropTable = "DROP TABLE IF EXISTS product";

        var sqlCreateTable = """
                CREATE TABLE product (
                  id INT PRIMARY KEY AUTO_INCREMENT,
                  name VARCHAR(255) NOT NULL,
                  price INT NOT NULL,
                  imageUrl VARCHAR(4096) NOT NULL
                );
                """;

        jdbcTemplate.execute(sqlDropTable);
        jdbcTemplate.execute(sqlCreateTable);
    }

    @ConfigurationProperties(prefix = "spring.datasource")
    public Connection getConnection() throws Exception {
        return DriverManager.getConnection(databaseProperties.getUrl(),
                databaseProperties.getUsername(), databaseProperties.getPassword());
    }

    public void insertProduct(ProductDTO productDTO) {
        var sql = "INSERT INTO product (name, price, imageUrl) values (?, ?, ?)";
        jdbcTemplate.update(sql, productDTO.name(), productDTO.price(), productDTO.imageUrl());
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

    public Integer updateProduct(ProductDTO productDTO) {
        var sql = "UPDATE product SET name = ?,price = ?, imageUrl = ? WHERE id = ?";
        return jdbcTemplate.update(sql, productDTO.name(), productDTO.price(),
                productDTO.imageUrl(), productDTO.id());
    }

    public Integer deleteProduct(Integer id) {
        var sql = "DELETE FROM product WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public Integer countProduct() {
        var sql = "SELECT COUNT(id) FROM product";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }


}
