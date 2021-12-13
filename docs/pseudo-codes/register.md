## Elemanlar
- **İsim alanı**: input type = text, placeholder: "John"
- **Soyisim alanı**: input type = text, placeholder: "Doe"
- **Telefon Numarası alanı**: input type = tel, placeholder: "+90 555 444 3322"
- **Email alanı**: input type = email, placeholder: "your@email.com"
- **Şifre alanı**: input type = password, placeholder: "Şifre"
- **Şifre Tekrar alanı**: input type = password, placeholder: "Şifre tekrarı"
- **Kayıt Ol Butonu**: değer: "Kayıt Ol", varsayılan durum, kapalı
- **Geri Butonu**: değer: "İkon(geri)"

## Kayıt İşlemi Mantıksal Akış

### **İsim alanı terkedildiğinde**

*Eğer isim alanı boş ise*

    Hata mesajı: "Lütfen isminizi yazın."

### **Soyisim alanı terkedildiğinde**

*Eğer soyisim alanı boş ise*

    Hata mesajı: "Lütfen soyisminizi yazın."

### **Telefon numarası alanı terkedildiğinde**

*Eğer telefon numarası alanı boş ise*

    Hata mesajı: "Lütfen telefon numaranızı yazın."

*Değilse ama telefon numarası formatı uygun değil ise*

    Hata mesajı: "Lütfen telefon numaranızı tekrar kontrol edin."

### **Email alanı terkedildiğinde**

*Eğer email alanı boş ise*

    Hata mesajı: "Lütfen email adresinizi yazın."

*Değilse ama email adresi formatı uygun değil ise*

    Hata mesajı: "Lütfen adresinizi tekrar kontrol edin."

### **Şifre alanı terkedildiğinde**

*Eğer şifre alanı boş ise*

    Hata mesajı: "Lütfen şifre belirleyin."

*Değilse ama girilen şifre kriterlere uygun değil ise*

    Hata mesajı: "Lütfen şifrenizi en az 6 karakter olarak belirleyin."

### **Şifre tekrar alanı terkedildiğinde**

*Eğer girilen şifreler aynı değil ise*

    Hata mesajı: "Girdiğiniz şifreler aynı değil, lütfen tekrar kontrol edin."

### Tüm alanlar doldurulduğunda

*Eğer girilen bilgiler ilk kontrol işleminden başarıyla geçtiyse*

    Kayıt ol butonunun durumunu açık hale getir.

### Kayıt ol butonuna tıklandığında

*Sunucuya gönderilen mail adresi sistemde zaten kayıtlıysa*

    Hata mesajı: "Girdiğiniz mail adresi ile daha önce kayıt gerçekleştirilmiştir, eğer size aitse giriş sayfasından giriş yapabilir veya şifrenizi unuttuysanız giriş sayfasından şifre sıfırlama talebinde bulunabilirsiniz."

*Sunucu tarafından kaydın gerçekleştiğine dair mesaj geldiyse*

    Bilgi mesajı: "Tebrikler, Kaydınız başarıyla gerçekleşmiştir." 2 saniye gösterilir ve kullanıcının sisteme girişi gerçekleştirilir.