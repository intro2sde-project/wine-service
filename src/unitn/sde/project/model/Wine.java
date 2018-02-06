package unitn.sde.project.model;


import java.util.Map;



import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="wine")
@XmlAccessorType(XmlAccessType.FIELD)

public class Wine {
	
	/* 
	 * Wine's name.
	 * example: "Three Coins Cabernet Sauvignon"
	 */
	private String  name;
	
	/*
	 * Wine's code.
	 * example: "three-coins-cabernet-sauvignon-2009-1"
	 */
	private String code; 
	
	/*
	 * Wine's region. From country to region.
	 * example: "USA > California > Napa"	
	 */
	private String region;
	
	/*
	 * Winery.
	 * "Three Coins Wines"
	 */
	private String winery;
	
	/*
	 * Wine's variental.
	 * examples: "Cabernet Sauvignon", "chianti"
	 */
	private String varietal;
		
	/*
	 * Wine's price.
	 * example: "30.00"
	 */
	private double price;
	
	/*
	 * Wine's vintage.
	 * example: "2009"
	 */
	private int vintage;
	
	/*
	 * Wine's type.
	 * examples: "Red Wine", "Rosè"
	 */
	private String type;
	
	/*
	 * Wine's link to the corresponding snooth's page
	 * example: "http://www.snooth.com/wine/three-coins-cabernet-sauvignon-2009-1/"
	 */
	private String link;
	
	/*
	 * Wine's image link.
	 * example: "http:\/\/ei.isnooth.com\/multimedia\/9\/3\/4\/image_1779058_square.jpeg"
	 */
	private String image;
	
	/*
	 * own rating
	 * example: 3.5
	 */
	private double rating;
	
	/*
	 * My review of the wine
	 * example: "I like it, I got the first hangover with this wine"
	 */
	private String review;
	
	/*
	 * The percentage of alcohol for the wine
	 * example:"13.5"
	 */
	private String alcohol;
	
	
	/*
	 * List with all reviews from all users
	 * example: list of review
	 */
	private List<WineReview> reviews; 
	
	/*CONSTRUCTOR*/
	public Wine() {
		
	}
	
	public Wine(String name, String code, String region, String winery, String varietal, double price, int vintage,
			String type, String link, String image) {
		
		this.name = name;
		this.code = code;
		this.region = region;
		this.winery = winery;
		this.varietal = varietal;
		this.price = price;
		this.vintage = vintage;
		this.type = type;
		this.link = link;
		this.image = image;
	}

	public Wine(String name, String code, String region, String winery, String varietal, double price, int vintage,
			String type, String link, String image, double rating, String review, String alcohol, 
			List<WineReview> reviews) {
		
		this.name = name;
		this.code = code;
		this.region = region;
		this.winery = winery;
		this.varietal = varietal;
		this.price = price;
		this.vintage = vintage;
		this.type = type;
		this.link = link;
		this.image = image;
		this.rating = rating;
		this.review = review;
		this.alcohol = alcohol;
		this.reviews = reviews;
	}
	
	/*GETTER AND SETTER*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getWinery() {
		return winery;
	}

	public void setWinery(String winery) {
		this.winery = winery;
	}

	public String getVarietal() {
		return varietal;
	}

	public void setVarietal(String varietal) {
		this.varietal = varietal;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getVintage() {
		return vintage;
	}

	public void setVintage(int vintage) {
		this.vintage = vintage;

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(String alcohol) {
		this.alcohol = alcohol;
	}

	public List<WineReview> getReviews() {
		return reviews;
	}

	public void setReviews(List<WineReview> reviews) {
		this.reviews = reviews;
	}
	
	
}
