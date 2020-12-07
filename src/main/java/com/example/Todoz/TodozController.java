package com.example.Todoz;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class TodozController {

    @GetMapping("/")
    public String getTodos(Model model, @RequestParam(value = "url", required = false, defaultValue = "1") String url) throws IOException, JSONException {
        String urlen = "https://best-todolist.azurewebsites.net/todos";
        List<String> list = new ArrayList<>();
        String htmlList = "";
        try {
            JSONArray json = readJsonFromUrl(urlen);
            for (int i = 0; i < json.length(); i++) {
                list.add(json.get(i).toString());
            }
            //split
            for (int i = 0; i < list.size(); i++) {
                String[] tmp = list.get(i).split(",");

                for (int j = 0; j < tmp.length; j++) {
                    System.out.println(tmp[j]);
                    htmlList += tmp[j];
                }

            }
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
        model.addAttribute("url", url);
        model.addAttribute("list", htmlList);

        System.out.println("List length: " + list.size());
        System.out.println("A list item: " + list.get(0));
        return "index";

    }

    public static JSONArray readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();

        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONArray json = new JSONArray(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }


}
