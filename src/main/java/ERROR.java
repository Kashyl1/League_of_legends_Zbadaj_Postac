
/**
 * Enum odpowiedzialny za szukanie errorów
 */
public enum ERROR {
    INVALID_CHARACTER("Nie ma takiej postaci w bazie danych o nazwie: "),
    INVALID_ITEM("Nie ma takiego przedmiotu w bazie danych o nazwie: "),
    UNKNOWN_COMMAND("Nie ma takiej komendy");

    private final String wiadomosc;

    ERROR(String wiadomosc) {
        this.wiadomosc = wiadomosc;
    }

    public String getWiadomosc() {
        return wiadomosc;
    }
}