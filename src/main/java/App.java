

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class App {
    Scanner scanner = new Scanner(System.in);
    List<Postac> postacie;
    List<Przedmioty> przedmioty;
    HashMap<String, HashMap<String, String>> ekwipunekBohatera = new HashMap<>();

    Postac znalezionyBohater = null;
    Przedmioty znalezionyPrzedmiot = null;

    public App() {

        Gson gson = new Gson();
        Reader readerPostacie;
        Reader readerPrzedmioty;
        InputStream inputStreamPostacie = getClass().getResourceAsStream("/Postacie.json");
        InputStream inputStreamPrzedmioty = getClass().getResourceAsStream("/Postacie.json");
        assert inputStreamPostacie != null;
        readerPostacie = new InputStreamReader(inputStreamPostacie);
        assert inputStreamPrzedmioty != null;
        readerPrzedmioty = new InputStreamReader(inputStreamPrzedmioty);

        JsonObject json = gson.fromJson(readerPostacie, JsonObject.class);
        JsonArray postacieJson = json.getAsJsonArray("postacie");
        postacie = new ArrayList<>();
        for (JsonElement postacJson : postacieJson) {
            Postac postac = gson.fromJson(postacJson, Postac.class);
            postacie.add(postac);
        }

        JsonObject jsonPrzedmioty = gson.fromJson(readerPrzedmioty, JsonObject.class);
        JsonArray przedmiotyJson = jsonPrzedmioty.getAsJsonArray("itemy");
        przedmioty = new ArrayList<>();

        for (JsonElement przedmiotJson : przedmiotyJson) {
            Przedmioty przedmioty1 = gson.fromJson(przedmiotJson, Przedmioty.class);
            przedmioty.add(przedmioty1);
        }
        System.out.println(Informacje.WPROWADZENIE.getWiadomosc());
        System.out.println(Informacje.WYBOR_BOHATERA.getWiadomosc());
        String wybierzBohateraLubPrzedmiot = scanner.nextLine();
        Wybor wybor = Wybor.get(wybierzBohateraLubPrzedmiot);
        while (wybor != Wybor.EXIT) {
            boolean petlaNr2 = true;
            try {
                switch (wybor) {
                    case CHAMPIONS:
                        pokazBohaterow();
                    case NIEZNANA_KOMENDA:
                        System.out.println(wybor.getWiadomosc());
                        break;
                    case ITEMY:
                        pokazPrzedmioty();
                        break;
                    case WYBOR_BOHATERA:
                        System.out.println(wybor.getWiadomosc());
                        wybierzBohateraLubPrzedmiot = scanner.nextLine();
                        while (petlaNr2) {
                            znalezionyBohater = szukajBohatera(wybierzBohateraLubPrzedmiot);
                            if (znalezionyBohater != null) {
                                System.out.println(Informacje.OPCJE_BOHATERA_DO_WYBORU.getWiadomosc());
                                String wybierzAtrybut = scanner.nextLine();
                                wybor = Wybor.get(wybierzAtrybut);
                                switch (wybor) {
                                    case OPCJE_DLA_BOHATERA, NIEZNANA_KOMENDA:
                                        System.out.println(wybor.getWiadomosc());
                                        break;
                                    case CONTINUE:
                                        pokazInformacjeOBohaterze(wybierzAtrybut, znalezionyBohater);
                                        break;
                                    case WYBIERZ_LEVEL_BOHATERA:
                                        poziomBohatera(znalezionyBohater);
                                        break;
                                    case PRZEJDZ_DO_EKWIPUNKU:
                                        ekwipunekBohatera(znalezionyBohater);
                                        break;
                                    case POJEDYNEK:
                                        testUmiejetnosci(znalezionyBohater);
                                    case RETURN:
                                        petlaNr2 = false;
                                        break;
                                    case EXIT:
                                        System.out.println(wybor.getWiadomosc());
                                        System.exit(1);
                                }
                            } else {
                                petlaNr2 = false;
                            }
                        }
                        break;
                    case WYBOR_PRZEDMIOTU:
                        System.out.println(wybor.getWiadomosc());
                        wybierzBohateraLubPrzedmiot = scanner.nextLine();
                        znalezionyPrzedmiot = szukajPrzedmiotow(wybierzBohateraLubPrzedmiot);
                        if (znalezionyPrzedmiot != null) {
                            znalezionyPrzedmiot(znalezionyPrzedmiot);
                            System.out.println(znalezionyPrzedmiot);
                        }
                        break;
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Informacje.WYBOR_BOHATERA.getWiadomosc());
            wybierzBohateraLubPrzedmiot = scanner.nextLine();
            wybor = Wybor.get(wybierzBohateraLubPrzedmiot);

        }
        System.out.println(wybor.getWiadomosc());
    }

    public Przedmioty szukajPrzedmiotow(String wybierzPrzedmiot) {
        Przedmioty znalezionyPrzedmiot;
        for (Przedmioty przedmioty1 : przedmioty) {
            if (przedmioty1.nazwa().equalsIgnoreCase(wybierzPrzedmiot)) {
                znalezionyPrzedmiot = przedmioty1;
                return znalezionyPrzedmiot;
            }
        }
        System.out.println(ERROR.INVALID_ITEM.getWiadomosc() + wybierzPrzedmiot);
        return null;
    }

    public void znalezionyPrzedmiot(Przedmioty przedmiot) {
        System.out.println(Informacje.WYBRANY_PRZEDMIOT.getWiadomosc() + przedmiot.nazwa());
    }

    public void pokazBohaterow() {
        System.out.println();
        postacie.stream()
                .collect(Collectors.groupingBy(i -> postacie.indexOf(i) / 4))
                .forEach((key, value) -> System.out.println(value.stream()
                        .map(Postac::getImie)
                        .collect(Collectors.joining(", "))));
        System.out.println();
    }

    public Postac szukajBohatera(String bohater) {
        Postac znalezionyBohater;
        for (Postac postac : postacie) {
            if (postac.getImie().equalsIgnoreCase(bohater)) {
                znalezionyBohater = postac;
                System.out.println(Informacje.WYBRANY_BOHATER.getWiadomosc() + bohater);
                return znalezionyBohater;
            }
        }
        System.out.println(ERROR.INVALID_CHARACTER.getWiadomosc() + bohater);
        return null;
    }

    public void pokazPrzedmioty() {
        System.out.println();
        przedmioty.stream()
                .collect(Collectors.groupingBy(i -> przedmioty.indexOf(i) / 4))
                .forEach((key, value) -> System.out.println(value.stream()
                        .map(Przedmioty::nazwa)
                        .collect(Collectors.joining(", "))));
        System.out.println();
    }

    public void pokazInformacjeOBohaterze(String wybierzAtrybut, Postac znalezionyBohater) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Wybor wybor = Wybor.get(wybierzAtrybut);
        assert znalezionyBohater != null;
        switch (wybierzAtrybut.toLowerCase()) {
            case "/opcje" -> System.out.println(wybor.getWiadomosc());
            case "imie" -> System.out.println("Imię: " + znalezionyBohater.getImie());
            case "poziom" -> System.out.println("Poziom: " + znalezionyBohater.getPoziom());
            case "hp" -> System.out.println("HP: " + znalezionyBohater.getStatystyki().getHp());
            case "mana" -> System.out.println("Mana: " + znalezionyBohater.getStatystyki().getMana());
            case "obrazenia od ataku" -> System.out.println("Obrazenia od ataku: " + znalezionyBohater.getStatystyki().getObrazeniaOdAtaku());
            case "moc umiejetnosci" -> System.out.println("Moc umiejetnosci: " + znalezionyBohater.getStatystyki().getMocUmiejetnosci());
            case "predkosc ataku" -> System.out.println("Prędkość ataku: " + znalezionyBohater.getStatystyki().getPredkoscAtaku());
            case "szansa na trafienie krytyczne" -> System.out.println("Szansa na trafienie krytyczne: " + znalezionyBohater.getStatystyki().getSzansaNaTrafienieKrytyczne());
            case "odpornosc na magie" -> System.out.println("Odporność na magię: " + znalezionyBohater.getStatystyki().getOdpornoscNaMagie());
            case "pancerz" -> System.out.println("Pancerz: " + znalezionyBohater.getStatystyki().getPancerz());
            case "przyspieszenie umiejetnosci" -> System.out.println("Przyśpieszenie umiejętności: " + znalezionyBohater.getStatystyki().getPrzyspieszenieUmiejetnosci());
            case "predkosc ruchu" -> System.out.println("Prędkość ruchu: " + znalezionyBohater.getStatystyki().getPredkoscRuchu());
            case "q" -> umiejetnoscQ(znalezionyBohater);
            case "w" -> umiejetnoscW(znalezionyBohater);
            case "e" -> umiejetnoscE(znalezionyBohater);
            case "r" -> umiejetnoscR(znalezionyBohater);
            case "pasywka" -> umiejetnoscPasywna(znalezionyBohater);
            case "statystyki" -> System.out.println(znalezionyBohater.getStatystyki().toString());
            case "umiejetnosci" -> {
                umiejetnoscQ(znalezionyBohater);
                umiejetnoscW(znalezionyBohater);
                umiejetnoscE(znalezionyBohater);
                umiejetnoscR(znalezionyBohater);
                umiejetnoscPasywna(znalezionyBohater);
            }
            case "skalowanie" -> System.out.println(znalezionyBohater.getSkalowanie().toString());
            case "wszystko" -> {
                System.out.println("Imię: " + znalezionyBohater.getImie());
                System.out.println("Poziom: " + znalezionyBohater.getPoziom());
                System.out.println(znalezionyBohater.getStatystyki().toString());
                System.out.println(znalezionyBohater.getSkalowanie().toString());
                umiejetnosci(znalezionyBohater);
            }
            default -> {
                if (wybierzAtrybut.equalsIgnoreCase("/opcje bohatera")) {
                    System.out.println(wybor.getWiadomosc());
                } else {
                    System.out.println(ERROR.UNKNOWN_COMMAND.getWiadomosc());
                }
            }
        }
    }

    public void umiejetnosci(Postac znalezionyBohater) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        umiejetnoscQ(znalezionyBohater);
        umiejetnoscW(znalezionyBohater);
        umiejetnoscE(znalezionyBohater);
        umiejetnoscR(znalezionyBohater);
        umiejetnoscPasywna(znalezionyBohater);
    }

    public void pokazStatystykiOrazUmiejetnosciBohatera(Postac znalezionyBohater) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        umiejetnoscQ(znalezionyBohater);
        umiejetnoscW(znalezionyBohater);
        umiejetnoscE(znalezionyBohater);
        umiejetnoscR(znalezionyBohater);
        umiejetnoscPasywna(znalezionyBohater);
        System.out.println("Poziom: " + znalezionyBohater.getPoziom());
        System.out.println(znalezionyBohater.getStatystyki().toString());
    }

    public void poziomBohatera(Postac znalezionyBohater) {
        System.out.println(Informacje.LEVEL_BOHATERA.getWiadomosc());
        int level;
        while (true) {
            System.out.println("Wpisz \"/return\" aby wrócić lub" +
                    "\nWprowadź liczbę od 1 do 18:");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("/return")) {
                break;
            } else {
                try {
                    level = Integer.parseInt(input);
                    if (level < 1 || level > 18) {
                        throw new NumberFormatException();
                    } else {
                        resetujStatystyki(znalezionyBohater, znalezionyBohater.getPoziom());
                        System.out.println("\nPomyślnie zmieniłeś poziom postaci! Aktualny poziom to: " + level + "\n");
                        znalezionyBohater.setPoziom(level);
                        if (znalezionyBohater.getPoziom() == 1) {
                            level = znalezionyBohater.getPoziom();
                        } else {
                            level = znalezionyBohater.getPoziom() - 1;
                        }
                        znalezionyBohater.getStatystyki().setHp((int) (znalezionyBohater.getStatystyki().getHp() + (znalezionyBohater.getSkalowanie().getSkalowanieHP() * level)));
                        znalezionyBohater.getStatystyki().setMana((int) (znalezionyBohater.getStatystyki().getMana() + (znalezionyBohater.getSkalowanie().getSkalowanieMany() * level)));
                        znalezionyBohater.getStatystyki().setObrazeniaOdAtaku((int) (znalezionyBohater.getStatystyki().getObrazeniaOdAtaku() + (znalezionyBohater.getSkalowanie().getSkalowanieAD() * level)));
                        znalezionyBohater.getStatystyki().setPancerz((int) (znalezionyBohater.getStatystyki().getPancerz() + (znalezionyBohater.getSkalowanie().getSkalowaniePancerz() * level)));
                        znalezionyBohater.getStatystyki().setOdpornoscNaMagie((int) (znalezionyBohater.getStatystyki().getOdpornoscNaMagie() + (znalezionyBohater.getSkalowanie().getSkalowanieMR() * level)));
                        znalezionyBohater.getStatystyki().setPredkoscAtaku((znalezionyBohater.getStatystyki().getPredkoscAtaku() + (znalezionyBohater.getSkalowanie().getSkalowanieAS() * level)));
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Wprowadzono złą liczbę! Pamiętaj że poziom bohatera może być 1-18");
                }
            }
        }
    }

    public void resetujStatystyki(Postac znalezionyBohater, int poziom) {
        poziom -= 1;
        znalezionyBohater.getStatystyki().setHp((int) (znalezionyBohater.getStatystyki().getHp() - (znalezionyBohater.getSkalowanie().getSkalowanieHP() * poziom)));
        znalezionyBohater.getStatystyki().setMana((int) (znalezionyBohater.getStatystyki().getMana() - (znalezionyBohater.getSkalowanie().getSkalowanieMany() * poziom)));
        znalezionyBohater.getStatystyki().setObrazeniaOdAtaku((int) (znalezionyBohater.getStatystyki().getObrazeniaOdAtaku() - (znalezionyBohater.getSkalowanie().getSkalowanieAD() * poziom)));
        znalezionyBohater.getStatystyki().setPancerz((int) (znalezionyBohater.getStatystyki().getPancerz() - (znalezionyBohater.getSkalowanie().getSkalowaniePancerz() * poziom)));
        znalezionyBohater.getStatystyki().setOdpornoscNaMagie((int) (znalezionyBohater.getStatystyki().getOdpornoscNaMagie() - (znalezionyBohater.getSkalowanie().getSkalowanieMR() * poziom)));
        znalezionyBohater.getStatystyki().setPredkoscAtaku((znalezionyBohater.getStatystyki().getPredkoscAtaku() - (znalezionyBohater.getSkalowanie().getSkalowanieAS() * poziom)));
    }


    public void umiejetnoscQ(Postac znalezionyBohater) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("Umiejętność pierwsza: " + znalezionyBohater.getUmiejetnoscPierwsza().getNazwa());
        System.out.println("Opis: " + znalezionyBohater.getUmiejetnoscPierwsza().getOpis());
        BazoweObrazenia Q = znalezionyBohater.getUmiejetnoscPierwsza().getBazoweObrazenia();
        System.out.println("Obrazenia bazowe bez skalowania z aktualnym AP/AD postaci:");
        for (int i = 1; i <= 5; i++) {
            Integer dmg = (Integer) Q.getClass().getMethod("getLvl" + i).invoke(Q);
            System.out.println("Lvl " + i + " bez skalowania: " + dmg);
            System.out.println("Lvl " + i + " ze skalowaniem: " + (dmg + (znalezionyBohater.getUmiejetnoscPierwsza().getSkalowanieMocyUmiejetnosci() * znalezionyBohater.getStatystyki().getMocUmiejetnosci())));
        }
    }

    public void umiejetnoscW(Postac znalezionyBohater) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println();
        System.out.println("Umiejętność Druga: " + znalezionyBohater.getUmiejetnoscDruga().getNazwa());
        System.out.println("Opis: " + znalezionyBohater.getUmiejetnoscDruga().getOpis());
        BazoweObrazenia W = znalezionyBohater.getUmiejetnoscDruga().getBazoweObrazenia();
        System.out.println("Obrazenia bazowe bez skalowania z aktualnym AP/AD postaci:");
        for (int i = 1; i <= 5; i++) {
            Integer dmg = (Integer) W.getClass().getMethod("getLvl" + i).invoke(W);
            System.out.println("Lvl " + i + " bez skalowania: " + dmg);
            System.out.println("Lvl " + i + " ze skalowaniem: " + (dmg + (znalezionyBohater.getUmiejetnoscDruga().getSkalowanieMocyUmiejetnosci() * znalezionyBohater.getStatystyki().getMocUmiejetnosci())));
        }
    }

    public void umiejetnoscE(Postac znalezionyBohater) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println();
        System.out.println("Umiejętność Trzecia: " + znalezionyBohater.getUmiejetnoscTrzecia().getNazwa());
        System.out.println("Opis: " + znalezionyBohater.getUmiejetnoscTrzecia().getOpis());
        BazoweObrazenia E = znalezionyBohater.getUmiejetnoscTrzecia().getBazoweObrazenia();
        System.out.println("Obrazenia bazowe bez skalowania z aktualnym AP/AD postaci:");
        for (int i = 1; i <= 5; i++) {
            Integer dmg = (Integer) E.getClass().getMethod("getLvl" + i).invoke(E);
            System.out.println("Lvl " + i + " bez skalowania: " + dmg);
            if (znalezionyBohater.getUmiejetnoscTrzecia().getSkalowanieMocyUmiejetnosci() == 0) {
                System.out.println("Lvl " + i + " ze skalowaniem(bezOblodzenia): " + (dmg + (znalezionyBohater.getUmiejetnoscTrzecia().getSkalowanieMocyUmiejetnosciBezOblodzenia() * znalezionyBohater.getStatystyki().getMocUmiejetnosci())));
                System.out.println("Lvl " + i + " ze skalowaniem(oblodzenie): " + (dmg + (znalezionyBohater.getUmiejetnoscTrzecia().getSkalowanieMocyUmiejetnosciZOblodzeniem() * znalezionyBohater.getStatystyki().getMocUmiejetnosci())));

            } else {
                System.out.println("Lvl " + i + " ze skalowaniem: " + (dmg + (znalezionyBohater.getUmiejetnoscTrzecia().getSkalowanieMocyUmiejetnosci() * znalezionyBohater.getStatystyki().getMocUmiejetnosci())));
            }
        }
    }

    public void umiejetnoscR(Postac znalezionyBohater) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println();
        System.out.println("Umiejętność Czwarta: " + znalezionyBohater.getUmiejetnoscCzwarta().getNazwa());
        System.out.println("Opis: " + znalezionyBohater.getUmiejetnoscCzwarta().getOpis());
        BazoweObrazenia R = znalezionyBohater.getUmiejetnoscCzwarta().getBazoweObrazenia();
        System.out.println("Obrazenia bazowe bez skalowania z aktualnym AP/AD postaci:");
        for (int i = 1; i <= 3; i++) {
            Integer dmg = (Integer) R.getClass().getMethod("getLvl" + i).invoke(R);
            System.out.println("Lvl " + i + " bez skalowania: " + dmg);
            System.out.println("Lvl " + i + " ze skalowaniem: " + (dmg + (znalezionyBohater.getUmiejetnoscCzwarta().getSkalowanieMocyUmiejetnosci() * znalezionyBohater.getStatystyki().getMocUmiejetnosci())));
        }
        System.out.println();
    }

    public void umiejetnoscPasywna(Postac znalezionyBohater) {
        System.out.println("Umiejętność pasywna: " + znalezionyBohater.getUmiejetnoscPasywna().getNazwa());
        System.out.println("Bonus umiejętności pasywnej: " + znalezionyBohater.getUmiejetnoscPasywna().getOpis());
    }

    public void ekwipunekBohatera(Postac znalezionyBohater) {
        System.out.println(Informacje.INFORMACJA_O_PRZEDMIOTACH.getWiadomosc());
        String wybierzPrzedmiot = scanner.nextLine();
        Wybor wybor = Wybor.get(wybierzPrzedmiot);
        while (wybor != Wybor.RETURN) {
            switch (wybor) {
                case ITEMY:
                    pokazPrzedmioty();
                    System.out.println(wybor.getWiadomosc());
                    break;
                case OTWORZ_EKWIPUNEK:
                    pokazywanieEkwipunku();
                    break;
                case ZALOZ_PRZEDMIOT:
                    zakladanieEkwipunku(znalezionyBohater);
                    break;
                case ZDEJMIJ_PRZEDMIOT:
                    zdejmowanieEkwipunku(znalezionyBohater);
                case EXIT:
                    System.out.println(wybor.getWiadomosc());
                    System.exit(1);
            }
            System.out.println(Informacje.INFORMACJA_O_PRZEDMIOTACH.getWiadomosc());
            wybierzPrzedmiot = scanner.nextLine();
            wybor = Wybor.get(wybierzPrzedmiot);
        }
    }

    public void zakladanieEkwipunku(Postac znalezionyBohater) {
        System.out.println("Wpisz nazwę przedmiotu którą chcesz wybrać!");
        String wybierzItem = scanner.nextLine();
        Przedmioty znalezionyPrzedmiot = szukajPrzedmiotow(wybierzItem);
        if (znalezionyPrzedmiot != null) {
            znalezionyPrzedmiot(znalezionyPrzedmiot);
            System.out.println(znalezionyPrzedmiot);
            HashMap<String, String> ekwipunek = ekwipunekBohatera.getOrDefault(znalezionyBohater.getImie(), new HashMap<>());
            ekwipunek.put(znalezionyPrzedmiot.nazwa(), znalezionyPrzedmiot.typ());
            ekwipunekBohatera.put(znalezionyBohater.getImie(), ekwipunek);
            dodawanieStatystykDoBohatera(znalezionyBohater, znalezionyPrzedmiot);
        }
    }

    public void dodawanieStatystykDoBohatera(Postac znalezionyBohater, Przedmioty znalezionyPrzedmiot) {
        znalezionyBohater.getStatystyki().setHp((znalezionyBohater.getStatystyki().getHp() + znalezionyPrzedmiot.hp()));
        znalezionyBohater.getStatystyki().setPredkoscAtaku((znalezionyBohater.getStatystyki().getPredkoscAtaku() + znalezionyPrzedmiot.predkoscAtakuBonus()));
        znalezionyBohater.getStatystyki().setPredkoscRuchu((znalezionyBohater.getStatystyki().getPredkoscRuchu() + znalezionyPrzedmiot.predkoscRuchu()));
        znalezionyBohater.getStatystyki().setMocUmiejetnosci((znalezionyBohater.getStatystyki().getMocUmiejetnosci() + znalezionyPrzedmiot.mocUmiejetnosci()));
        znalezionyBohater.getStatystyki().setMana((znalezionyBohater.getStatystyki().getMana() + znalezionyPrzedmiot.mana()));
        znalezionyBohater.getStatystyki().setPrzyspieszenieUmiejetnosci(znalezionyBohater.getStatystyki().getPrzyspieszenieUmiejetnosci() + znalezionyPrzedmiot.przyspieszenieUmiejetnosci());
        znalezionyBohater.getStatystyki().setSzansaNaTrafienieKrytyczne(znalezionyBohater.getStatystyki().getSzansaNaTrafienieKrytyczne() + znalezionyPrzedmiot.szansaNaTrafienieKrytyczne());
        znalezionyBohater.getStatystyki().setObrazeniaOdAtaku((znalezionyBohater.getStatystyki().getObrazeniaOdAtaku() + znalezionyPrzedmiot.obrazeniaOdAtaku()));
        znalezionyBohater.getStatystyki().setOdpornoscNaMagie((znalezionyBohater.getStatystyki().getOdpornoscNaMagie() + znalezionyPrzedmiot.odpornoscNaMagie()));
    }

    public void pokazywanieEkwipunku() {
        System.out.println("Ekwipunek bohatera " + znalezionyBohater.getImie() + ":");
        // KOD DO WYŚWIETLENIA POSTACI
        HashMap<String, String> ekwipunek = ekwipunekBohatera.get(znalezionyBohater.getImie());
        if (ekwipunek == null || ekwipunek.isEmpty()) {
            System.out.println("Ekwipunek jest pusty.");
            return;
        }
        for (String nazwaPrzedmiotu : ekwipunek.keySet()) {
            String typPrzedmiotu = ekwipunek.get(nazwaPrzedmiotu);
            System.out.println("Nazwa: " + nazwaPrzedmiotu);
            System.out.println("Typ: " + typPrzedmiotu);
        }
    }

    public void zdejmowanieEkwipunku(Postac znalezionyBohater) {
        System.out.println("Który przedmiot chcesz zdjąć?");
        String nazwaPrzedmiotu = scanner.nextLine().toLowerCase();
        Przedmioty znalezionyPrzedmiot = szukajPrzedmiotow(nazwaPrzedmiotu);
        //Szukamy przedmiotu w ekwipunku bohatera
        HashMap<String, String> ekwipunek = ekwipunekBohatera.get(znalezionyBohater.getImie());
        if (ekwipunek == null || !ekwipunek.containsKey(nazwaPrzedmiotu)) {
            System.out.println("Nie masz takiego przedmiotu w ekwipunku.");
            return;
        }
        String typPrzedmiotu = ekwipunek.get(nazwaPrzedmiotu);
        ekwipunek.remove(nazwaPrzedmiotu);
        ekwipunekBohatera.put(znalezionyBohater.getImie(), ekwipunek);
        System.out.println("Zdjęto przedmiot:");
        System.out.println("Nazwa: " + nazwaPrzedmiotu);
        System.out.println("Typ: " + typPrzedmiotu);
        usuwanieStatystykZBohatera(znalezionyBohater, znalezionyPrzedmiot);
    }

    public void usuwanieStatystykZBohatera(Postac znalezionyBohater, Przedmioty znalezionyPrzedmiot) {
        znalezionyBohater.getStatystyki().setHp((znalezionyBohater.getStatystyki().getHp() - znalezionyPrzedmiot.hp()));
        znalezionyBohater.getStatystyki().setPredkoscAtaku((znalezionyBohater.getStatystyki().getPredkoscAtaku() - znalezionyPrzedmiot.predkoscAtakuBonus()));
        znalezionyBohater.getStatystyki().setPredkoscRuchu((znalezionyBohater.getStatystyki().getPredkoscRuchu() - znalezionyPrzedmiot.predkoscRuchu()));
        znalezionyBohater.getStatystyki().setMocUmiejetnosci((znalezionyBohater.getStatystyki().getMocUmiejetnosci() - znalezionyPrzedmiot.mocUmiejetnosci()));
        znalezionyBohater.getStatystyki().setMana((znalezionyBohater.getStatystyki().getMana() + znalezionyPrzedmiot.mana()));
        znalezionyBohater.getStatystyki().setPrzyspieszenieUmiejetnosci(znalezionyBohater.getStatystyki().getPrzyspieszenieUmiejetnosci() - znalezionyPrzedmiot.przyspieszenieUmiejetnosci());
        znalezionyBohater.getStatystyki().setSzansaNaTrafienieKrytyczne(znalezionyBohater.getStatystyki().getSzansaNaTrafienieKrytyczne() - znalezionyPrzedmiot.szansaNaTrafienieKrytyczne());
        znalezionyBohater.getStatystyki().setObrazeniaOdAtaku((znalezionyBohater.getStatystyki().getObrazeniaOdAtaku() - znalezionyPrzedmiot.obrazeniaOdAtaku()));
        znalezionyBohater.getStatystyki().setOdpornoscNaMagie((znalezionyBohater.getStatystyki().getOdpornoscNaMagie() - znalezionyPrzedmiot.odpornoscNaMagie()));
    }

    public void testUmiejetnosci(Postac znalezionyBohater) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        System.out.println(Informacje.OPCJE_TESTU_OBRAZEN.getWiadomosc());
        String wybierzPrzeciwnika = scanner.nextLine();
        Postac przeciwnik;
        String umiejetnosci;
        HashMap<String, Integer> skill;
        Wybor wybor = Wybor.get(wybierzPrzeciwnika);
        while (wybor != Wybor.RETURN) {
            switch (wybor) {
                case CHAMPIONS -> pokazBohaterow();
                case WYBIERZ_PRZECIWNIKA -> {
                    przeciwnik = wybierzPostac(znalezionyBohater);
                    pokazStatystykiOrazUmiejetnosciBohatera(przeciwnik);
                    wiadomoscPojedynku(znalezionyBohater, przeciwnik);
                }
                //METODA POJEDYNKU
                case LOSUJ_PRZECIWNIKA -> {
                    przeciwnik = wylosujPrzeciwnika(znalezionyBohater);
                    pokazStatystykiOrazUmiejetnosciBohatera(przeciwnik);
                    wiadomoscPojedynku(znalezionyBohater, przeciwnik);
                }

                //METODA POJEDYNKU
                case STWORZ_KUKLE_TRENINGOWA -> {
                    przeciwnik = tworzenieKukly(znalezionyBohater);
                    System.out.println(przeciwnik.getStatystyki().toString1());
                    umiejetnosci = wpiszUmiejetnosc();
                    if (umiejetnosci != null) {
                        liczObrazenia(umiejetnosci);
                        skill = liczObrazenia(umiejetnosci);
                        umiejetnoscObliczenia(skill, znalezionyBohater, przeciwnik);
                    }
                }
                case EXIT -> {
                    System.out.println(wybor.getWiadomosc());
                    System.exit(1);
                }
                case NIEZNANA_KOMENDA -> System.out.println(wybor.getWiadomosc());
            }


            System.out.println(Informacje.OPCJE_TESTU_OBRAZEN.getWiadomosc());
            wybierzPrzeciwnika = scanner.nextLine();
            wybor = Wybor.get(wybierzPrzeciwnika);
        }
    }
    public void wiadomoscPojedynku(Postac znalezionyBohater, Postac przeciwnik) {
        System.out.println("Pojedynek: " +
                "\n" + znalezionyBohater.getImie() + " vs " + przeciwnik.getImie());
        System.out.println("Teraz jak powinien wyglądać pojedynek?");
    }

    public Postac wylosujPrzeciwnika(Postac znalezionyBohater) {
        List<Postac> bohaterowie = new ArrayList<>();
        for (Postac postac : postacie) {
            if (!postac.getImie().equals(znalezionyBohater.getImie())) {
                bohaterowie.add(postac);
            }
        }
        Random random = new Random();
        return bohaterowie.get(random.nextInt(bohaterowie.size()));
    }

    public Postac wybierzPostac(Postac znalezionyBohater) {
        while (true) {
            System.out.println("Wybierz imie postaci z którą chcesz walczyć!");
            String wyborPostaci = scanner.nextLine().toLowerCase();

            for (Postac postac : postacie) {
                if (postac.getImie().toLowerCase().equals(wyborPostaci) && !postac.equals(znalezionyBohater)) {
                    return postac;
                }
            }
            System.out.println("Wpisałeś złą nazwę postaci, bądź taką samą którą wybrałeś!" +
                               "\nSpróbuj jeszcze raz!");
        }
    }
    public Postac tworzenieKukly(Postac znalezionyBohater) {
        Statystyki statystykiKukly = new Statystyki(znalezionyBohater.getStatystyki().getHp(),
                znalezionyBohater.getStatystyki().getPancerz(),
                znalezionyBohater.getStatystyki().getOdpornoscNaMagie());
        Postac kukla = new Postac(
                "Kukla",
                1,
                statystykiKukly,
                znalezionyBohater.getUmiejetnoscPierwsza(),
                znalezionyBohater.getUmiejetnoscDruga(),
                znalezionyBohater.getUmiejetnoscTrzecia(),
                znalezionyBohater.getUmiejetnoscCzwarta(),
                znalezionyBohater.getUmiejetnoscPasywna(),
                znalezionyBohater.getSkalowanie()
        );
        while (true) {
            try {
            //    System.out.println("Wprowadź całkowitą liczbę reprezentującą życie kukły");
             //   int statystyka = scanner.nextInt();
                kukla.getStatystyki().setHp(15);

             //   System.out.println("Wprowadź całkowitą liczbę reprezentującą pancerz kukły");
             //   statystyka = scanner.nextInt();
                kukla.getStatystyki().setPancerz(15);

                System.out.println("Wprowadź całkowitą liczbę reprezentującą odporność na magie kukły");
                int statystyka = scanner.nextInt();
                kukla.getStatystyki().setOdpornoscNaMagie(statystyka);
                scanner.nextLine();
                return kukla;
            } catch (InputMismatchException e) {
                System.out.println("Niepoprawny format liczby! Spróbuj jeszcze raz");
                scanner.next();
            }
        }
    }
    public String wpiszUmiejetnosc() {
        Scanner scanner = new Scanner(System.in);
        String umiejetnosci;
            System.out.println(Informacje.WZOR_DO_UMIEJETNOSCI.getWiadomosc());
            umiejetnosci = scanner.nextLine();
            // Sprawdzanie minimalnej długości ciągu
            if (umiejetnosci.length() < 3) {
                System.out.println("Minimalna długość ciągu umiejętności to 3 znaki (1 litera i 1 cyfra " +
                        "oddzielone spacją).");
                return null;
            }
            // Sprawdzanie maksymalnej długości ciągu
            if (umiejetnosci.length() > 15) {
                System.out.println("Maksymalna długość ciągu umiejętności to 15 znaków (4 litery i 4 cyfry " +
                        "oddzielone spacją).");
                return null;
            }
            // Sprawdzanie unikalności liter
            for (int i = 0; i < umiejetnosci.length(); i += 4) {
                char litera = umiejetnosci.charAt(i);
                if (umiejetnosci.indexOf(litera) != umiejetnosci.lastIndexOf(litera)) {
                    System.out.println("Litera " + litera + " występuje więcej niż raz!");
                    return null;
                }
            }
            // Sprawdzanie liter i cyfr
            String[] umiejetnosciArr = umiejetnosci.split(" ");
            String litery = "QWER";
            String cyfryQWE = "12345";
            String cyfryR = "123";
            for (int i = 0; i < umiejetnosciArr.length; i += 2) {
                String litera = umiejetnosciArr[i];
                String cyfra = umiejetnosciArr[i+1];
                if (!litery.contains(litera)) {
                    System.out.println("Nieprawidłowa litera " + litera);
                    return null;
                }
                if (litera.equals("R")) {
                    if (!cyfryR.contains(cyfra)) {
                        System.out.println("Nieprawidłowa cyfra " + cyfra + " po literze " + litera);
                        return null;
                    }
                } else {
                    if (!cyfryQWE.contains(cyfra)) {
                        System.out.println("Nieprawidłowa cyfra " + cyfra + " po literze " + litera);
                        return null;
                    }
                }
            }
            System.out.println("Ciąg umiejętności jest prawidłowy.");
            System.out.println("Wprowadzono poprawny string: " + umiejetnosci);
            return umiejetnosci;
    }
    public HashMap<String, Integer> liczObrazenia(String umiejetnosci) {
        String[] skille = umiejetnosci.split(" ");
        HashMap<String, Integer> umiejetnosc = new HashMap<>();
        for (int i = 0; i < skille.length; i += 2) {
            umiejetnosc.put(skille[i], Integer.valueOf(skille[i + 1]));
        }
        return umiejetnosc;
    }
    public void umiejetnoscObliczenia(HashMap<String, Integer> umiejetnosc, Postac znalezionyBohater, Postac przeciwnik) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        int obrazeniaOgolne = 0;
        BazoweObrazenia Q = znalezionyBohater.getUmiejetnoscPierwsza().getBazoweObrazenia();
        BazoweObrazenia W = znalezionyBohater.getUmiejetnoscDruga().getBazoweObrazenia();
        BazoweObrazenia E = znalezionyBohater.getUmiejetnoscTrzecia().getBazoweObrazenia();
        BazoweObrazenia R = znalezionyBohater.getUmiejetnoscCzwarta().getBazoweObrazenia();
        System.out.println("Odporność na magie przeciwnika: " + przeciwnik.getStatystyki().getOdpornoscNaMagie());

        for (String key: umiejetnosc.keySet()) {
            if (key.equalsIgnoreCase("Q")) {
                System.out.println("Umiejętność: " + znalezionyBohater.getUmiejetnoscPierwsza().getNazwa() + " (Q)");
                Integer dmg = (Integer) Q.getClass().getMethod("getLvl" + umiejetnosc.get(key)).invoke(Q);
                int obrazenia = (int) (dmg + (znalezionyBohater.getUmiejetnoscPierwsza().getSkalowanieMocyUmiejetnosci() * znalezionyBohater.getStatystyki().getMocUmiejetnosci()));
                if (przeciwnik.getStatystyki().getOdpornoscNaMagie() > 0) {
                    obrazenia = obrazenia / (1 + przeciwnik.getStatystyki().getOdpornoscNaMagie() / 100);
                }
                System.out.println("Lvl " + umiejetnosc.get(key) + " ze skalowaniem: " + obrazenia);
                obrazeniaOgolne += obrazenia;

            }
            if (key.equalsIgnoreCase("W")) {
                System.out.println("Umiejętność: " + znalezionyBohater.getUmiejetnoscDruga().getNazwa() + " (W)");

                Integer dmg = (Integer) W.getClass().getMethod("getLvl" + umiejetnosc.get(key)).invoke(W);
                int obrazenia = (int) (dmg + (znalezionyBohater.getUmiejetnoscDruga().getSkalowanieMocyUmiejetnosci() * znalezionyBohater.getStatystyki().getMocUmiejetnosci()));
                if (przeciwnik.getStatystyki().getOdpornoscNaMagie() > 0) {
                    obrazenia = obrazenia / (1 + przeciwnik.getStatystyki().getOdpornoscNaMagie() / 100);
                }
                System.out.println("Lvl " + umiejetnosc.get(key) + " ze skalowaniem: " + obrazenia);
                obrazeniaOgolne += obrazenia;
            }
            if (key.equalsIgnoreCase("E")) {
                System.out.println("Umiejętność: " + znalezionyBohater.getUmiejetnoscTrzecia().getNazwa() + " (E)");

                Integer dmg = (Integer) E.getClass().getMethod("getLvl" + umiejetnosc.get(key)).invoke(E);
                int obrazenia = (int) (dmg + (znalezionyBohater.getUmiejetnoscTrzecia().getSkalowanieMocyUmiejetnosci() * znalezionyBohater.getStatystyki().getMocUmiejetnosci()));
                if (przeciwnik.getStatystyki().getOdpornoscNaMagie() > 0) {
                    obrazenia = obrazenia / (1 + przeciwnik.getStatystyki().getOdpornoscNaMagie() / 100);
                }
                System.out.println("Lvl " + umiejetnosc.get(key) + " ze skalowaniem: " + obrazenia);
                obrazeniaOgolne += obrazenia;
            }
            if (key.equalsIgnoreCase("R")) {
                System.out.println("Umiejętność: " + znalezionyBohater.getUmiejetnoscCzwarta().getNazwa() + " (R)");
                Integer dmg = (Integer) R.getClass().getMethod("getLvl" + umiejetnosc.get(key)).invoke(R);
                int obrazenia = (int) (dmg + (znalezionyBohater.getUmiejetnoscCzwarta().getSkalowanieMocyUmiejetnosci() * znalezionyBohater.getStatystyki().getMocUmiejetnosci()));
                if (przeciwnik.getStatystyki().getOdpornoscNaMagie() > 0) {
                    obrazenia = obrazenia / (1 + przeciwnik.getStatystyki().getOdpornoscNaMagie() / 100);
                }
                System.out.println("Lvl " + umiejetnosc.get(key) + " ze skalowaniem: " + obrazenia);
                obrazeniaOgolne += obrazenia;            }
        }
        System.out.println("Obrazenia łączne z tych umiejętności: " + obrazeniaOgolne);
    }
}


