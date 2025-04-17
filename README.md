# Kotlin-Calismalari
# Asal mı Değil mi? – Kotlin Terminal Oyunu 🎮

Bu oyun, Kotlin dili kullanılarak terminal üzerinde geliştirilmiştir. Amaç, verilen sayının **asal olup olmadığını doğru tahmin etmek**. 3 seviyeden oluşur: **Kolay, Orta, Zor**.

## 🧠 Oyun Kuralları

- Her seviye için belli sayıda soru ve süre sınırı vardır.
- Doğru cevaplar puan kazandırır, yanlış cevaplar can kaybettirir.
- 3 can hakkınız vardır.
- Oyun sonunda **yüksek skor (high score)** kaydedilir.

## ⏱️ Seviye Bilgileri

| Seviye | Sayı Aralığı | Soru Sayısı | Süre (sn) | Puan Katsayısı |
|--------|--------------|-------------|-----------|----------------|
| Kolay  | 1 – 50       | 10          | 20        | 1              |
| Orta   | 51 – 100     | 7           | 30        | 2              |
| Zor    | 101 – 200    | 5           | 40        | 3              |

## 🚀 Nasıl Çalıştırılır?

1. Kotlin yüklü bir ortamda terminal açın.
2. `AsalOyun.kt` dosyasını çalıştırın:
```bash
kotlinc AsalOyun.kt -include-runtime -d AsalOyun.jar
java -jar AsalOyun.jar
