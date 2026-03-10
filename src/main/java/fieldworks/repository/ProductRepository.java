package fieldworks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fieldworks.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
