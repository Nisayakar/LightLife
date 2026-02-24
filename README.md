🥗 LightLife – AI Destekli Diyet ve Sağlıklı Yaşam Yönetim Sistemi
📌 Proje Hakkında

LightLife, bireylerin sağlıklı yaşam süreçlerini dijital ortamda yönetmelerini sağlayan, diyetisyen ve danışan etkileşimini destekleyen Spring Boot tabanlı bir backend uygulamasıdır.

Sistem; kullanıcıların diyet planlarını takip etmesini, aktivitelerini yönetmesini, abonelik işlemlerini gerçekleştirmesini ve bildirim mekanizmaları aracılığıyla süreçlerini sürdürülebilir hale getirmesini amaçlar.

Proje içerisinde modern backend mimarisi ve yazılım tasarım prensipleri uygulanmıştır.

🚀 Kullanılan Teknolojiler

Java 17

Spring Boot

Spring Security

JWT Authentication

Spring Data JPA

PostgreSQL / SQL Database

Maven

RESTful API Architecture


Kullanılan Design Pattern’ler

Projede sürdürülebilir ve genişletilebilir mimari için çeşitli tasarım desenleri uygulanmıştır:

Pattern	Kullanım Amacı

Factory Pattern	Bildirim türlerinin dinamik oluşturulması

Observer Pattern	Bildirim ve olay yönetimi

Strategy Pattern	Kilo hedefi ve kampanya hesaplama

Facade Pattern	Kullanıcı panel işlemlerinin sadeleştirilmesi

Template Pattern	Günlük & haftalık rapor üretimi


👥 Sistem Rolleri

🧑 Kullanıcı (Danışan)

Diyet planlarını görüntüleme

Aktivite takibi

BMI ve hedef hesaplama

Bildirim alma

Destek talebi oluşturma


🧑‍⚕️ Diyetisyen

Danışan yönetimi

Diyet planı oluşturma

Rapor görüntüleme

Bildirim gönderme

🔐 Güvenlik

JWT tabanlı kimlik doğrulama

Spring Security entegrasyonu

Role-based authorization

Custom Authentication Filter


📂 Proje Mimarisi

lightlife
│
├── controller      → REST API endpointleri
├── service         → İş mantığı
├── repository      → Veri erişim katmanı
├── entity          → Veritabanı modelleri
├── dto             → Request / Response modelleri
├── security        → JWT & Authentication
├── pattern         → Design Pattern implementasyonları
└── util            → Yardımcı hesaplama sınıfları


⚙️ Kurulum
1️⃣ Repository Klonla

git clone https://github.com/kullaniciAdi/lightlife.git

2️⃣ Proje Dizini

cd lightlife

3️⃣ Veritabanı Ayarı

application.properties dosyasını düzenleyin:

spring.datasource.url=jdbc:postgresql://localhost:5432/lightlife

spring.datasource.username=postgres

spring.datasource.password=yourpassword

4️⃣ Projeyi Çalıştır

mvn spring-boot:run

Uygulama:

http://localhost:8080

📡 API Özellikleri

Kullanıcı Yönetimi

Diyet Yönetimi

Aktivite Takibi

Abonelik Sistemi

Bildirim Yönetimi

Destek Talebi Sistemi

Raporlama

📊 Öne Çıkan Özellikler

Katmanlı Mimari

RESTful API Tasarımı

JWT Authentication

Design Pattern Kullanımı

Ölçeklenebilir Backend Yapısı

Modüler Servis Yapısı

🧪 Test

mvn test


👩‍💻 Geliştirici

Nisa Yakar

Software Engineering Student

Mehmet Akif Güneş

Software Engineering Student
