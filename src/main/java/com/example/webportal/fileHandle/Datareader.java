package com.example.webportal.fileHandle;

import com.example.webportal.models.WeatherData;
import com.example.webportal.repositories.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Component
public class Datareader {


    private WeatherDataRepository weatherDataRepository;

    @Autowired
    public void setWeatherDataRepository(WeatherDataRepository weatherDataRepository) {
        this.weatherDataRepository = weatherDataRepository;
    }


    public void readFile() {
        try {


            File file = new File("C:\\Users\\sowa0\\Desktop\\Praca inżynierska stacja pogodowa\\AllDataWithChangedUnits.txt");

            Scanner input = new Scanner(file);

            while (input.hasNext()) {
                String word = input.next();
                System.out.println(word);
                if (word.equals("Int.")) {
                    while (input.hasNext()) {
                        String dateStr = input.next();
                        String timeStr = input.next();
                        double tempOutt = parseDouble(input.next());
                        double hiTempp = parseDouble(input.next());
                        double lowTempp = parseDouble(input.next());
                        int outHumm = parseInt(input.next());
                        double dewPtt = parseDouble(input.next());
                        double windSpeedd = parseDouble(input.next());
                        String windDirr = input.next();
                        double windRunn = parseDouble(input.next());
                        double hiSpeedd = parseDouble(input.next());
                        String hiDirr = input.next();
                        double windChilll = parseDouble(input.next());
                        double heatIndexx = parseDouble(input.next());
                        double THWIndexx = parseDouble(input.next());
                        double barr = parseDouble(input.next());
                        double rainn = parseDouble(input.next());
                        double rainRatee = parseDouble(input.next());
                        double heatDDd = parseDouble(input.next());
                        double coolDDd = parseDouble(input.next());
                        double inTempp = parseDouble(input.next());
                        int inHumm = parseInt(input.next());
                        double inDeww = parseDouble(input.next());
                        double inHeatt = parseDouble(input.next());
                        double inEMCc = parseDouble(input.next());
                        double inAirDensityy = parseDouble(input.next());
                        int windSampp = parseInt(input.next());
                        int windTxx = parseInt(input.next());
                        double issReceptt = parseDouble(input.next());
                        int arcIntt = parseInt(input.next());

                        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");
                        LocalDate datee = LocalDate.parse(dateStr, dateFormatter);

                        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");
                        LocalTime timee = LocalTime.parse(timeStr, timeFormatter);


                        WeatherData weatherData = WeatherData.builder()
                                .date(datee)                  //data
                                .time(timee)                  //czas
                                .temperatureOut(tempOutt)     //zewnetrzna temperatura
                                .hiTemperature(hiTempp)       //najwyzsza temperatura dnia
                                .lowTemperature(lowTempp)     //najnizsza temperatura dnia
                                .outHumidity(outHumm)         //zewnetrzna wilgotnosc
                                .dewPt(dewPtt)                //temperatura punktu rosy
                                .windSpeed(windSpeedd)        //prekosc wiatru
                                .windDirection(windDirr)      //kierunek wiatru
                                .windRun(windRunn)            //suma przebytej odlegosci przez wiatr
                                .hiSpeed(hiSpeedd)            //najwyzsza predkosc wiatru
                                .hiDirection(hiDirr)          //kierunek najwyzszej predkosci wiatru
                                .windChill(windChilll)        //odczucie temperatury wiatru
                                .heatIndex(heatIndexx)        //wskaznik cieplny
                                .thwIndex(THWIndexx)          //wskaznik cieplny THW
                                .bar(barr)                    //cisnienie atmosferyczne
                                .rain(rainn)                  //suma opadow deszczu
                                .rainRate(rainRatee)          //tempo opadow deszczu
                                .heatDd(heatDDd)              //temperatura roznicowa
                                .coolDd(coolDDd)              //temperatura roznicowa chlodzaca
                                .temperatureIn(inTempp)       //temperatura wewnatrz
                                .inHumidity(inHumm)           //wilgotnosc wewnatrz
                                .inDew(inDeww)                //temperatura punktu rosy wewnatrz
                                .inHeat(inHeatt)              //wewnetrzna temperatura powietrza
                                .inEMC(inEMCc)                //wilgotnosc ekwiwalentna wewnatrz
                                .inAirDensity(inAirDensityy)  //gestosc powietrza wewnatrz
                                .windSamp(windSampp)          //liczba probek wiatru
                                .windTx(windTxx)              //temperatura nadajnika wiatrowego
                                .ISSRecept(issReceptt)        //procentowa jakosc odbioru danych
                                .arcInt(arcIntt)              //
                                .build();
                        weatherData.setIdByDateTime(setIDToWD(weatherData.getDate(), weatherData.getTime()));
                        weatherDataRepository.save(weatherData);
                    }
                    break;
                }
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public Long setIDToWD(LocalDate date, LocalTime time) {
        long id;
        String d = date.toString();
        String t = time.toString();
        String idStr = d + t;
        String idSt = idStr.replace("-", "");
        String idS = idSt.replace(":", "");
        String idToStr = idS.replace(".", "");
        id = Long.parseLong(idToStr);
        return id;
    }


    public double parseDouble(String input) {
        if (input.equals("---") || input.equals("------")) {
            return 0.0;
        }

        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.err.println("Nie można przekształcić '" + input + "' na liczbę.");
            return 0.0;
        }
    }

    public int parseInt(String input) {
        if (input.equals("---") || input.equals("------")) {
            return 0;
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.err.println("Nie można przekształcić '" + input + "' na liczbę całkowitą.");
            return 0;
        }
    }

}
