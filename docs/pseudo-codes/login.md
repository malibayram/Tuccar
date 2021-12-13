## Elemanlar
- **Email alanı**: input type = email, placeholder: "your@email.com"
- **Şifre alanı**: input type = password, placeholder: "Şifre"
- **Giriş Yap Butonu**: değer: "Giriş Yap", varsayılan durum, kapalı
- **Şifremi Unuttum Butonu**: değer: "İkon(geri)", varsayılan durum, açık
- **Geri Butonu**: değer: "İkon(geri)", varsayılan durum, açık

## Giriş İşlemi Mantıksal Akış

### **Email alanı terkedildiğinde**

*Eğer email alanı boş ise*

    Hata mesajı: "Lütfen email adresinizi yazın."

*Değilse ama email adresi formatı uygun değil ise*

    Hata mesajı: "Lütfen adresinizi tekrar kontrol edin."

### **Şifre alanı terkedildiğinde**

*Eğer şifre alanı boş ise*

    Hata mesajı: "Lütfen şifrenizi girin."

*Değilse ama girilen şifre kriterlere uygun değil ise*

    Hata mesajı: "Lütfen şifrenizi en az 6 karakter olarak girin."

### Tüm alanlar doldurulduğunda

*Eğer girilen bilgiler ilk kontrol işleminden başarıyla geçtiyse*

    Giriş yap butonunun durumunu açık hale getir.

### Giriş yap butonuna tıklandığında

*Sunucuya gönderilen mail adresi sistemde kayıtlı değilse*

    Hata mesajı: "Girdiğiniz mail adresi sistemde kayıtlı değil. Kayıt ol butonuyla kayıt sayfasından kayıt olabilrsiniz."

*Sunucuya gönderilen mail adresi ve şifre eşleşmiyorsa*

    Hata mesajı: "Girdiğiniz şifre doğru değil. Lütfen kontrol edip tekrar deneyin."

*Sunucu tarafından kaydın gerçekleştiğine dair mesaj geldiyse*

    Kullanıcının sisteme girişi gerçekleştirilir.