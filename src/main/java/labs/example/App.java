package labs.example;

import java.io.*;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App
{
    public static void main( String[] args ) throws IOException {
        String[] objects = getMangaObjects();

        Pattern pattern = Pattern.compile("\"(\\w+)\":\\s*(\\[[^\\]]*\\]|\"[^\"]*\"|\\d+|true|false)");
        List<Map<String, String>> listMap = new ArrayList<>();
        for(int i = 0; i < objects.length; i++){
            Map<String, String> hashMap = getMangaHashMap(pattern, objects[i]);
            listMap.add(hashMap);
        }

        listMap.forEach(item -> {
            System.out.println(App.convertListMapToObject(item));
        });
    }

    private static Map<String, String> getMangaHashMap(Pattern pattern, String objects) {
        Map<String, String> hashMap = new HashMap<>();

        Matcher matcher = pattern.matcher(objects);

        while (matcher.find()) {
            String chave = matcher.group(1); // Nome do campo
            String valor = matcher.group(2); // Valor do campo

            // Se for "genres", transformar lista em string única
            if (chave.equals("genres")) {
                valor = valor.replace("[", "").replace("]", "").replace("\"", "").replace(" ", "");
            }

            if(chave.equals("thumb")){
                valor = valor.replace("\\u0026", "&");
            }

            hashMap.put(chave, valor);
        }
        return hashMap;
    }

    private static String[] getMangaObjects() throws IOException {
        URL accessToken = new URL("https://mangaverse-api.p.rapidapi.com/manga/fetch?page=1");
        HttpURLConnection access = (HttpURLConnection) accessToken.openConnection();

        access.setRequestMethod("GET");
        access.setRequestProperty("x-rapidapi-host", "mangaverse-api.p.rapidapi.com");
        access.setRequestProperty("x-rapidapi-key", "90b8387a0bmshaf009bcb8dab315p1e2442jsnb5022b570294");
        access.setDoOutput(true);

        BufferedReader buff = new BufferedReader(new InputStreamReader(access.getInputStream()));

        String json = buff.readLine();

        String sub = json.substring(json.indexOf("[")+1, json.lastIndexOf("]"));

        buff.close();

        String[] objects = sub.split("\\}\\,");

        for(int i = 0; i < objects.length; i++){
            objects[i] = objects[i].replace("{","");
        }

        return objects;
    }

    public static MangaSummary convertListMapToObject(Map<String, String> map){
        MangaSummary mangaSummary = new MangaSummary();

        Map<Class<?>, Consumer<Field>> optionsRuns = new HashMap<>();
        optionsRuns.put(Long.class, (field) -> {
            String value = map.get(field.getName());
            try {
                if(value != null){
                    if(value.isBlank()){
                        field.set(mangaSummary, null);
                    } else {
                        field.set(mangaSummary, Long.parseLong(map.get(field.getName())));
                    }
                } else {
                    field.set(mangaSummary, null);
                }

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        optionsRuns.put(Boolean.class, (field) -> {
            String value = map.get(field.getName());
            try {
                if(value != null){
                    if(map.get(field.getName()).isBlank()){
                        field.set(mangaSummary, null);
                    } else {
                        field.set(mangaSummary, Boolean.parseBoolean(map.get(field.getName())));
                    }
                } else {
                    field.set(mangaSummary, null);
                }

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        optionsRuns.put(List.class, (field) -> {
            String value = map.get(field.getName());
            List<String> valueList = Arrays.stream(value.split(", ")).toList();
            try{
                if(value != null){
                    boolean validation = valueList.size() == 1;
                    boolean validation2 = (valueList.get(0) == null || valueList.get(0).isBlank());
                    if(validation){
                        if(validation2){
                            field.set(mangaSummary, null);
                        } else {
                            field.set(mangaSummary, valueList);
                        }
                    } else {
                        field.set(mangaSummary, null);
                    }
                } else {
                    field.set(mangaSummary, null);
                }

            } catch(IllegalAccessException e){
                throw new RuntimeException(e);
            }
        });
        optionsRuns.put(String.class, (field) -> {
            try {
                field.set(mangaSummary, map.get(field.getName()));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        Arrays.stream(mangaSummary.getClass().getDeclaredFields()).toList().forEach(field -> {
            field.setAccessible(true);
            optionsRuns.get(field.getType()).accept(field);
        });

        return mangaSummary;
    }


}
