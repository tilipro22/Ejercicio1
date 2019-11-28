package com.tili.log;

import com.tili.model.Item;

import java.io.*;
import java.util.List;

public class LogResult {

    private String FILE_NAME = "items.log";
    private String ENCODING_UTF8 = "utf-8";

    private Writer writer;

    public LogResult() {
        writer = null;
        String path = getClass().getProtectionDomain().getCodeSource().getLocation() + FILE_NAME;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(path.substring(6, path.length())), ENCODING_UTF8));
        } catch (IOException e) {
            System.out.println("No pudo crearse el archivo de log");
        }
    }

    public void writeItems(String user, List<Item> items) {
        int i = 0;

        try {
            writer.write("User " + user);
            writer.write(System.getProperty("line.separator"));
            for (Item item :
                    items) {
                System.out.println("Item " + (i + 1) + ": id=" + item.getId() + ", title=" + item.getTitle() + ", category=" + item.getCategory_id() + ", category_name=" + item.getName());
                writer.write("Item " + (i + 1) + ": id=" + item.getId() + ", title=" + item.getTitle() + ", category=" + item.getCategory_id() + ", category_name=" + item.getName());
                writer.write(System.getProperty("line.separator"));
                i++;
            }
        } catch (IOException e) {
            System.out.println("No pudo escribirse los datos del item. Message: " + e.getMessage() );
        }
    }

    public void close() {
        try {writer.close();} catch (Exception ex) {/*ignore*/}
    }

}
