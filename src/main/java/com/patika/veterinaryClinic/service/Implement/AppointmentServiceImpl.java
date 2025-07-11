package com.patika.veterinaryClinic.service.Implement;

import com.patika.veterinaryClinic.dto.request.AppointmentRequestDto;
import com.patika.veterinaryClinic.dto.response.AppointmentResponseDto;
import com.patika.veterinaryClinic.entity.Animal;
import com.patika.veterinaryClinic.entity.Appointment;
import com.patika.veterinaryClinic.entity.Doctor;
import com.patika.veterinaryClinic.exception.AlreadyExistsException;
import com.patika.veterinaryClinic.exception.BadRequestException;
import com.patika.veterinaryClinic.exception.InvalidDateRangeException;
import com.patika.veterinaryClinic.exception.NotFoundException;
import com.patika.veterinaryClinic.exception.message.ErrorMessage;
import com.patika.veterinaryClinic.mapper.AppointmentMapper;
import com.patika.veterinaryClinic.repository.AppointmentRepo;
import com.patika.veterinaryClinic.repository.AvailableDateRepo;
import com.patika.veterinaryClinic.service.Interface.AnimalService;
import com.patika.veterinaryClinic.service.Interface.AppointmentService;
import com.patika.veterinaryClinic.service.Interface.DoctorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepo appointmentRepo;
    private final AvailableDateRepo availableDateRepo;
    private final DoctorService doctorService;
    private final AnimalService animalService;
    private final AppointmentMapper appointmentMapper;

    @Override
    public AppointmentResponseDto save(AppointmentRequestDto request) {
        Long doctorId = request.getDoctorId();
        Long animalId = request.getAnimalId();
        LocalDate appointmentDay = request.getAppointmentDate().toLocalDate();

        // Girilen ID'ler var mı yok mu kontrolü (getEntitiy içinde bir kontrol yapılır)
        Doctor doctor = doctorService.getEntityById(doctorId);
        Animal animal = animalService.getEntityById(animalId);

        //Doktorun o gün çalışıyor mu kontrolü
        boolean isAvailable = availableDateRepo.existsByDoctorIdAndAvailableDate(doctorId, appointmentDay);
        if (!isAvailable) {
            throw new NotFoundException(String.format(ErrorMessage.AVAILABLE_DATE_NOT_FOUND_EXCEPTION, String.valueOf(doctorId)));
        }

        // Aynı anda başka randevu var mı kontrolü
        boolean conflict = appointmentRepo.existsByDoctorIdAndAppointmentDate(doctorId, request.getAppointmentDate());
        if (conflict) {
            throw new AlreadyExistsException(ErrorMessage.APPOINTMENT_ALREADY_EXIST_EXCEPTION);
        }

        if (request.getAppointmentDate().getMinute() != 0 || request.getAppointmentDate().getSecond() != 0) {
            throw new BadRequestException(ErrorMessage.INVALID_TIME_RANGE_EXCEPTION);
        }
        Appointment appointment = appointmentMapper.toEntity(request);
        Appointment saved = appointmentRepo.save(appointment);
        return appointmentMapper.toDto(saved);

    }

    @Override
    public AppointmentResponseDto update(Long id, AppointmentRequestDto request) {

        Long doctorId = request.getDoctorId();
        Long animalId = request.getAnimalId();
        LocalDate appointmentDay = request.getAppointmentDate().toLocalDate();

        //Randevu kaydı var mı yok mu kontrol edilir
        Appointment appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorMessage.APPOINTMENT_NOT_FOUND_EXCEPTION, String.valueOf(id))));

        // Girilen ID'ler var mı yok mu kontrolü (getEntitiy içinde bir kontrol yapılır)
        Doctor doctor = doctorService.getEntityById(doctorId);
        Animal animal = animalService.getEntityById(animalId);

        //Doktorun o gün çalışıyor mu kontrolü
        boolean isAvailable = availableDateRepo.existsByDoctorIdAndAvailableDate(doctorId, appointmentDay);
        if (!isAvailable) {
            throw new NotFoundException(String.format(ErrorMessage.AVAILABLE_DATE_NOT_FOUND_EXCEPTION, String.valueOf(doctorId)));
        }
        //Saat doğru girildi mi
        if (request.getAppointmentDate().getMinute() != 0 || request.getAppointmentDate().getSecond() != 0) {
            throw new BadRequestException(ErrorMessage.INVALID_TIME_RANGE_EXCEPTION);
        }

        // Aynı anda başka randevu var mı kontrolü
        boolean conflict = appointmentRepo.existsByDoctorIdAndAppointmentDate(doctorId, request.getAppointmentDate())
                && (!appointment.getAppointmentDate().equals(request.getAppointmentDate()));

        //Başka randevu varsa hata fırlatılır
        if (conflict) {
            throw new AlreadyExistsException(ErrorMessage.APPOINTMENT_ALREADY_EXIST_EXCEPTION);
        }
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setDoctor(doctorService.getEntityById(request.getDoctorId()));
        appointment.setAnimal(animalService.getEntityById(request.getAnimalId()));

        Appointment updatedAppointment = appointmentRepo.save(appointment);
        return appointmentMapper.toDto(updatedAppointment);
    }

    @Override
    public void delete(Long id) {
        Appointment appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorMessage.APPOINTMENT_NOT_FOUND_EXCEPTION, String.valueOf(id))));
        appointmentRepo.delete(appointment);
    }

    @Override
    public List<AppointmentResponseDto> getAll() {
        return appointmentRepo.findAll()
                .stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponseDto> getByDoctorIdAndDateRange(Long doctorId, LocalDateTime start, LocalDateTime end) {
        //Start bilgisinin End bilgisinden önce olmasını kontrol edilir
        if (!start.isBefore(end)) {
            throw new InvalidDateRangeException(ErrorMessage.INVALID_START_END_DATE_RANGE_EXCEPTION);
        }
        //Girilen Doctor ID ve Tarih aralığına göre Randevu Listelenir
        return appointmentRepo
                .findByDoctorIdAndAppointmentDateBetween(doctorId, start, end)
                .stream()
                .map(appointmentMapper::toDto)
                .toList();
    }

    @Override
    public List<AppointmentResponseDto> getByAnimalIdAndDateRange(Long animalId, LocalDateTime start, LocalDateTime end) {
        //Start bilgisinin End bilgisinden önce olmasını kontrol edilir
        if (!start.isBefore(end)) {
            throw new InvalidDateRangeException(ErrorMessage.INVALID_START_END_DATE_RANGE_EXCEPTION);
        }
        //Girilen Animal ID ve Tarih aralığına göre Randevu Listelenir
        return appointmentRepo
                .findByAnimalIdAndAppointmentDateBetween(animalId, start, end)
                .stream()
                .map(appointmentMapper::toDto)
                .toList();
    }
}
