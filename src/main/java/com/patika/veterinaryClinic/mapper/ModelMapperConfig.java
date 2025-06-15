package com.patika.veterinaryClinic.mapper;

import com.patika.veterinaryClinic.dto.request.AnimalRequestDto;
import com.patika.veterinaryClinic.dto.request.CustomerRequestDto;
import com.patika.veterinaryClinic.entity.Animal;
import com.patika.veterinaryClinic.entity.Customer;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.typeMap(AnimalRequestDto.class, Animal.class)
                .addMappings(mapper -> mapper.skip(Animal::setId));

        modelMapper.typeMap(CustomerRequestDto.class, Customer.class)
                .addMappings(mapper -> mapper.skip(Customer::setId));

        return modelMapper;
    }


}
