package com.emirhanarici.rentACar01.business.concretes;

import com.emirhanarici.rentACar01.business.abstracts.BrandService;
import com.emirhanarici.rentACar01.core.utilities.mappers.ModelMapperService;
import com.emirhanarici.rentACar01.dataAccess.abstracts.BrandRepository;
import com.emirhanarici.rentACar01.entities.concretes.Brand;
import com.emirhanarici.rentACar01.requests.CreateBrandRequest;
import com.emirhanarici.rentACar01.requests.UpdateBrandRequest;
import com.emirhanarici.rentACar01.responses.GetAllBrandResponse;
import com.emirhanarici.rentACar01.responses.GetByIdBrandResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {

    private BrandRepository brandRepository;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllBrandResponse> getAll() {

        List<Brand> brands = brandRepository.findAll();

        List<GetAllBrandResponse> brandsResponse = brands.stream()
                .map(brand -> this.modelMapperService.forResponse()
                        .map(brand, GetAllBrandResponse.class)).collect(Collectors.toList());

        return brandsResponse;
    }

    @Override
    public GetByIdBrandResponse getById(int id) {
       Brand brand =  this.brandRepository.findById(id).orElse(null);

       GetByIdBrandResponse response = this.modelMapperService.forResponse().map(brand,GetByIdBrandResponse.class);

        return response;
    }

    @Override
    public void add(CreateBrandRequest createBrandRequest) {

//        Brand brand = new Brand();
//        brand.setName(createBrandRequest.getName());

        Brand brand
                = this.modelMapperService.forRequest().map(createBrandRequest,Brand.class);



        this.brandRepository.save(brand);
    }

    @Override
    public void update(UpdateBrandRequest updateBrandRequest) {

        Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest,Brand.class);

        this.brandRepository.save(brand);

    }

    @Override
    public void delete(int id) {

        this.brandRepository.deleteById(id);
    }


}
