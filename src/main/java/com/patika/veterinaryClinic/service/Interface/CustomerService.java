package com.patika.veterinaryClinic.service.Interface;

import com.patika.veterinaryClinic.dto.request.CustomerRequestDto;
import com.patika.veterinaryClinic.dto.response.CustomerListResponseDto;
import com.patika.veterinaryClinic.dto.response.CustomerResponseDto;


import java.util.List;

public interface CustomerService {
    CustomerResponseDto save(CustomerRequestDto request);

    CustomerResponseDto update(Long id, CustomerRequestDto request);

    CustomerResponseDto getById(Long id);

    void delete(Long id);

    List<CustomerListResponseDto> getAll();

    List<CustomerResponseDto> searchByName(String name);
}
