package com.patika.veterinaryClinic.controller;


import com.patika.veterinaryClinic.dto.request.CustomerRequestDto;
import com.patika.veterinaryClinic.dto.response.*;
import com.patika.veterinaryClinic.service.Interface.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDto> create(@Valid @RequestBody CustomerRequestDto request) {
        return new ResponseEntity<>(customerService.save(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> update(@PathVariable Long id,
                                                      @Valid @RequestBody CustomerRequestDto request) {
        CustomerResponseDto updated = customerService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomer(@PathVariable Long id) {
        CustomerResponseDto response = customerService.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CustomerListResponseDto>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<CustomerResponseDto>> filterByName(@Valid @RequestParam String name) {
        return ResponseEntity.ok(customerService.searchByName(name));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<VeterinaryResponse> delete(@PathVariable Long id) {
        customerService.delete(id);

        VeterinaryResponse response = new VeterinaryResponse();
        response.setMessage(id + " numaralı id'ye sahip müşteri kaydı başarıyla silindi.");
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }
}
