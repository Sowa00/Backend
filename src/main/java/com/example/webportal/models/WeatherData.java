package com.example.webportal.models;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;



@Entity
@Builder
@Table(name = "weather_data")

public class WeatherData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_by_date_time")
    private Long idByDateTime;
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    public Long getIdByDateTime() {
        return idByDateTime;
    }

    public void setIdByDateTime(Long idByDateTime) {
        this.idByDateTime = idByDateTime;
    }

    @Column(name = "temperature_out")
    private double temperatureOut;

    @Column(name = "hi_temperature")
    private double hiTemperature;

    @Column(name = "low_temperature")
    private double lowTemperature;

    @Column(name = "out_humidity")
    private int outHumidity;

    @Column(name = "dew_pt")
    private double dewPt;

    @Column(name = "wind_speed")
    private double windSpeed;

    @Column(name = "wind_direction")
    private String windDirection;

    @Column(name = "wind_run")
    private double windRun;

    @Column(name = "hi_speed")
    private double hiSpeed;

    @Column(name = "hi_direction")
    private String hiDirection;

    @Column(name = "wind_chill")
    private double windChill;

    @Column(name = "heat_index")
    private double heatIndex;

    @Column(name = "thw_index")
    private double thwIndex;

    @Column(name = "bar")
    private double bar;

    @Column(name = "rain")
    private double rain;

    @Column(name = "rain_rate")
    private double rainRate;

    @Column(name = "heat_dd")
    private double heatDd;

    @Column(name = "cool_dd")
    private double coolDd;

    @Column(name = "temperature_in")
    private double temperatureIn;

    @Column(name = "in_humidity")
    private int inHumidity;

    @Column(name = "in_dew")
    private double inDew;

    @Column(name = "in_heat")
    private double inHeat;

    @Column(name = "in_emc")
    private double inEMC;

    @Column(name = "in_air_density")
    private double inAirDensity;

    @Column(name = "wind_samp")
    private int windSamp;

    @Column(name = "wind_tx")
    private int windTx;

    @Column(name = "iss_recept")
    private double ISSRecept;

    @Column(name = "arc_int")
    private int arcInt;

    public WeatherData(){}

    public WeatherData(Long id, Long idByDateTime, LocalDate date, LocalTime time, double temperatureOut, double hiTemperature, double lowTemperature, int outHumidity, double dewPt, double windSpeed, String windDirection, double windRun, double hiSpeed, String hiDirection, double windChill, double heatIndex, double thwIndex, double bar, double rain, double rainRate, double heatDd, double coolDd, double temperatureIn, int inHumidity, double inDew, double inHeat, double inEMC, double inAirDensity, int windSamp, int windTx, double ISSRecept, int arcInt) {
        this.id = id;
        this.idByDateTime = idByDateTime;
        this.date = date;
        this.time = time;
        this.temperatureOut = temperatureOut;
        this.hiTemperature = hiTemperature;
        this.lowTemperature = lowTemperature;
        this.outHumidity = outHumidity;
        this.dewPt = dewPt;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.windRun = windRun;
        this.hiSpeed = hiSpeed;
        this.hiDirection = hiDirection;
        this.windChill = windChill;
        this.heatIndex = heatIndex;
        this.thwIndex = thwIndex;
        this.bar = bar;
        this.rain = rain;
        this.rainRate = rainRate;
        this.heatDd = heatDd;
        this.coolDd = coolDd;
        this.temperatureIn = temperatureIn;
        this.inHumidity = inHumidity;
        this.inDew = inDew;
        this.inHeat = inHeat;
        this.inEMC = inEMC;
        this.inAirDensity = inAirDensity;
        this.windSamp = windSamp;
        this.windTx = windTx;
        this.ISSRecept = ISSRecept;
        this.arcInt = arcInt;
    }

    public Long getId(){
        return id;
    }

    public void setId(long id){
        this.id=id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public double getTemperatureOut() {
        return temperatureOut;
    }

    public void setTemperatureOut(double temperatureOut) {
        this.temperatureOut = temperatureOut;
    }

    public double getHiTemperature() {
        return hiTemperature;
    }

    public void setHiTemperature(double hiTemperature) {
        this.hiTemperature = hiTemperature;
    }

    public double getLowTemperature() {
        return lowTemperature;
    }

    public void setLowTemperature(double lowTemperature) {
        this.lowTemperature = lowTemperature;
    }

    public int getOutHuminity() {
        return outHumidity;
    }

    public void setOutHuminity(int outHuminity) {
        this.outHumidity = outHuminity;
    }

    public double getDewPt() {
        return dewPt;
    }

    public void setDewPt(double dewPt) {
        this.dewPt = dewPt;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public double getWindRun() {
        return windRun;
    }

    public void setWindRun(double windRun) {
        this.windRun = windRun;
    }

    public double getHiSpeed() {
        return hiSpeed;
    }

    public void setHiSpeed(double hiSpeed) {
        this.hiSpeed = hiSpeed;
    }

    public String getHiDirection() {
        return hiDirection;
    }

    public void setHiDirection(String hiDirection) {
        this.hiDirection = hiDirection;
    }

    public double getWindChill() {
        return windChill;
    }

    public void setWindChill(double windChill) {
        this.windChill = windChill;
    }

    public double getHeatIndex() {
        return heatIndex;
    }

    public void setHeatIndex(double heatIndex) {
        this.heatIndex = heatIndex;
    }

    public double getThwIndex() {
        return thwIndex;
    }

    public void setThwIndex(double thwIndex) {
        this.thwIndex = thwIndex;
    }

    public double getBar() {
        return bar;
    }

    public void setBar(double bar) {
        this.bar = bar;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    public double getRainRate() {
        return rainRate;
    }

    public void setRainRate(double rainRate) {
        this.rainRate = rainRate;
    }

    public double getHeatDd() {
        return heatDd;
    }

    public void setHeatDd(double heatDd) {
        this.heatDd = heatDd;
    }

    public double getCoolDd() {
        return coolDd;
    }

    public void setCoolDd(double coolDd) {
        this.coolDd = coolDd;
    }

    public double getTemperatureIn() {
        return temperatureIn;
    }

    public void setTemperatureIn(double temperatureIn) {
        this.temperatureIn = temperatureIn;
    }

    public int getInHumidity() {
        return inHumidity;
    }

    public void setInHumidity(int inHuminity) {
        this.inHumidity = inHuminity;
    }

    public double getInDew() {
        return inDew;
    }

    public void setInDew(double inDew) {
        this.inDew = inDew;
    }

    public double getInHeat() {
        return inHeat;
    }

    public void setInHeat(double inHeat) {
        this.inHeat = inHeat;
    }

    public double getInEMC() {
        return inEMC;
    }

    public void setInEMC(double inEMC) {
        this.inEMC = inEMC;
    }

    public double getInAirDensity() {
        return inAirDensity;
    }

    public void setInAirDensity(double inAirDensity) {
        this.inAirDensity = inAirDensity;
    }

    public int getWindSamp() {
        return windSamp;
    }

    public void setWindSamp(int windSamp) {
        this.windSamp = windSamp;
    }

    public int getWindTx() {
        return windTx;
    }

    public void setWindTx(int windTx) {
        this.windTx = windTx;
    }

    public double getISSRecept() {
        return ISSRecept;
    }

    public void setISSRecept(double ISSRecept) {
        this.ISSRecept = ISSRecept;
    }

    public int getArcInt() {
        return arcInt;
    }

    public void setArcInt(int arcInt) {
        this.arcInt = arcInt;
    }
}
