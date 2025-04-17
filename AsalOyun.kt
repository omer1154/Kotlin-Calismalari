import java.util.Scanner
import kotlin.random.Random
import java.io.File
import kotlin.math.sqrt

fun asalOyun(n: Int): Boolean {
    if (n < 2) return false
    for (i in 2..sqrt(n.toDouble()).toInt())
        if (n % i == 0) return false
    return true
}
fun saveHighscore(score: Int) {
    val file = File("highscore.txt")
    file.writeText(score.toString())
}
fun getHighscore(): Int {
    val file = File("highscore.txt")
    return if (file.exists()) {
        file.readText().toInt()
    } else { 0
    }
}
data class Quadruple<A, B, C, D>(val first: A, val second: B, val third: C, val fourth: D)
operator fun <A, B, C, D> Quadruple<A, B, C, D>.component1() = first
operator fun <A, B, C, D> Quadruple<A, B, C, D>.component2() = second
operator fun <A, B, C, D> Quadruple<A, B, C, D>.component3() = third
operator fun <A, B, C, D> Quadruple<A, B, C, D>.component4() = fourth

fun main() {
    val scanner = Scanner(System.`in`)
    var puan = 0
    var can = 3

    val seviyeler = listOf("kolay", "orta", "zor")

    println("Asal mi degil mi? Oyununa hos geldin!")
    println("Her sayinin asal olup olmadigini tahmin et ve her dogrudan puan topla.\n")
    println("3 seviye var, 3 canin var. Hadi baslayalim!\n")

    val highScore = getHighscore()

    for (seviye in seviyeler) {
        val (minSayi, maxSayi, puanKatsayi, soruSayisi) = when (seviye) {
            "kolay" -> Quadruple(1, 50, 1, 10)
            "orta" -> Quadruple(51, 100, 2, 7)
            "zor" -> Quadruple(101, 200, 3, 5)
            else -> Quadruple(1, 50, 1, 10)
        }
        val seviyeSüreleri = mapOf(
            "kolay" to 20,
            "orta" to 30,
            "zor" to 40,
        )
        val süre = seviyeSüreleri[seviye] ?: 30
        val baslangıçZamanı = System.currentTimeMillis()
        val bitişZamanı = baslangıçZamanı + (süre * 1000)

        println("===== ${seviye.uppercase()} SEVIYE =====")
        println("Dikkat! Suren basladi: ${seviye.uppercase()} seviye - $süre saniye\n")
        println("Sayilar: $minSayi - $maxSayi | Her dogru cevap: $puanKatsayi puan")

        var sorulanSoru = 0

        while (can > 0 && sorulanSoru < soruSayisi) {

        val suankiZaman = System.currentTimeMillis()
        if (suankiZaman >= bitişZamanı) {
        println("Suren doldu! $seviye seviyesi sona erdi.")
            break
        }
        val kalanSaniye = ((bitişZamanı - suankiZaman)/1000).toInt()
        println("Kalan sure $kalanSaniye saniye")

        val number = Random.nextInt(minSayi, maxSayi + 1)
        println("Soru ${sorulanSoru + 1} /$soruSayisi")

        println("Sayi: $number")
        print("Asal mi? yoksa Asal Degil mi?(e/h): ")
        val input = scanner.next().lowercase()

        when (input) {
            "e", "evet" -> {
                if (asalOyun(number)) {
                    println("Dogru tahmin! Bu sayi asal.")
                    puan += puanKatsayi
                } else {
                    println("Yanlis tahmin! Bu sayi asal degil.")
                    can--
                }
            }
            "h", "hayır"-> {
                if (!asalOyun(number)) {
                    println("Dogru tahmin! Bu sayi asal degil.")
                    puan += puanKatsayi
                } else {
                    println("Yanlis tahmin! Bu sayi asaldi.")
                    can--
                }
            }
        }
        sorulanSoru++
        println("Skor: $puan | Kalan can: $can\n")
    }
    if (can <= 0) {
        println("Canin kalmadi. Oyun bitti!")
        println("Toplam puan: $puan")
        return
    } else {
        println("Tebrikler! ${seviye.uppercase()} seviyeyi tamamladin.\n")
        if (seviye != "zor") {
            println("Simdi siradaki seviye geliyor...\n")
            Thread.sleep(1000)
        }
    }
    if (can > 0) {
        println("Tebrikler bu seviyeyi basariyla tamamladin!")
        println("Final skorun: $puan")
        }
        if (puan > highScore) {
            println("Tebrikler! Yeni yuksek puan kazandiniz: $puan")
            saveHighscore(puan)
        } else {
            println("Bu seviyede yeni bir yuksek puan kazanamadiniz.")
            println("Mevcut En Yuksek Puanin: $highScore")
        }
    }
}