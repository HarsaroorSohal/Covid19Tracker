package com.harsaroor.covid19.controllers;

import com.harsaroor.covid19.models.CovidData;
import com.harsaroor.covid19.services.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CovidDataController1 {

    @Autowired
    public CovidDataService covidDataService;

    @RequestMapping("/data")
    public List<CovidData> getCovidData() {
        return(covidDataService.getCovidData());
    }
    @RequestMapping("/data/{name}")
    public Optional<CovidData> getCountryCovidData(@PathVariable String name) {
        return(covidDataService.getCountryCovidData(name));
    }
}
