package com.emirhanarici.rentACar01.controllers;


import com.emirhanarici.rentACar01.service.ModelService;
import com.emirhanarici.rentACar01.dto.requests.CreateModelRequest;
import com.emirhanarici.rentACar01.dto.responses.GetAllModelsResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
@AllArgsConstructor
public class ModelsController {

    private ModelService modelService;

    @GetMapping()
    public List<GetAllModelsResponse> getAll() {
        return modelService.getAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody @Valid CreateModelRequest createModelRequest) {
        this.modelService.add(createModelRequest);
    }

    @GetMapping("/{modelId}")
    public GetAllModelsResponse getOneModel(@PathVariable int modelId) {

        return this.modelService.getModel(modelId);

    }


}
