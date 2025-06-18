package com.patika.veterinaryClinic.service.Implement;

import com.patika.veterinaryClinic.dto.request.CustomerRequestDto;
import com.patika.veterinaryClinic.dto.response.CustomerListResponseDto;
import com.patika.veterinaryClinic.dto.response.CustomerResponseDto;
import com.patika.veterinaryClinic.entity.Customer;
import com.patika.veterinaryClinic.exception.AlreadyExistsException;
import com.patika.veterinaryClinic.exception.NotFoundException;
import com.patika.veterinaryClinic.exception.message.ErrorMessage;
import com.patika.veterinaryClinic.repository.CustomerRepo;
import com.patika.veterinaryClinic.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo customerRepo;
    private final ModelMapper modelMapper;

    @Override
    public CustomerResponseDto save(CustomerRequestDto request) {
        if (customerRepo.existsByMail(request.getMail())) {
            throw new AlreadyExistsException(String.format(ErrorMessage.MAIL_ALREADY_EXISTS_EXCEPTION, request.getMail()));
        }

        if (customerRepo.existsByPhone(request.getPhone())) {
            throw new AlreadyExistsException(String.format(ErrorMessage.PHONE_ALREADY_EXISTS_EXCEPTION, request.getPhone()));
        }

        Customer customer = modelMapper.map(request, Customer.class);
        Customer savedCustomer = customerRepo.save(customer);

        return modelMapper.map(savedCustomer, CustomerResponseDto.class);
    }

    @Override
    public CustomerResponseDto update(Long id, CustomerRequestDto request) {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND_EXCEPTION));
        if (customerRepo.existsByMail(request.getMail()) && !request.getMail().equals(customer.getMail())) {
            throw new AlreadyExistsException(String.format(ErrorMessage.MAIL_ALREADY_EXISTS_EXCEPTION, request.getMail()));
        }

        if (customerRepo.existsByPhone(request.getPhone()) && !request.getPhone().equals(customer.getPhone())) {
            throw new AlreadyExistsException(String.format(ErrorMessage.PHONE_ALREADY_EXISTS_EXCEPTION, request.getPhone()));
        }
        modelMapper.map(request, customer);
        return modelMapper.map(customerRepo.save(customer), CustomerResponseDto.class);
    }

    @Override
    public CustomerResponseDto getById(Long id) {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorMessage.CUSTOMER_NOT_FOUND_EXCEPTION, String.valueOf(id))));
        return modelMapper.map(customer, CustomerResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND_EXCEPTION));
        customerRepo.delete(customer);
    }

    @Override
    public List<CustomerListResponseDto> getAll() {
        return customerRepo.findAll().stream().map(customer -> new CustomerListResponseDto(
                customer.getId(),
                customer.getName(),
                customer.getPhone(),
                customer.getMail()
        )).collect(Collectors.toList());
    }

    @Override
    public List<CustomerResponseDto> searchByName(String name) {
        List<CustomerResponseDto> customerResponseDto = customerRepo.findByNameContainingIgnoreCase(name)
                .stream()
                .map(customer -> modelMapper.map(customer, CustomerResponseDto.class))
                .collect(Collectors.toList());
        if (customerResponseDto.isEmpty()) {
            throw new NotFoundException(ErrorMessage.CUSTOMER_NAME_NOT_FOUND_EXCEPTION);
        }
        return customerResponseDto;
    }

}
