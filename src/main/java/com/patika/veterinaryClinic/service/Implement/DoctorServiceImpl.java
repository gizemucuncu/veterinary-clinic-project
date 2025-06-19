package com.patika.veterinaryClinic.service.Implement;

import com.patika.veterinaryClinic.dto.request.DoctorRequestDto;
import com.patika.veterinaryClinic.dto.response.DoctorListResponseDto;
import com.patika.veterinaryClinic.dto.response.DoctorResponseDto;
import com.patika.veterinaryClinic.entity.Doctor;
import com.patika.veterinaryClinic.exception.AlreadyExistsException;
import com.patika.veterinaryClinic.exception.NotFoundException;
import com.patika.veterinaryClinic.exception.message.ErrorMessage;
import com.patika.veterinaryClinic.repository.DoctorRepo;
import com.patika.veterinaryClinic.service.Interface.DoctorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepo doctorRepo;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public DoctorResponseDto save(DoctorRequestDto request) {
        //Kaydedilmek istenen doktorun mail ve phone bilgisi sitemde mevcut mu ?
        if (doctorRepo.existsByMail(request.getMail())) {
            throw new AlreadyExistsException(String.format(ErrorMessage.MAIL_ALREADY_EXISTS_EXCEPTION, request.getMail()));
        }

        if (doctorRepo.existsByPhone(request.getPhone())) {
            throw new AlreadyExistsException(String.format(ErrorMessage.PHONE_ALREADY_EXISTS_EXCEPTION, request.getPhone()));
        }

        Doctor doctor = modelMapper.map(request, Doctor.class);
        Doctor savedDoctor = doctorRepo.save(doctor);

        return modelMapper.map(savedDoctor, DoctorResponseDto.class);
    }

    @Override
    @Transactional
    public DoctorResponseDto update(Long id, DoctorRequestDto request) {
        Doctor doctor = doctorRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorMessage.DOCTOR_NOT_FOUND_EXCEPTION, String.valueOf(id))));

        if (doctorRepo.existsByMail(request.getMail()) && !doctor.getMail().equals(request.getMail())) {
            throw new AlreadyExistsException(String.format(ErrorMessage.MAIL_ALREADY_EXISTS_EXCEPTION, request.getMail()));
        }

        if (doctorRepo.existsByPhone(request.getPhone()) && !doctor.getPhone().equals(request.getPhone())) {
            throw new AlreadyExistsException(String.format(ErrorMessage.PHONE_ALREADY_EXISTS_EXCEPTION, request.getPhone()));
        }

        modelMapper.map(request, doctor); // DTO’dan entity’ye field'ları güncelle
        Doctor updated = doctorRepo.save(doctor);
        return modelMapper.map(updated, DoctorResponseDto.class);
    }

    @Override
    @Transactional
    public DoctorResponseDto getById(Long id) {
        Doctor doctor = doctorRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorMessage.DOCTOR_NOT_FOUND_EXCEPTION, String.valueOf(id))));
        return modelMapper.map(doctor, DoctorResponseDto.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Doctor doctor = doctorRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorMessage.DOCTOR_NOT_FOUND_EXCEPTION, String.valueOf(id))));
        doctorRepo.delete(doctor);
    }

    @Override
    @Transactional
    public List<DoctorListResponseDto> getAll() {
        return doctorRepo.findAll().stream().map(doctor -> new DoctorListResponseDto(
                doctor.getId(),
                doctor.getName(),
                doctor.getCity()
        )).collect(Collectors.toList());
    }

    @Override
    public Doctor getEntityById(Long id) {
        return doctorRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorMessage.DOCTOR_NOT_FOUND_EXCEPTION, String.valueOf(id))));
    }
}
