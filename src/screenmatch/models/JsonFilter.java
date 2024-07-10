package screenmatch.models;

import com.google.gson.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class JsonFilter {
    private String apiKey = "&apikey=544d84b6";
    private Omdb searchResult;
    private List<String> ratings = new ArrayList<>();
    private String totalSeasons;
    private boolean response;

    public JsonFilter(String search) throws IOException, InterruptedException {
        String address = "https://www.omdbapi.com/?t=" + search + apiKey;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(address))
                .build();
        HttpResponse<String> response =client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.body());
        String json = response.body();
        JsonObject jObject = JsonParser.parseString(json).getAsJsonObject();
        String searchResponse = jObject.get("Response").getAsString();
        if (searchResponse.equalsIgnoreCase("true")) {
            //Extração da array "Ratings" do JSON
            JsonArray ratingsArray = jObject.getAsJsonArray("Ratings");
            for (int i = 0; i < ratingsArray.size(); i++) {
                JsonObject ratingsObject = ratingsArray.get(i).getAsJsonObject();
                String source = ratingsObject.get("Source").getAsString();
                String value = ratingsObject.get("Value").getAsString();
                ratings.add("Fonte: " + source + " - Nota: " + value + " ");
            }
            //Builder do JSON
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .setPrettyPrinting()
                    .create();
            searchResult = gson.fromJson(json, Omdb.class);
            //Tratamento do JSON, porque ele veio com uma das keys com letra minuscula e o resto com maiuscula
            if (searchResult.type().equalsIgnoreCase("series")) {
                this.totalSeasons = jObject.get("totalSeasons").getAsString();
            }
            this.response = true;
        } else {
            this.response = false;
        }
    }
    public String getType() {
        return searchResult.type();
    }

    public Omdb getSearchResult() {
        return searchResult;
    }

    public List<String> getRatings() {
        return ratings;
    }

    public String getTotalSeasons() {
        return totalSeasons;
    }
    public boolean getResponse() {
        return this.response;
    }
}
