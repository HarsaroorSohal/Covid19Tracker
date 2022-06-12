package com.harsaroor.covid19.controllers;

import com.harsaroor.covid19.models.CovidData;
import com.harsaroor.covid19.services.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CovidDataController1 {

    @Autowired
    private CovidDataService covidDataService;

    @RequestMapping("/data")
    public List<CovidData> getCovidData() {
        return(covidDataService.getAllData());
    }
    @RequestMapping("/data/{name}")
    public CovidData getCountryCovidData(@PathVariable String name) {
        return(covidDataService.getCountryCovidData(name));
    }
}
