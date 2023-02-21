package com.emirhanarici.rentACar01.controllers;

import com.emirhanarici.rentACar01.business.abstracts.BrandService;
import com.emirhanarici.rentACar01.requests.CreateBrandRequest;
import com.emirhanarici.rentACar01.requests.UpdateBrandRequest;
import com.emirhanarici.rentACar01.responses.GetAllBrandResponse;
import com.emirhanarici.rentACar01.responses.GetByIdBrandResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {


    private BrandService brandService;

    public BrandsController(BrandService brandService) {
        this.brandService = brandService;
    }


    @GetMapping()
    public List<GetAllBrandResponse> getAll() {
        return brandService.getAll();
    }

    @GetMapping("/{id}")
    public GetByIdBrandResponse getById(@PathVariable int id) {
        return brandService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody CreateBrandRequest createBrandRequest) {
        this.brandService.add(createBrandRequest);
    }

    @PutMapping
    public void update(@RequestBody UpdateBrandRequest updateBrandRequest) {

        this.brandService.update(updateBrandRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        this.brandService.delete(id);
    }

}
