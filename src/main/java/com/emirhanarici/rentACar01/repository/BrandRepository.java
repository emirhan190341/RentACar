package com.emirhanarici.rentACar01.repository;

import com.emirhanarici.rentACar01.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {

    boolean existsBrandByName(String name);

    boolean existsBrandById(int id);

}

