package GoogleAPI;

/*
 * Class "Translator" handling all about word's Vietnamese meanings using API...
 * @Author: Meoki
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;

public class Translator {

    //Call URL request and get response
    public static String callUrlAndParseResult(String langFrom, String langTo, String word) throws Exception {
        String url = "https://translate.googleapis.com/translate_a/single?" + "client=gtx&" + "sl=" + langFrom + "&tl=" + langTo + "&dt=t&q=" + URLEncoder.encode(word, "UTF-8");
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf8"));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return parseResult(response.toString());
    }

    //Handling JSON response and get Vietnamese meaning
    private static String parseResult(String inputJson) {
        JSONArray jsonArray = new JSONArray(inputJson);
        JSONArray jsonArray2 = (JSONArray) jsonArray.get(0);
        JSONArray jsonArray3 = (JSONArray) jsonArray2.get(0);
        return jsonArray3.get(0).toString();
    }
}