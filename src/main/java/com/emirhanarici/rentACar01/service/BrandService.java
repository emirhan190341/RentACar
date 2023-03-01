package com.emirhanarici.rentACar01.service;

import com.emirhanarici.rentACar01.rules.BrandBusinessRules;
import com.emirhanarici.rentACar01.repository.BrandRepository;
import com.emirhanarici.rentACar01.entities.Brand;
import com.emirhanarici.rentACar01.dto.requests.CreateBrandRequest;
import com.emirhanarici.rentACar01.dto.requests.UpdateBrandRequest;
import com.emirhanarici.rentACar01.dto.responses.GetAllBrandResponse;
import com.emirhanarici.rentACar01.dto.responses.GetByIdBrandResponse;
import com.emirhanarici.rentACar01.utilities.mappers.ModelMapperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandService{

    private BrandRepository brandRepository;
    private ModelMapperService modelMapperService;
    private BrandBusinessRules brandBusinessRules;


    public List<GetAllBrandResponse> getAll() {

        List<Brand> brands = brandRepository.findAll();

        List<GetAllBrandResponse> brandsResponse = brands.stream()
                .map(brand -> this.modelMapperService.forResponse()
                        .map(brand, GetAllBrandResponse.class)).collect(Collectors.toList());

        return brandsResponse;
    }


    public GetByIdBrandResponse getById(int id) {
       Brand brand =  this.brandRepository.findById(id).orElse(null);

       GetByIdBrandResponse response = this.modelMapperService.forResponse().map(brand,GetByIdBrandResponse.class);

        return response;
    }


    public void add(CreateBrandRequest createBrandRequest) {

        this.brandBusinessRules.checkIfBrandNameExists(createBrandRequest.getName());

        Brand brand
                = this.modelMapperService.forRequest().map(createBrandRequest,Brand.class);

        this.brandRepository.save(brand);
    }


    public void update(UpdateBrandRequest updateBrandRequest) {

        Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest,Brand.class);

        this.brandRepository.save(brand);

    }

    public void delete(int id) {

        this.brandRepository.deleteById(id);
    }


}
