package com.emirhanarici.rentACar01.dataAccess.abstracts;

import com.emirhanarici.rentACar01.entities.concretes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {


}

