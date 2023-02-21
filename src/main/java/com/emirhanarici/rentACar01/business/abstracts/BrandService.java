package com.emirhanarici.rentACar01.business.abstracts;

import com.emirhanarici.rentACar01.entities.concretes.Brand;
import com.emirhanarici.rentACar01.requests.CreateBrandRequest;
import com.emirhanarici.rentACar01.requests.UpdateBrandRequest;
import com.emirhanarici.rentACar01.responses.GetAllBrandResponse;
import com.emirhanarici.rentACar01.responses.GetByIdBrandResponse;

import java.util.List;


public interface BrandService {

    List<GetAllBrandResponse> getAll();
    GetByIdBrandResponse getById(int id);
    void add(CreateBrandRequest createBrandRequest);
    void update(UpdateBrandRequest updateBrandRequest);
    void delete(int id);



}
