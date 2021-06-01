# apoteka-isa

Link ka aplikaciji na Heroku: https://apoteka-isa.herokuapp.com/

Pokretanje aplikacije:
  1. importovati aplikaciju u workspace
  2. instalirati sve dependency-je iz pom.xml
  2. pokrenuti aplikaciju

Korišćene tehnologije: 
  1. Spring Boot
  2. PostgreSQL
  
Napomene:
  1. Password za sve korisnike u bazi je adminadmin
  2. Pre pokretanja potrebno je kreirati bazu pod nazivom apoteka
  3. Projekat je rađen u Java 11
  4. Email adresa je: apotekaa.isa2021@gmail.com
     Šifra za email: Apoteka.2021
  5. Greška: savetovanje i pregled su pomešani - te klasa Counseling se odnosi na dermatologa, a klasa Examination na farmaceuta
  6. sonar.png je screenshot sonara i quality gate-a  
 
  Testovi:
  
  Student 2 - MedicineServiceTest, MedicinePriceServiceTest i drugih pet testova u IntegrationTest
  
  Student 3 - ExamintaionServiceTest, CounselingServiceTest i prvih pet testova u IntegrationTest
  
  Student 4 - UnitTests i poslednjih pet testova u IntegrationTest
