package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String JSON_NAME = "name";
    private static final String JSON_MAIN_NAME = "mainName";
    private static final String JSON_ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String JSON_PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String JSON_DESCRIPTION = "description";
    private static final String JSON_IMAGE = "image";
    private static final String JSON_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject rootObject = new JSONObject(json);
            JSONObject nameObject = rootObject.getJSONObject(JSON_NAME);
            String mainName = nameObject.optString(JSON_MAIN_NAME);
            List<String> alsoKnownAs = toStringList(nameObject.getJSONArray(JSON_ALSO_KNOWN_AS));
            String placeOfOrigin = rootObject.optString(JSON_PLACE_OF_ORIGIN);
            String description = rootObject.optString(JSON_DESCRIPTION);
            String image = rootObject.optString(JSON_IMAGE);
            List<String> ingredients = toStringList(rootObject.getJSONArray(JSON_INGREDIENTS));
            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        } catch (JSONException e) {
            e.printStackTrace();
            return new Sandwich();
        }
    }

    private static List<String> toStringList(JSONArray array) throws JSONException {
        if (array == null)
            return null;

        List<String> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(array.optString(i));
        }
        return list;
    }
}
