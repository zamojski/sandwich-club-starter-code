package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject rootObject = new JSONObject(json);
            JSONObject nameObject = rootObject.getJSONObject("name");
            String mainName = nameObject.getString("mainName");
            List<String> alsoKnownAs = toStringList(nameObject.getJSONArray("alsoKnownAs"));
            String placeOfOrigin = rootObject.getString("placeOfOrigin");
            String description = rootObject.getString("description");
            String image = rootObject.getString("image");
            List<String> ingredients = toStringList(rootObject.getJSONArray("ingredients"));
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
            list.add(array.getString(i));
        }
        return list;
    }
}
