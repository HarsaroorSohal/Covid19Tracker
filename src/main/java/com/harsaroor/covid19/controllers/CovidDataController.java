package com.harsaroor.covid19.controllers;

import com.harsaroor.covid19.models.CovidData;
import com.harsaroor.covid19.services.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//Not a REST Controller, this returns an HTML page
@Controller
public class CovidDataController {

    @Autowired
    private CovidDataService covidDataService;

    @RequestMapping("/")
    public String covidData(Model model){
        List<CovidData> allStats = covidDataService.getAllData();
        int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getNewCases()).sum();
        model.addAttribute("totalCases", totalCases);
        model.addAttribute("totalNewCases", totalNewCases);
        model.addAttribute("covidData", covidDataService.getAllData());
        return "covidData";
    }
}
