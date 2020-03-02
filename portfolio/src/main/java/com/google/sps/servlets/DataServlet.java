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

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
    private final List<String> hardCodedJson = new ArrayList();
    private final List<Comment> comments = new ArrayList();

    @Override
    public void init() {
        jsonInit();
    }

    public void jsonInit() {
        hardCodedJson.add("This is my first comment!");
        hardCodedJson.add("This is my 2nd comment!");
        hardCodedJson.add("This is my 3rd and last comment!");

        // String json1 = "{";
        // json1 += "\"name\": ";
        // json1 += "\"" + "Murilo" + "\"";
        // json1 += "}";

        // String json2 = "{";
        // json2 += "\"age\": ";
        // json2 += "\"" + "20" + "\"";
        // json2 += "}";

        // String json3 = "{";
        // json3 += "\"gender\": ";
        // json3 += "\"" + "Male" + "\"";
        // json3 += ", ";
        // json3 += "\"height\": ";
        // json3 += "\"" + "1.75" + "\"";
        // json3 += "}";

        // hardCodedJson.add(json1);
        // hardCodedJson.add(json2);
        // hardCodedJson.add(json3);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // String json = convertArrayListToJson(hardCodedJson);
        String json = convertToJsonUsingGson(comments);
        response.setContentType("application/json;");
        response.getWriter().println(json);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get the input from the form.
        String name = getParameter(request, "name", "");
        String text = getParameter(request, "comment", "");
        
        Comment comment = new Comment(name, text);
        comments.add(comment);

        String json = convertToJsonUsingGson(comments);

        // Respond with the result.
        // response.setContentType("text/html;");
        response.setContentType("application/json;");
        response.getWriter().println(json);
    }

    private String getParameter(HttpServletRequest request, String name, String defaultValue) {
        String value = request.getParameter(name);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    public String convertArrayListToJson(List<String> list) {
        // Starting json string
        String json = "{";
        json += "\"messages\": [";
        
        // Adding all hard-coded messages
        for (int i = 0; i < list.size()-1; i++) {
            json += "\"" + list.get(i) + "\",";
        }

        // Adding very last message
        json += "\"" + list.get(list.size()-1) + "\"";
        
        // Finishing json string
        json += "]";
        json += "}";

        // Return json string
        return json;
    }

    private String convertToJsonUsingGson(List<Comment> list) {
        Gson gson = new Gson();
        String json = gson.toJson(comments);
        return json;
    }

}