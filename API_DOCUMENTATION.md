
# 🧪 API Endpoint Dokümantasyonu - Veterinary Clinic System

Bu belge, Veteriner Yönetim Sistemi REST API'lerinin genel bir özetini ve kullanım örneklerini içerir. Tüm istekler varsayılan olarak `http://localhost:8080` adresine yapılır.

---

## 👨‍⚕️ Doctor Endpoints

| Method | Endpoint                 | Açıklama              |
|--------|--------------------------|------------------------|
| POST   | /api/doctor              | Yeni doktor oluşturur |
| PUT    | /api/doctor/{id}         | Doktor bilgisi günceller |
| DELETE | /api/doctor/{id}         | Doktor siler           |
| GET    | /api/doctor/{id}         | ID ile doktor getirir  |
| GET    | /api/doctor/list         | Tüm doktorları listeler |

---

## 👤 Customer Endpoints

| Method | Endpoint                      | Açıklama                   |
|--------|-------------------------------|----------------------------|
| POST   | /api/customer                 | Yeni müşteri oluşturur     |
| PUT    | /api/customer/{id}            | Müşteri günceller           |
| DELETE | /api/customer/{id}            | Müşteri siler              |
| GET    | /api/customer/{id}            | ID ile müşteri getirir     |
| GET    | /api/customer/search?name=xyz | İsme göre müşteri arar     |
| GET    | /api/customer/list            | Tüm müşterileri listeler   |

---

## 🐶 Animal Endpoints

| Method | Endpoint                             | Açıklama                        |
|--------|--------------------------------------|---------------------------------|
| POST   | /api/animal                          | Yeni hayvan kaydı oluşturur     |
| PUT    | /api/animal/{id}                     | Hayvan günceller                |
| DELETE | /api/animal/{id}                     | Hayvan siler                    |
| GET    | /api/animal/{id}                     | ID ile hayvan getirir           |
| GET    | /api/animal/search?name=xyz          | İsme göre hayvan arar           |
| GET    | /api/animal/customer/{customerId}    | Müşteriye ait hayvanları listeler |
| GET    | /api/animal/list                     | Tüm hayvanları listeler         |

---

## 💉 Vaccine Endpoints

| Method | Endpoint                                      | Açıklama                                |
|--------|-----------------------------------------------|-----------------------------------------|
| POST   | /api/vaccine                                  | Aşı oluşturur                           |
| PUT    | /api/vaccine/{id}                             | Aşı bilgisi günceller                    |
| DELETE | /api/vaccine/{id}                             | Aşı siler                               |
| GET    | /api/vaccine/{id}                             | Aşı getirir                             |
| GET    | /api/vaccine/list                             | Tüm aşıları listeler                     |
| GET    | /api/vaccine/animal/{animalId}                | Belirli hayvana ait aşıları listeler    |
| GET    | /api/vaccine/protection?startDate=&endDate=   | Belirli tarih aralığında bitecek aşılar |

---

## 📅 AvailableDate Endpoints

| Method | Endpoint                                  | Açıklama                              |
|--------|-------------------------------------------|----------------------------------------|
| POST   | /api/available-date                       | Doktora yeni müsait günü ekler         |
| PUT    | /api/available-date/{id}                  | Müsait günü günceller                  |
| DELETE | /api/available-date/{id}                  | Müsait günü siler                      |
| GET    | /api/available-date/{id}                  | ID ile müsait günü getirir            |
| GET    | /api/available-date/doctor/{doctorId}     | Doktora ait tüm müsait günleri getirir|

---

## 📆 Appointment Endpoints

| Method | Endpoint                                                                       | Açıklama                                |
|--------|--------------------------------------------------------------------------------|------------------------------------------|
| POST   | /api/appointment                                                               | Yeni randevu oluşturur                   |
| PUT    | /api/appointment/{id}                                                          | Randevu günceller                         |
| DELETE | /api/appointment/{id}                                                          | Randevu siler                            |
| GET    | /api/appointment/list                                                          | Tüm randevuları listeler                 |
| GET    | /api/appointment/animal?animalId=&start=&end=                                  | Hayvan ID ve tarih aralığına göre filtre |
| GET    | /api/appointment/doctor?doctorId=&start=&end=                                  | Doktor ID ve tarih aralığına göre filtre |

---

## 🔗 Notlar

- `{id}`: Güncellenmek/silinmek istenen nesnenin veritabanı ID’si
- JSON örnekleri ve detaylar için `Gizem Ucuncu_Veterinary Clinic Management System.postman_collection.json` dosyasını inceleyin.
- API'de DTO kullanımı, validasyon ve hata yönetimi mevcuttur.

---

Geliştirici: Gizem Üçüncü
