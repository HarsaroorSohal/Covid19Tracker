package com.harsaroor.covid19.controllers;

import com.harsaroor.covid19.models.CovidData;
import com.harsaroor.covid19.services.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

//Not a REST Controller, this returns an HTML page
@Controller
public class CovidDataController {

    @Autowired
    public CovidDataService covidDataService = new CovidDataService();

    @RequestMapping("/")
    public String covidData(Model model){
        List<CovidData> allStats = covidDataService.getCovidData();
        int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getNewCases()).sum();
        model.addAttribute("totalCases", totalCases);
        model.addAttribute("totalNewCases", totalNewCases);
        model.addAttribute("covidData", covidDataService.getCovidData());
        return "covidData";
    }
}
