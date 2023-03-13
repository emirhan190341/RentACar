package com.emirhanarici.rentACar01.controllers;

import com.emirhanarici.rentACar01.service.BrandService;
import com.emirhanarici.rentACar01.dto.requests.CreateBrandRequest;
import com.emirhanarici.rentACar01.dto.requests.UpdateBrandRequest;
import com.emirhanarici.rentACar01.dto.responses.GetAllBrandResponse;
import com.emirhanarici.rentACar01.dto.responses.GetByIdBrandResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@AllArgsConstructor
public class BrandsController {


    private BrandService brandService;

    @GetMapping
    public ResponseEntity<List<GetAllBrandResponse>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(brandService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetByIdBrandResponse> getById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(brandService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GetAllBrandResponse> add(@RequestBody @Valid CreateBrandRequest createBrandRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(brandService.add(createBrandRequest));

    }

    @PutMapping
    public ResponseEntity<GetAllBrandResponse> update(@RequestBody UpdateBrandRequest updateBrandRequest) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(brandService.update(updateBrandRequest));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        this.brandService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
