package com.patika.veterinaryClinic.exception.message;

public class ErrorMessage {
    public final static String MAIL_ALREADY_EXISTS_EXCEPTION = "Girilen mail adresi sistemde kayıtlı";
    public final static String PHONE_ALREADY_EXISTS_EXCEPTION = "Girilen telefon numarası siştemde kayıtlı";
    public final static String NOT_FOUND_EXCEPTION = "%s id'li kayıt bulunamadı";
    public final static String VACCINE_MADE_BEFORE_EXCEPTION = "Hayvana bu aşı daha önce yapılmış. Aşı koruyuculuğu devam ediyor...";
    public final static String VACCINE_NOT_FOUND_EXCEPTION = "Sistemde %s id'li aşı bulunamadı!";
    public final static String ANIMAL_VACCINE_NOT_FOUND_EXCEPTION = "%s id ye sahip hayvan için aşı kaydı bulunamadı!";
    public final static String AVAILABLE_DATE_ALREADY_EXISTS_EXCEPTION = "Müsaitlik tablosunda bu tarihte doktor için kayıt zaten mevcuttur.";
    public final static String DOCTOR_AVAILABLE_DATE_ALREADY_EXISTS_EXCEPTION = "Doktor bu tarihte zaten bir kayda sahip...";
    public final static String AVAILABLE_DATE_NOT_FOUND_EXCEPTION = "%s id'li müsait tarih kaydı bulunamadı...";

    // TODO: Buradaki mesajları özelleştir..
}
