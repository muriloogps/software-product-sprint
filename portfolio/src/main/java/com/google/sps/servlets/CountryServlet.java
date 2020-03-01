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
        Random rand = new Random();
        response.setContentType("text/html;");
        response.getWriter().println(countries.get(rand.nextInt(countries.size())));
    }

}
