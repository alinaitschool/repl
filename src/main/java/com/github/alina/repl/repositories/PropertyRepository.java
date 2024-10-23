package com.github.alina.repl.repositories;

import com.github.alina.repl.models.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long>, JpaSpecificationExecutor<Property> {
    // example using query annotations.
//    @Query("SELECT p FROM Property p WHERE " +
//    "(:city IS NULL OR LOWER(p.city)=LOWER(:city)) AND " +
//    "(:propertyType IS NULL OR LOWER(p.propertyType) LIKE LOWER(CONCAT('%', :propertyType, '%'))) AND " +
//    "(:price IS NULL OR p.price=:price) AND " +
//    "(:bedrooms IS NULL OR p.bedrooms=:bedrooms)")
//   List<Property> findPropertiesByFilter(@Param("city") String city, @Param("propertyType") String propertyType,
//                                         @Param("price") Long price, @Param("bedrooms") Long bedrooms);
}
