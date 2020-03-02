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
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
    private final List<String> hardCodedJson = new ArrayList();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Query query = new Query("Comment").addSort("timestamp", SortDirection.DESCENDING);

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        PreparedQuery results = datastore.prepare(query);

        List<Comment> comments = new ArrayList<>();
        for (Entity entity : results.asIterable()) {
        long id = entity.getKey().getId();
        String name = (String) entity.getProperty("name");
        String text = (String) entity.getProperty("text");
        long timestamp = (long) entity.getProperty("timestamp");

        Comment comment = new Comment(name, text, timestamp);
        comments.add(comment);
        }

        Gson gson = new Gson();

        response.setContentType("application/json;");
        response.getWriter().println(gson.toJson(comments));
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get the input from the form.
        long timestamp = System.currentTimeMillis();
        String name = getParameter(request, "name", "");
        String text = getParameter(request, "comment", "");
        Comment comment = new Comment(name, text, timestamp);

        Entity commentEntity = new Entity("Comment");
        commentEntity.setProperty("name", name);
        commentEntity.setProperty("text", text);
        commentEntity.setProperty("timestamp", timestamp);

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(commentEntity);
        
        String json = convertToJsonUsingGson(comment);

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

    private String convertToJsonUsingGson(Comment comment) {
        Gson gson = new Gson();
        String json = gson.toJson(comment);
        return json;
    }

}