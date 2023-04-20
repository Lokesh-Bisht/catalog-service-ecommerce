package dev.lokeshbisht.catalogservice.repository;

import dev.lokeshbisht.catalogservice.entity.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends MongoRepository<Brand, String> {

  Optional<Brand> findByBrandId(Integer brandId);
  void deleteByBrandId(Integer brandId);
}
