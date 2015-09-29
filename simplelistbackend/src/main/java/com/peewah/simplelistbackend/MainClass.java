/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peewah.simplelistbackend;

import com.google.gson.Gson;
import java.util.ArrayList;
import model.ListItem;
import spark.Spark;
import static spark.Spark.*;

/**
 *
 * @author csacanam
 */
public class MainClass
{

    public static void main(String[] args)
    {
        Gson gson = new Gson();

        //Enable CORS
        Spark.options("/*", (request, response) ->
        {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null)
            {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null)
            {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        Spark.before((request, response) ->
        {
            response.header("Access-Control-Allow-Origin", "*");
        });

        //In a real application this information is in a database
        ArrayList<ListItem> list = new ArrayList<>();
        list.add(new ListItem("Hello World from Slack"));
        list.add(new ListItem("An Item"));
        list.add(new ListItem("I <3 Colombia"));
        list.add(new ListItem("Cali is the capital of Salsa"));
        list.add(new ListItem("Android or iOS?"));
        list.add(new ListItem("This is awesome"));
        list.add(new ListItem("I love the backend"));
        list.add(new ListItem("Objective-C or Swift?"));
        list.add(new ListItem("I'm a developer"));
        list.add(new ListItem("Peewah is awesome"));

        //GET Web service
        get("/list", (req, rsp) -> list, gson::toJson);
    }
}
