### **Kullanım Durumu:** Kayıt Olma
---
**Ana Aktör:** Kullanıcı

**Diğer Aktörler:** Veri Doğrulayıcı

**Ön Koşul:** Yok

**Başarı Kriteri:** Yeni kullanıcı sisteme eklenerek telefon doğrulama sayfafına yönlendirilmesi

---
### Ana Senaryo
1. Kullanıcı açılan ekrandan isim kısmına ismini girer.
2. Veri doğrulayıcı ismin 30 karakterden fazla girilmesini engeller.
3. Soy isim kısmına soy ismini girer.
4. Veri doğrulayıcı soy ismin 30 karakterden fazla girilmesini engeller.
5. Telefon numarası kısmına numarasını girer.
6. Veri doğrulayıcı girilen telefon numarasının doğru formatta girildiğini 
onaylar.
7. Kullanıcı email adresi kısmına email adresini girer.
8. Veri doğrulayıcı girilen email adresinin doğru formatta girildiğini 
onaylar.
9. Kullanıcı şifre kısmına sistemde kullanmak istediği şifreyi girer.
10. Veri doğrulayıcı girilen şifrenin istenilen kriterlerde olduğunu onaylar.
11. Kullanıcı şifre tekrarı kısmına sistemde kullanmak istediği şifreyi tekrar girer.
12. Veri doğrulayıcı girilen şifrelerin aynı olduğunu onaylar.
13. Kullanıcı kayıt ol butonuna tıklar.
14. Veri doğrulayıcı tüm bilgilerin girildiğini kontrol eder ve bilgileri sunucuya iletir. Sistem, sunucudan cevap gelinceye kadar kullanıcıya bekleme ekranı gösterir.
15. Sunucu tarafından bilgiler sisteme kaydedilir ve sisteme işlemin başarılı olduğu mesajı iletilerek kayıt tamamlanır.

---
### Alternatif Senaryolar
- 3-a: Girilen telefon numarası uygun formatta değildir ve veri doğrulayıcı durumu kullanıcıya bildirir.
- 5-b: Girilen mail adresi uygun formatta değildir ve veri doğrulayıcı durumu kullanıcıya bildirir.
- 13-a: Girilen email adresi daha önce sisteme kaydedilmiştir. Bu durum kullanıcıya bildirilir ve şifresini unuttuysa şifremi unuttum butonuya şifre sıfırlama talebinde bulunabileceği hatırlatılır.