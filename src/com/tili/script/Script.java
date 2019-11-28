package com.tili.script;

import com.tili.log.LogResult;
import com.tili.model.Item;
import com.tili.services.HttpRequestML;

import java.util.Arrays;
import java.util.List;

public class Script {

    public static void main (String[] args) {

        if (args.length > 0) {
            LogResult log = new LogResult();
            List<String> users = Arrays.asList(args);
            HttpRequestML httpRequestML = new HttpRequestML();

            for (String user:
                 users) {
                System.out.println("user: " + user);
                List<Item> items = httpRequestML.getItemsByUser(user);
                log.writeItems(user, items);
            }
            log.close();
        }
        else {
            System.out.println("No ingreso usuarios como argumento");
        }

    }
}
