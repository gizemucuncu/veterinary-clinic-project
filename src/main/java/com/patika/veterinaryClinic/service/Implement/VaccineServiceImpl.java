package com.patika.veterinaryClinic.service.Implement;

import com.patika.veterinaryClinic.dto.request.VaccineRequestDto;
import com.patika.veterinaryClinic.dto.response.VaccineResponseDto;
import com.patika.veterinaryClinic.entity.Animal;
import com.patika.veterinaryClinic.entity.Vaccine;
import com.patika.veterinaryClinic.exception.AlreadyExistsException;
import com.patika.veterinaryClinic.exception.message.ErrorMessage;
import com.patika.veterinaryClinic.mapper.VaccineMapper;
import com.patika.veterinaryClinic.repository.AnimalRepo;
import com.patika.veterinaryClinic.repository.VaccineRepo;
import com.patika.veterinaryClinic.service.VaccineService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class VaccineServiceImpl implements VaccineService {
    private final VaccineRepo vaccineRepo;
    private final AnimalRepo animalRepo;
    private final VaccineMapper vaccineMapper;

    @Override
    @Transactional
    public VaccineResponseDto save(VaccineRequestDto request) {
        Animal animal = animalRepo.findById(request.getAnimalId())
                .orElseThrow(() -> new AlreadyExistsException(ErrorMessage.NOT_FOUND_EXCEPTION));

        boolean alreadyExist = vaccineRepo.existsConflictingVaccine(
                request.getName(),
                request.getCode(),
                request.getAnimalId(),
                request.getProtectionStartDate(),
                request.getProtectionFinishDate()
        );
        if (alreadyExist) {
            throw new AlreadyExistsException(ErrorMessage.VACCINE_MADE_BEFORE_EXCEPTION);
        }

        Vaccine vaccine = vaccineMapper.toEntity(request, animal);
        Vaccine saved = vaccineRepo.save(vaccine);

        return vaccineMapper.toDto(saved);
    }

    @Override
    @Transactional
    public VaccineResponseDto update(Long id, VaccineRequestDto request) {
        Vaccine vaccine = vaccineRepo.findById(id)
                .orElseThrow(() -> new AlreadyExistsException(ErrorMessage.NOT_FOUND_EXCEPTION));

        Animal animal = animalRepo.findById(request.getAnimalId())
                .orElseThrow(() -> new AlreadyExistsException(ErrorMessage.NOT_FOUND_EXCEPTION));
        Vaccine update = vaccineMapper.toEntity(request, animal);
        update.setId(id);
        Vaccine save = vaccineRepo.save(update);
        return vaccineMapper.toDto(save);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!vaccineRepo.existsById(id)) {
            throw new AlreadyExistsException(String.format(ErrorMessage.VACCINE_NOT_FOUND_EXCEPTION, String.valueOf(id)));
        }
        vaccineRepo.deleteById(id);
    }

    @Override
    @Transactional
    public VaccineResponseDto getById(Long id) {
        return vaccineMapper.toDto(
                vaccineRepo.findById(id)
                        .orElseThrow(() ->
                                new AlreadyExistsException(String.format(ErrorMessage.VACCINE_NOT_FOUND_EXCEPTION, String.valueOf(id))
                                )));
    }

    @Override
    @Transactional
    public List<VaccineResponseDto> getAll() {
        return vaccineRepo.findAll()
                .stream()
                .map(vaccineMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<VaccineResponseDto> getByProtectionDateRange(LocalDate startDate, LocalDate endDate) {
        return vaccineRepo.findByProtectionFinishDateBetween(startDate, endDate)
                .stream()
                .map(vaccineMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<VaccineResponseDto> getAllByAnimalId(Long animalId) {
        List<VaccineResponseDto> animalVaccine = vaccineRepo.findAll()
                .stream()
                .filter(vaccine -> vaccine.getAnimal().getId().equals(animalId))
                .map(vaccineMapper::toDto)
                .collect(Collectors.toList());
        if (animalVaccine.isEmpty()) {
            throw new AlreadyExistsException(String.format(ErrorMessage.ANIMAL_VACCINE_NOT_FOUND_EXCEPTION, String.valueOf(animalId)));
        } else {
            return animalVaccine;
        }
    }
}
