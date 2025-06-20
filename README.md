# 🐾 Veteriner Yönetim Sistemi 🐾

Veteriner kliniği için geliştirilen bu RESTful API, doktor, müşteri, hayvan, aşı ve randevu yönetimini içeren, kapsamlı bir yönetim sistemidir.

---

## 📦 Proje Teknolojileri

| Teknoloji         | Açıklama                                      |
|-------------------|-----------------------------------------------|
| Java 21           | Ana programlama dili                          |
| Spring Boot       | Uygulama çatısı                               |
| Spring Data JPA   | ORM & Repository işlemleri                    |
| PostgreSQL        | Veritabanı yönetim sistemi                    |
| ModelMapper       | DTO - Entity dönüşümleri                      |
| Lombok            | Boilerplate kodların azaltılması              |
| Hibernate Validator | @Valid desteği ve validation mesajları     |
| Postman           | API test aracı                                |
| Graphviz          | UML diyagramı üretimi                         |

---

## 🧩 Proje Özellikleri

✅ Doktor kayıt/güncelle/sil  
✅ Hayvan ve sahiplerinin yönetimi  
✅ Hayvana ait aşıların takip edilmesi  
✅ Randevu sistemi (çakışma, müsaitlik kontrolü)  
✅ Aşı koruyuculuğu bitmeden tekrar aşı yapılmasının engellenmesi  
✅ Doktorun çalıştığı günlerin yönetimi  
✅ DTO yapısı, custom exception’lar, global error handler  
✅ Validasyon kuralları ve anlamlı hata mesajları

---

## 🗂️ Proje Katmanları

```
com.patika.veterinaryClinic
├── controller
├── dto
│   ├── request
│   └── response
├── entity
├── mapper
├── repository
├── service
│   ├── Implement
│   └── Interface
├── exception
└── config
```

---

## 🗃️ Veritabanı Şeması

![veterinary_clinic_uml](https://github.com/user-attachments/assets/15d615fc-30ac-47ad-81a0-47ca1be04b84)


---

## 🧪 Postman Testleri

Postman koleksiyonu proje dizinine `postman_collection.Gizem Ucuncu_Veterinary Clinic Managament System.postman_collection.json` olarak eklenmiştir.  
Her varlık için ayrı klasörler ve test senaryoları içerir ✅

---

## 🔎 API Dokümantasyonu

👉 Daha detaylı endpoint bilgisi için [API_DOCUMENTATION.md](./API_DOCUMENTATION.md) dosyasına göz atabilirsiniz.

---

## 🏁 Başlangıç

```bash
git clone https://github.com/kullaniciAdi/veterinary-clinic-project.git
cd veterinary-clinic-project
```

### Uygulama yapılandırması:
- `application.properties` veya `application.yml` içinde veritabanı ayarlarını yapın

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/veterinaryClinic
spring.datasource.username=postgres
spring.datasource.password=postgres
```

### Uygulamayı başlatmak için:

```bash
mvn clean install
mvn spring-boot:run
```

---

## 📤 Export Dosyaları

| Dosya | Açıklama |
|-------|----------|
| `Gizem Ucuncu_Veterinary Clinic Managament System.postman_collection.json` | Postman test koleksiyonu |
| `veterinaryClinicBackupFile` | Örnek verilerle dolu SQL dump dosyası |

---

## 👩‍💻 Geliştirici

> Geliştiren: **Gizem Üçüncü**  
> GitHub: [github.com/gizemucuncu](https://github.com/gizemucuncu)  

---

## 🐶 Not

Bu uygulamadaki verilerin gerçek kurum ve kişilerle ilgisi yoktur. Gerçek veriler üzerinde test edilmemiştir 😄
