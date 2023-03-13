package com.emirhanarici.rentACar01.controllers;

import com.emirhanarici.rentACar01.dto.requests.CreateBrandRequest;
import com.emirhanarici.rentACar01.dto.requests.UpdateBrandRequest;
import com.emirhanarici.rentACar01.dto.responses.GetAllBrandResponse;
import com.emirhanarici.rentACar01.dto.responses.GetByIdBrandResponse;
import com.emirhanarici.rentACar01.repository.BrandRepository;
import com.emirhanarici.rentACar01.service.BrandService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class BrandsControllerTest {


    @MockBean
    private BrandService brandService;

    @MockBean
    private BrandRepository brandRepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void itShouldSaveBrand_WhenValidBrandRequestBody() throws Exception {

        //given
        CreateBrandRequest createBrandRequest = CreateBrandRequest.builder()
                .name("name")
                .build();

        GetAllBrandResponse getAllBrandResponse = GetAllBrandResponse.builder()
                .id(1)
                .name("name")
                .build();
        //when
        when(brandService.add(createBrandRequest)).thenReturn(getAllBrandResponse);

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializeJson(createBrandRequest)))
                .andDo(print())
                .andExpect(jsonPath("$.name").value(createBrandRequest.getName()))
                .andExpect(status().isCreated());


    }

    @Test
    void itShouldGetAllBrands() throws Exception {

        //given
        List<GetAllBrandResponse> getAllBrandResponse = List.of(new GetAllBrandResponse());

        //when
        when(brandService.getAll()).thenReturn((getAllBrandResponse));

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializeJson(getAllBrandResponse)))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void itShouldGetBrand_WhenGivenId() throws Exception {

        //given
        int id = 1;
        GetByIdBrandResponse getByIdBrandResponse = new GetByIdBrandResponse();

        //when
        when(brandService.getById(id)).thenReturn(getByIdBrandResponse);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get(String.format("/api/brands/%s", id))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializeJson(getByIdBrandResponse)))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void itShouldUpdateBrand_WhenBrandRequestBody() throws Exception {
        //given
        UpdateBrandRequest updateBrandRequest = UpdateBrandRequest.builder()
                .id(1)
                .name("name")
                .build();

        GetAllBrandResponse getAllBrandResponse = GetAllBrandResponse.builder()
                .id(1)
                .name("name")
                .build();

        //when
        when(brandService.update(updateBrandRequest)).thenReturn(getAllBrandResponse);

        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/api/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializeJson(getAllBrandResponse)))
                .andDo(print())
                .andExpect(jsonPath("$.name").value(updateBrandRequest.getName()))
                .andExpect(status().isOk());
    }


    @Test
    void itShouldDeleteBrand_WhenGivenId() throws Exception {

        //given
        int id = 1;

        //when
        brandService.delete(id);
        verify(brandService, times(1)).delete(1);

        //then
        mockMvc.perform(MockMvcRequestBuilders.delete(String.format("/api/brands/%s", id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());


    }


    private String serializeJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }


}