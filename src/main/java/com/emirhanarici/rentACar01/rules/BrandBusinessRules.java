package com.emirhanarici.rentACar01.rules;


import com.emirhanarici.rentACar01.utilities.exceptions.BusinessException;
import com.emirhanarici.rentACar01.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BrandBusinessRules {

    private BrandRepository brandRepository;

    public void checkIfBrandNameExists(String name){
        if(this.brandRepository.existsBrandByName(name)){
            throw new BusinessException("Brand name already exists");
        }
    }

}
