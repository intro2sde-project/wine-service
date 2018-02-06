package unitn.sde.project.adapters;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import unitn.sde.project.model.Wine;
import unitn.sde.project.model.WineReview;

public class SnoothAdapter {
	
	/*
	 * Get the snooth response and return a WIne object
	 */
	public static Wine simplifyResponse(JSONObject jObj) {
		
    	Wine wine = null;
    	String name = "Unknown"; 
    	String code = "Unknown";
    	String region = "Unknown";
    	String winery = "Unknown";
    	String varietal = "Unknown";
    	double price = 0;
    	int vintage = 0;
    	String type = "Unknown";
    	String link = "Unknown";
    	String image = "Unknown";
    	
    	try {
    		name = jObj.getString("name");
    	} catch(Exception e) {
    		System.out.println("Something's wrong wiht 'name': " + e);
    	}
    	
    	try {
    		code = jObj.getString("code");
    	} catch(Exception e) {
    		System.out.println("Something's wrong with 'code': " + e);
    	}
    	
    	try {
    		region =  jObj.getString("region");
    	} catch(Exception e) {
    		System.out.println("Something's wrong with 'region': " + e);
    	}
    	
    	try {
    		winery =  jObj.getString("winery");
    	} catch(Exception e) {
    		System.out.println("Something's wrong with 'winery': " + e);
    	}
    	
    	try {
    		varietal =  jObj.getString("varietal");
    	} catch(Exception e) {
    		System.out.println("Something's wrong with 'varietal': " + e);
    	}
    	
    	try {
    		price =  jObj.getDouble("price");
    	} catch(Exception e) {
    		System.out.println("Something's wrong with 'price': " + e);
    	}
    	
    	
    	try {
    		vintage =   jObj.getInt("vintage");
    	} catch(Exception e) {
    		System.out.println("Something's wrong with 'wintage': " + e);
    	}
    	
    	try {
    		type = jObj.getString("type");
    	} catch(Exception e) {
    		System.out.println("Something's wrong with 'type': " + e);
    	}
    	
    	try {
    		link = jObj.getString("link");
    	} catch(Exception e) {
    		System.out.println("Something's wrong with 'link': " + e);
    	}
    	
    	try {
    		image = jObj.getString("image");
    	} catch(Exception e) {
    		System.out.println("Something's wrong with 'image': " + e);
    	}
    	
    	try {
    		wine = new Wine(
    				name,
        			code,
        			region,
        			winery,
        			varietal,
        			price ,
        			vintage,
        			type,
        			link,
        			image 		
        		);
    	} catch (Exception e) {
    		System.out.print("error:" + e.toString());
    	}
    	return  wine;
    }

	public static Wine simplifyResponseWithMoreDetails(JSONObject jObj, String name) {
		Wine wine = simplifyResponse(jObj);
		
		String alcohol = "unknown";
		List<WineReview>  reviews = new ArrayList<>();
    	
    	try {
    		JSONArray jArr = jObj.getJSONArray("reviews");
    		for(int i = 0; i < jArr.length(); i++) {
    			WineReview wr = new WineReview();
    			wr.setBody(((JSONObject)jArr.get(i)).getString("body"));
    			wr.setDate(((JSONObject)jArr.get(i)).getLong("date"));
    			wr.setRating(((JSONObject)jArr.get(i)).getString("rating"));
    			wr.setLang(((JSONObject)jArr.get(i)).getString("lang"));
    			wr.setName(((JSONObject)jArr.get(i)).getString("name"));
    			reviews.add(wr);
    			
    			if(((JSONObject)jArr.get(i)).getString("name").equals(name)) {
    				wine.setRating(Double.parseDouble(((JSONObject)jArr.get(i)).getString("rating")));
    				wine.setReview(((JSONObject)jArr.get(i)).getString("body"));
    			}
    		}
    		wine.setReviews(reviews);
    		
    	} catch(Exception e) {
    		System.out.println("Something's wrong with 'review': " + e);
    	}
    	
    	try {
    		alcohol =  jObj.getString("alcohol");
    		wine.setAlcohol(alcohol);
    	} catch(Exception e) {
    		System.out.println("Something's wrong with 'alcohol': " + e);
    	}

		
    	return wine;
	}
	
	public static Wine simplifyResponseWithinWishlist(JSONObject jObj) {

		Wine wine = simplifyResponse(jObj);
		String review = "unknown";
		double rating = 0.0;
		
		try {
    		review = jObj.getString("my-review");
    		wine.setReview(review);
    	} catch(Exception e) {
    		System.out.println("Something's wrong with 'link': " + e);
    	}
    	
    	try {
    		rating = jObj.getDouble("my-rating");
    		wine.setRating(rating);
    	} catch(Exception e) {
    		System.out.println("Something's wrong with 'image': " + e);
    	}
		
    	return wine;
	}
}
