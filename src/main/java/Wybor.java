/**
 * Enum odpowiedzialny za komendy, których użytkownik korzystający z aplikacji będzie używać
 */
public enum Wybor {
    CONTINUE(null, null),
    WYBIERZ_LEVEL_BOHATERA("/level", "Wybierz poziom bohatera!"),
    WYBOR_BOHATERA("/bohater", "Wybierz bohatera!"),
    WYBOR_PRZEDMIOTU("/przedmiot", "Wybierz przedmiot!"),
    NIEZNANA_KOMENDA(null, ERROR.UNKNOWN_COMMAND.getWiadomosc()),
    PRZEJDZ_DO_EKWIPUNKU("/ekwipunek", ""),
    POJEDYNEK("/pojedynek", ""),
    OTWORZ_EKWIPUNEK("/pokaz ekwipunek", "O to ekwipunek twojej postaci"),
    ZALOZ_PRZEDMIOT("/zaloz", ""),
    ZDEJMIJ_PRZEDMIOT("/zdejmij", ""),
    OPCJE_DLA_BOHATERA("/opcje bohatera", """
            Możesz wyświetlić:
            Wszystkie ogólne informacje o statystykach i umiejętnościach bohatera wpisując:
            ------------------------------------------------------------------------------------------------------------
            -imie
            -poziom
            -statystyki
            -hp
            -mana
            -Obrazenia od ataku
            -moc umiejetnosci
            -predkosc ataku
            -szansa na trafienie krytyczne
            -odpornosc na magie
            -pancerz
            -przyspieszenie umiejetnosci
            -predkosc ruchu
            -Umiejetnosci
            -Q
            -W
            -E
            -R
            -pasywka
            -skalowanie
            ------------------------------------------------------------------------------------------------------------
            Małe i duże litery nie mają znaczenia, NIE UŻYWAJ POLSKICH ZNAKÓW!
            Przyklad:\s
            hp
            pasywka
            Obrazenia od ataku
            statystyki"""),
    CHAMPIONS("/champions", "Powyżej znajdują się postacie z bazy danych, do których możesz uzyskać dostęp"),
    ITEMY("/itemy", "Powyżej znajdują się przedmoty z bazy danych, do których możesz uzyskać dostęp"),
    EXIT("/exit", "Żegnaj przywoływaczu!"),
    WYBIERZ_PRZECIWNIKA("/wybierz przeciwnika", "Wybierz swojego przeciwnika z postaci dostępnych w bazie danych"),
    LOSUJ_PRZECIWNIKA("/losuj przeciwnika", "O to wylosowany przeciwnik!"),
    STWORZ_KUKLE_TRENINGOWA("/trening", "Stwórz kukłe!"),

    PRZEDMIOTY_POSTACI("/aktualne przedmioty", "O to aktualne przedmioty:"),
    RETURN("/return", "");

    final String opcje;
    final String wiadomosc;

    /**
     * Konstruktor klasy
     */
    Wybor(String opcje, String wiadomosc) {
        this.opcje = opcje;
        this.wiadomosc = wiadomosc;
    }

    /**
     * Metoda sprawdzająca czy użytkownik użył dobrej komendy
     * @return
     */
    public static Wybor get(String input) {
        for (Wybor r : Wybor.values()) {
            if (input.equalsIgnoreCase(r.opcje)) {
                return r;
            }
        }
        return input.startsWith("/") ? NIEZNANA_KOMENDA : CONTINUE;
    }

    /**
     * Metoda zwracająca wiadomość w zależnosci od opcji którą użytkownik wybrał
     * @return
     */
    public String getWiadomosc() {
        return wiadomosc;
    }
}
