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

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
    private final List<Country> countries = new ArrayList();

    @Override
    public void init() {
        countries.add(new Country("Germany", "Berlin"));
        countries.add(new Country("Brazil", "Brasilia"));
        countries.add(new Country("United States", "Washington"));
        countries.add(new Country("France", "Paris"));
        countries.add(new Country("Canada", "Ottawa"));
        countries.add(new Country("Russia", "Moscow"));
        countries.add(new Country("South Africa (any one of the 3 capitals)", "Bloemfontein, Cape Town, Pretoria"));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // response.setContentType("text/html;");
        // response.getWriter().println("<h1>Hello Murilo!</h1>");
        //String[] pair = new String[2];
        //response[0] = countries.get(4);
        //response[1] = capitals.get(4);
        Random rand = new Random();

        response.setContentType("text/html;");
        response.getWriter().println(countries.get(rand.nextInt(countries.size())));
    }

}

// @WebServlet("/country")
// class Country extends HttpServlet {

//     public String[] doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//         ArrayList<String> countries = populateCountries();
//         ArrayList<String> capitals = populateCapitals();
//         String[] response = new String[2];
//         response[0] = countries.get(4);
//         response[1] = capitals.get(4);
//         return response;
//     }

//     public ArrayList populateCountries() {
//         ArrayList<String> countries = new ArrayList();
//         countries.add("Germany");
//         countries.add("Brazil");
//         countries.add("United States");
//         countries.add("France");
//         countries.add("Canada");
//         countries.add("Russia");
//         countries.add("China");
//         countries.add("Australia");
//         countries.add("Venezuela");
//         countries.add("India");
//         countries.add("Switzerland");
//         countries.add("Nigeria");
//         countries.add("Panama");
//         countries.add("Jamaica");
//         countries.add("South Africa (any one of the 3 capitals)");
//         countries.add("Italy");
//         countries.add("Mexico");
//         countries.add("Argentina");
//         countries.add("Colombia");
//         countries.add("Turkey");
//         return countries;
//     }

//     public ArrayList populateCapitals() {
//         ArrayList<String> capitals = new ArrayList();
//         countries.add("Berlin");
//         countries.add("Brasilia");
//         countries.add("Washington");
//         countries.add("Paris");
//         countries.add("Ottawa");
//         countries.add("Moscow");
//         countries.add("Beijing");
//         countries.add("Canberra (yeah, I know...)");
//         countries.add("Caracas");
//         countries.add("New Delhi");
//         countries.add("Bern");
//         countries.add("Abuja");
//         countries.add("Panama City");
//         countries.add("Kingston");
//         countries.add("Bloemfontein, Cape Town, Pretoria");
//         countries.add("Rome");
//         countries.add("Mexico City");
//         countries.add("Buenos Aires");
//         countries.add("Bogota");
//         countries.add("Ankara");
//         return capitals;
//     }
// }
