package com.patika.veterinaryClinic.service.Implement;

import com.patika.veterinaryClinic.dto.request.AvailableDateRequestDto;
import com.patika.veterinaryClinic.dto.response.AvailableDateResponseDto;
import com.patika.veterinaryClinic.entity.AvailableDate;
import com.patika.veterinaryClinic.exception.AlreadyExistsException;
import com.patika.veterinaryClinic.exception.NotFoundException;
import com.patika.veterinaryClinic.exception.message.ErrorMessage;
import com.patika.veterinaryClinic.mapper.AvailableDateMapper;
import com.patika.veterinaryClinic.repository.AvailableDateRepo;
import com.patika.veterinaryClinic.repository.DoctorRepo;
import com.patika.veterinaryClinic.service.AvailableDateService;
import com.patika.veterinaryClinic.service.DoctorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AvailableDateImpl implements AvailableDateService {

    private final AvailableDateRepo availableDateRepo;
    private final DoctorRepo doctorRepo;
    private final DoctorService doctorService;
    private final AvailableDateMapper availableDateMapper;

    @Override
    public AvailableDateResponseDto save(AvailableDateRequestDto request) {
        if (availableDateRepo.existsByAvailableDateAndDoctorId(request.getAvailableDate(), request.getDoctorId())) {
            throw new AlreadyExistsException(String.format(ErrorMessage.AVAILABLE_DATE_ALREADY_EXISTS_EXCEPTION, request.getDoctorId()));
        }
        AvailableDate availableDate = availableDateMapper.toEntity(request);
        return availableDateMapper.toDto(availableDateRepo.save(availableDate));
    }

    @Override
    public AvailableDateResponseDto update(Long id, AvailableDateRequestDto request) {
        AvailableDate updateAvailable = availableDateRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorMessage.AVAILABLE_DATE_NOT_FOUND_EXCEPTION, String.valueOf(id))));
        boolean conflict = availableDateRepo.existsByAvailableDateAndDoctorIdAndId(request.getAvailableDate(), request.getDoctorId(), id);
        if (conflict) {
            throw new AlreadyExistsException(ErrorMessage.DOCTOR_AVAILABLE_DATE_ALREADY_EXISTS_EXCEPTION);
        }
        updateAvailable.setAvailableDate(request.getAvailableDate());
        updateAvailable.setDoctor(doctorService.getEntityById(request.getDoctorId()));

        return availableDateMapper.toDto(availableDateRepo.save(updateAvailable));
    }

    @Override
    public List<AvailableDateResponseDto> getAllByDoctorId(Long doctorId) {
        if (!doctorRepo.existsById(doctorId)) {
            throw new NotFoundException(String.format(String.format(ErrorMessage.DOCTOR_NOT_FOUND_EXCEPTION, String.valueOf(doctorId))));
        }
        return availableDateRepo.findByDoctorId(doctorId)
                .stream()
                .map(availableDateMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AvailableDateResponseDto getById(Long id) {
        AvailableDate availableDate = availableDateRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorMessage.AVAILABLE_DATE_NOT_FOUND_EXCEPTION, String.valueOf(id))));
        return availableDateMapper.toDto(availableDate);
    }

    @Override
    public void delete(Long id) {
        if (!availableDateRepo.existsById(id)) {
            throw new NotFoundException(String.format(ErrorMessage.AVAILABLE_DATE_NOT_FOUND_EXCEPTION, String.valueOf(id)));
        }
        availableDateRepo.deleteById(id);
    }

}
