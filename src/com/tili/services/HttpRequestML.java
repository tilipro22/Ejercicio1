package com.tili.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tili.model.Item;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HttpRequestML {

    private final int OPT_SELLER = 1;
    private final int OPT_CATEGORIES = 2;

    private final String REQUEST_GET = "curl -X GET ";
    private final String REQUEST_SELLER = "https://api.mercadolibre.com/sites/MLA/search?seller_id=";
    private final String REQUEST_CATEGORIES = "https://api.mercadolibre.com/categories/";

    private InputStream getContentRequest(int option, String params) {
        String url = REQUEST_GET;

        switch (option) {
            case OPT_SELLER:
                url += REQUEST_SELLER + params;
                break;

            case OPT_CATEGORIES:
                url += REQUEST_CATEGORIES + params;
                break;
        }

        Process process = null;
        try {
            process = Runtime.getRuntime().exec(url);
        } catch (IOException e) {
            System.out.println("Error al ejecutar: " + url);
        }
        finally {
            return process != null ? process.getInputStream() : null;
        }
    }

    public List<Item> getItemsByUser(String user) {
        List<Item> items = new ArrayList<Item>();
        InputStream content = getContentRequest(OPT_SELLER, user);

        if (content != null) {
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse(new InputStreamReader(content));
            JsonArray jsonArray = jsonElement.getAsJsonObject().get("results").getAsJsonArray();

            for (int i=0; i < jsonArray.size(); i++) {
                Item item = generateItem(jsonArray.get(i).getAsJsonObject());
                items.add(item);
            }
        }

        return items;
    }

    private Item generateItem(JsonObject jsonItem) {

        String id = jsonItem.get("id").toString();
        String title = jsonItem.get("title").toString();
        String category = jsonItem.get("category_id").toString();

        InputStream contentRequest = getContentRequest(OPT_CATEGORIES, category);
        JsonElement jsonElement = new JsonParser().parse(new InputStreamReader(contentRequest));

        String categoryName = jsonElement.getAsJsonObject().get("name").toString();
        return new Item(id, title, category, categoryName);
    }
}
