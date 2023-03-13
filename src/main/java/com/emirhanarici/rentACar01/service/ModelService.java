package com.emirhanarici.rentACar01.service;

import com.emirhanarici.rentACar01.entities.Brand;
import com.emirhanarici.rentACar01.repository.ModelRepository;
import com.emirhanarici.rentACar01.entities.Model;
import com.emirhanarici.rentACar01.dto.requests.CreateModelRequest;
import com.emirhanarici.rentACar01.dto.responses.GetAllModelsResponse;
import com.emirhanarici.rentACar01.utilities.mappers.ModelMapperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModelService  {

    private ModelRepository modelRepository;

    private ModelMapperService modelMapperService;
    public List<GetAllModelsResponse> getAll() {

        List<Model> models = modelRepository.findAll();

        return models.stream()
                .map(model -> this.modelMapperService.forResponse()
                        .map(model, GetAllModelsResponse.class)).collect(Collectors.toList());
    }

    public void add(CreateModelRequest createModelRequest) {

        Model model = this.modelMapperService.forRequest().map(createModelRequest,Model.class);
        this.modelRepository.save(model);

    }


    public GetAllModelsResponse getModel(int  modelId) {


        Model model =  this.modelRepository.findById(modelId).orElse(null);

        return this.modelMapperService.forResponse().map(model,GetAllModelsResponse.class);
    }
}
