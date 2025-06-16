package com.patika.veterinaryClinic.exception.message;

public class ErrorMessage {
    public final static String MAIL_ALREADY_EXISTS_EXCEPTION = "Girilen mail adresi sistemde kayıtlı";
    public final static String PHONE_ALREADY_EXISTS_EXCEPTION = "Girilen telefon numarası siştemde kayıtlı";

    //NOT_FOUND
    public final static String NOT_FOUND_EXCEPTION = "%s id'li kayıt bulunamadı";
    public final static String ANIMAL_NOT_FOUND_EXCEPTION = "%s id'li hayvan kaydı bulunamadı";
    public final static String DOCTOR_NOT_FOUND_EXCEPTION = "%s id'li doktor kayıt bulunamadı";
    public final static String CUSTOMER_NOT_FOUND_EXCEPTION = "%s id'li müşteri kayıt bulunamadı";
    public final static String VACCINE_NOT_FOUND_EXCEPTION = "Sistemde %s id'li aşı kaydı bulunamadı!";
    public final static String ANIMAL_VACCINE_NOT_FOUND_EXCEPTION = "%s id ye sahip hayvan için aşı kaydı bulunamadı!";
    public final static String APPOINTMENT_NOT_FOUND_EXCEPTION = "%s id'li randevu bulunamadı...";

    public final static String VACCINE_MADE_BEFORE_EXCEPTION = "Hayvana bu aşı daha önce yapılmış. Aşı koruyuculuğu devam ediyor...";

    public final static String AVAILABLE_DATE_ALREADY_EXISTS_EXCEPTION = "Müsaitlik tablosunda bu tarihte doktor için kayıt zaten mevcuttur.";
    public final static String DOCTOR_AVAILABLE_DATE_ALREADY_EXISTS_EXCEPTION = "Doktor bu tarihte zaten bir kayda sahip...";

    public final static String AVAILABLE_DATE_NOT_FOUND_EXCEPTION = "Girilen tarihte %s id'li doktor çalışmamaktadır.";
    public final static String APPOINTMENT_ALREADY_EXIST_EXCEPTION = "Seçilen tarihte başka bir randevu kaydı bulunmaktadır, başka tarih seçiniz...";

    public final static String INVALID_TIME_RANGE_EXCEPTION = "Geçersiz saat seçimi. Lütfen sadece saat başı (örneğin 13:00, 14:00) bir saat seçin.";

    // TODO: Buradaki mesajları özelleştir..
}
