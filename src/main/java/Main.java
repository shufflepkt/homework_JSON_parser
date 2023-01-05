import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Employee> initList = new ArrayList<>();
        initList.add(new Employee(1, "John", "Smith", "USA", 25));
        initList.add(new Employee(2, "Inav", "Petrov", "RU", 23));

        String initJson = listToJson(initList);
        writeString(initJson);

        String json = readString("new_data.json");

        List<Employee> list = jsonToList(json);

        System.out.println(list.get(0));
        System.out.println(list.get(1));
    }

    public static List<Employee> jsonToList(String json) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Type listType = new TypeToken<List<Employee>>() {
        }.getType();
        List<Employee> list = gson.fromJson(json, listType);

        return list;
    }

    public static String readString(String file) {
        String json = null;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            json = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static String listToJson(List<Employee> list) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Type listType = new TypeToken<List<Employee>>() {
        }.getType();
        String json = gson.toJson(list, listType);
        return json;
    }

    public static void writeString(String json) {
        try (FileWriter file = new FileWriter("new_data.json")) {
            file.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}