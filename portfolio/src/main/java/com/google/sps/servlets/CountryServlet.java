// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import java.util.*;
import java.io.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

/** Servlet that returns random country-capital pair. */
@WebServlet("/country")
public class CountryServlet extends HttpServlet {
    private final List<Country> countries = new ArrayList();

    @Override
    public void init() {
        countryInit();
    }

    public void countryInit() {
        // South America
        countries.add(new Country("Brazil", "Brasília"));
        countries.add(new Country("Argentina", "Buenos Aires"));
        countries.add(new Country("Colombia", "Bogotá"));
        countries.add(new Country("Venezuela", "Caracas"));
        countries.add(new Country("Uruguay", "Montevidéu"));

        // Central America
        countries.add(new Country("Panama", "Panama City"));
        countries.add(new Country("Guatemala", "Guatemala City"));
        countries.add(new Country("Haiti", "Port-au-Prince"));
        countries.add(new Country("Cuba", "Havana"));
        countries.add(new Country("Dominican Republic", "Santo Domingo"));
        
        // North America
        countries.add(new Country("United States", "Washington"));
        countries.add(new Country("Canada", "Ottawa"));
        countries.add(new Country("Mexico", "Mexico City"));
        
        // Asia
        countries.add(new Country("Russia", "Moscow"));
        countries.add(new Country("India", "New Delhi"));
        countries.add(new Country("Pakistan", "Islamabad"));
        countries.add(new Country("Japan", "Tokyo"));
        countries.add(new Country("China", "Beijing"));
        
        // Africa
        countries.add(new Country("South Africa (any one of the 3 capitals)", "Bloemfontein, Cape Town, Pretoria"));
        countries.add(new Country("Morocco", "Rabat"));
        countries.add(new Country("Egypt", "Cairo"));
        countries.add(new Country("Algeria", "Algiers"));
        countries.add(new Country("Kenya", "Nairobi"));

        // Europe
        countries.add(new Country("France", "Paris"));
        countries.add(new Country("Germany", "Berlin"));
        countries.add(new Country("England", "London"));
        countries.add(new Country("Wales", "Cardiff"));
        countries.add(new Country("Ireland", "Dublin"));
        countries.add(new Country("Northern Ireland", "Belfast"));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Country> sublist = sublistOfCountries(10, countries);
        response.setContentType("text/html;");
        response.getWriter().println(convertToJsonUsingGson(sublist));
        // response.getWriter().println(countries.get(rand.nextInt(countries.size())));
    }

    public List<Country> sublistOfCountries(int size, List<Country> countriesOriginal) {
        List<Country> countries = new ArrayList<>(countriesOriginal);
        int listSize = countries.size();

        if (size > listSize) {
            return null;
        }

        List<Country> sublist = new ArrayList<>();
        Country country;
        Random rand = new Random();        

        while (size > 0) {
            country = countries.remove(rand.nextInt(listSize));
            sublist.add(country);
            size--;
            listSize--;   
        }

        return sublist;
    }

    private String convertToJsonUsingGson(List<Country> countries) {
        Gson gson = new Gson();
        String json = gson.toJson(countries);
        return json;
    }

}
