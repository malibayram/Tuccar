### **Kullanım Durumu:** Giriş Yapma
---
**Ana Aktör:** Kullanıcı

**Diğer Aktörler:** Veri Doğrulayıcı

**Ön Koşul:** Kullanıcı kayıt olmuş olmalı

**Başarı Kriteri:** Kayıtlı kullanıcının sisteme giriş yaparak uygulamayı kullanmaya başlaması

---
### Ana Senaryo
1. Kullanıcı email adresi kısmına email adresini girer.
2.  Veri doğrulayıcı girilen email adresinin doğru formatta girildiğini 
onaylar.
3. Kullanıcı şifre kısmına sisteme kayıt olurken belirlediği şifreyi girer.
4. Veri doğrulayıcı girilen şifrenin istenilen kriterlerde olduğunu onaylar.
5. Kullanıcı giriş yap butonuna tıklar.
6. Veri doğrulayıcı bilgileri sunucuya iletir. Sistem, sunucudan cevap gelinceye kadar kullanıcıya bekleme ekranı gösterir.
7. Sunucu tarafından bilgiler sistemde kontrol edilir ve sisteme işlemin başarılı olduğu mesajı iletilerek giriş işlemi tamamlanır.

---
### Alternatif Senaryolar
- 7-a: Girilen email adresi daha önce sisteme kaydedilnemiştir. Bu durum kullanıcıya bildirilir kayıt ol butonuna tıklayarak kayıt sayfasından kayıt olabileceği kullanıcıya bildirilir.
- 7-b: Girilen email adresi ve şifre uyuşmamaktadır. Bu durum kullanıcıya bildirilir ve şifresini unuttuysa şifremi unuttum butonuya şifre sıfırlama talebinde bulunabileceği hatırlatılır.