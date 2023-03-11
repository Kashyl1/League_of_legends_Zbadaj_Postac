public enum Informacje {
    WPROWADZENIE("""
            Program aktulnie działa tylko dla postaci magicznych, PATCH: 13.5
            """),
    WYBOR_BOHATERA("""
            Komendy:
            "/champions" - Aby wyświetlić wszystkie postacie w bazie danych
            "/itemy" - Aby wyświetlic wszystkie przedmioty w bazie danych
            "/bohater" - Aby przejść do wyboru bohaterów
            "/przedmiot" - Aby przejść do wyboru przedmiotów
            "/exit" - Aby wyłączyć aplikacje"""),
    OPCJE_BOHATERA_DO_WYBORU("""
            Wybierz co chcesz zrobić, bądź wpisz:
            "/opcje bohatera", Aby zobaczyć opcje bohatera
            "/return", Aby cofnąć
            "/level", Aby zwiększyć poziom bohatera oraz jego bazowe statystyki
            "/ekwipunek", Aby móc zakładać przedmioty i zwiększać statystyki postaci
            "/pojedynek" - Aby przejść do pojedynku i sprawdzenia obrażeń
            "/exit" - Aby wyłączyć aplikacje"""),
    OPCJE_TESTU_OBRAZEN("""
            Komendy:
            "/wybierz przeciwnika" - Aby wybrać przeciwnika (POJEDYNEK NIE ZAIMPLEMENTOWANY)
            "/losuj przeciwnika" - Aby wylosować przeciwnika (POJEDYNEK NIE ZAIMPLEMENTOWANY)
            "/trening" - Aby stworzyć kukłe do treningu
            "/champions" - Aby wyświetlić wszystkie postacie
            "/return" - Aby cofnąć
            "/exit" - Aby wyłączyć aplikacje"""),
    WZOR_DO_UMIEJETNOSCI("""
                Wzór na wpisanie umiejętności które chcemy sprawdzić:
                (litera cyfra litera cyfra litera cyfra litera cyfra)
                Ciąg może mieć max 8 znaków i musi posiadać tyle samo liter co cyfr
                Poziom to cyfra która ma zostać wpisana po literze określającą daną umiejętność
                Litery mogą być: Q W E R, gdzie:
                Q - Pierwsza umiejętność (max poziom 5)
                W - Druga umiejętność (max poziom 5)
                E - Trzecia umiejętność (max poziom 5)
                R - Czwarta umiejętność (max poziom 3)
                Przykładowy wzór gdy chcemy sprawdzić damage 3 umiejętności:
                Q 4 R 2 W 3
                """),
    LEVEL_BOHATERA("Wpisz poziom bohatera który Cię interesuje, pamiętaj że poziom mieści się w przedziale domkniętym 1-18"),
    WYBRANY_BOHATER("Wybrano postać: "),
    WYBRANY_PRZEDMIOT("Wybrano Przedmiot: "),
    INFORMACJA_O_PRZEDMIOTACH("""
            Komendy:
            "/itemy" - Aby wyświetlić wszystkie dostępne przedmioty oraz ich statystyki
            "/zaloz" - Aby założyć przedmiot
            "/pokaz ekwipunek" - Aby zobaczyć ekwipunek
            "/zdejmij" - Aby zdjąc przedmiot (Rózróżniane są duże i małe litery!)
            "/return" - Aby cofnąć
            "/exit" - Aby wyłączyć aplikacje""");
    private final String wiadomosc;

    Informacje(String wiadomosc) {
        this.wiadomosc = wiadomosc;
    }

    public String getWiadomosc() {
        return wiadomosc;
    }
}
