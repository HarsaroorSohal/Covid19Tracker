package com.harsaroor.covid19.services;

import com.harsaroor.covid19.models.CovidData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@Service
public class CovidDataService {

    //Source for fetching the updated covid19 cases data
    private String COVID_DATA_SOURCE = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private List<CovidData> allData = new ArrayList<>(); //?what is an arraylist

    public CovidData getCountryCovidData(String name) {
        for (CovidData covidData : allData) {
            if(covidData.getCountry().equals(name))
                return covidData;
        }
        return null;
    }

    public List<CovidData> getAllData() {
        return allData;
    }

    //Run the function everytime the application runs
    @PostConstruct
    //Run the application every day (cron = sec min hour day mon year)
    @Scheduled(cron = "* * * 1 * *")
    public void FetchCovidData() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(COVID_DATA_SOURCE))
                .build();

        //Sending the request and handling the body of the response i.e converting it to a string
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvBodyReader = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);

        HashSet<String> uniqueCountries=new HashSet();
        HashMap<String, Integer> CountryCases = new HashMap<>();
        HashMap<String, Integer> CountryChanges = new HashMap<>();

        for (CSVRecord record : records) {
            CovidData covidData = new CovidData();
            String countryName = record.get("Country/Region");
            int latestCases = Integer.parseInt(record.get(record.size() - 1));
            int yesterdaysCases = Integer.parseInt(record.get(record.size() - 2));
            int newCases = Math.abs(latestCases - yesterdaysCases);


            if(!uniqueCountries.contains(countryName)) {
                covidData.setCountry(countryName);
                covidData.setLatestTotalCases(latestCases);
                covidData.setNewCases(newCases);
                allData.add(covidData);
                uniqueCountries.add(countryName);
                CountryCases.put(countryName, latestCases);
                CountryChanges.put(countryName, newCases);
            }
            else {
                int currCountCases = CountryCases.get(countryName);
                int currCountChanges = CountryChanges.get(countryName);
                CountryCases.put(countryName, currCountCases+latestCases);
                CountryChanges.put(countryName, currCountChanges+newCases);
            }
       }
        for (CovidData covidData : allData) {
            covidData.setLatestTotalCases(CountryCases.get(covidData.getCountry()));
            covidData.setNewCases(CountryChanges.get(covidData.getCountry()));
        }

    }
}
