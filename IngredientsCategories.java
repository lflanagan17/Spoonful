import java.util.ArrayList;

/**
 * Class containing all the ingredients grouped into categories
 * @author fremont
 *
 */

public class IngredientsCategories {
	
	private ArrayList<String> fruits;
	private ArrayList<String> vegetables;
	private ArrayList<String> grains;
	private ArrayList<String> proteinsWithoutMeat;
	private ArrayList<String> meat;
	private ArrayList<String> dairy;
	private ArrayList<String> misc;

	 
	/**
	 * Initialize the categories and store them into lists
	 */
	public IngredientsCategories() {
		this.fruits = new ArrayList<String>();
		this.vegetables = new ArrayList<String>();
		this.grains = new ArrayList<String>();
		this.proteinsWithoutMeat = new ArrayList<String>();
		this.meat = new ArrayList<String>();
		this.dairy =  new ArrayList<String>();
		this.misc =  new ArrayList<String>();
		
		addFruits();
		addVegetables();
		addGrains();
		addProteinsWithoutMeat();
		addMeats();
		addDairy();
		addMisc();
					
	}
	
	/**
	 * Add all the fruits to the category
	 */
	public void addFruits() {
		fruits.add("Bananas");
		fruits.add("Lemon");
		fruits.add("Blueberries");
		fruits.add("Raisins");
		fruits.add("Apples");
		fruits.add("Avocados");
		fruits.add("Lime");
		fruits.add("Whole Peeled Tomatoes");
		fruits.add("Golden Raisins");
		fruits.add("Dried Cranberries");
		
		
	}
	
	/**
	 * Add all the vegetables to the category
	 */
	public void addVegetables() {
		vegetables.add("Onion");
		vegetables.add("Ginger");
		vegetables.add("Broccoli");
		vegetables.add("Cloves Garlic");
		vegetables.add("Russet Potatoes");
		vegetables.add("Spinach");
		vegetables.add("Chives");
		vegetables.add("Zucchini");
		vegetables.add("Shallot");
		vegetables.add("Cilantro");
		vegetables.add("Green Onion");
		vegetables.add("Pumpkin Puree");
		vegetables.add("Cauliflower");
		vegetables.add("Mashed Potato Flakes");
		vegetables.add("Carrots");
		vegetables.add("Potatoes");
		vegetables.add("Onion Powder");
		vegetables.add("Diced Tomatoes");
		vegetables.add("Tomato Paste");
		vegetables.add("Parsley");
		vegetables.add("Celery");
		vegetables.add("Green Peas");
		vegetables.add("Cabbage");
		vegetables.add("Garlic");
		vegetables.add("Green Bell Pepper");
		vegetables.add("Red Bell Pepper");
		vegetables.add("Green Chile");
		vegetables.add("Green Chilies");
		vegetables.add("Mushrooms");
		vegetables.add("Frozen Corn");
		vegetables.add("Jalapeno Peppers");
		vegetables.add("Plum Tomatoes");
		vegetables.add("Brussels Sprouts");
		vegetables.add("Eggplant");
		
	}
	
	/**
	 * Add all the grains in the category
	 */
	public void addGrains() {
		grains.add("Biscuit Dough");
		grains.add("Saltine Craker");
		grains.add("Flour");
		grains.add("Rotini Pasta");
		grains.add("Bread Crumbs");
		grains.add("Lasagna Noodles");
		grains.add("Cornstarch");
		grains.add("Whole Kernel Corn");
		grains.add("Corn Chips");
		grains.add("Tortilla Chips");
		grains.add("Cornmeal");
		grains.add("Ziti Pasta");
		grains.add("Cooking Oats");
		grains.add("Active Dry Yeast");
		grains.add("Chicken-Flavored Crackers");
		grains.add("Corn Tortillas");
		grains.add("White Rice");
		grains.add("Corn Kernels");
		grains.add("Flour Tortillas");
		grains.add("Hamburger Buns");
		grains.add("Rolled Oats");
		grains.add("Bread");
		grains.add("Angel Hair Pasta");
		grains.add("Bow Tie Pasta");
		grains.add("Graham Cracker Crust");
		grains.add("Dry Corn Bread Mix");
		grains.add("Elbow Macaroni");
		
	}
	
	/**
	 * Add all the protein without meats in the category
	 */
	public void addProteinsWithoutMeat() {
		proteinsWithoutMeat.add("Eggs");
		proteinsWithoutMeat.add("Walnut");
		proteinsWithoutMeat.add("Chili Beans");
		proteinsWithoutMeat.add("Chili Beans in Spicy Sauce");
		proteinsWithoutMeat.add("Black Beans");
		proteinsWithoutMeat.add("Almonds");
	}
	
	/**
	 * Add all the meats in the category
	 */
	public void addMeats() {
		meat.add("Chicken Breast");
		meat.add("Pot Roast");
		meat.add("Ground Beef");
		meat.add("Cod Fillets");
		meat.add("Dungeness Crabs");
		meat.add("Bacon");
		meat.add("Lamb Shoulder");
		meat.add("Tilapia Fillets");
		meat.add("Italian Sausage");
		meat.add("Ham");
		meat.add("Chicken Bouillon Granules");
		meat.add("Chicken Thighs");
		meat.add("Bacon Bits");
		meat.add("Beef Bouillon");
		meat.add("Pork Chops");
		meat.add("Salmon Fillets");
		meat.add("Chunk Chicken");
		meat.add("Cooked Chicken");
		meat.add("Pork Tenderloin");
		meat.add("Salmon");
		meat.add("Beef Stew Meat");
		meat.add("Whole Chickens");
		meat.add("Chicken Wings");
		meat.add("Pork Ribs");
		
		
	}
	
	/**
	 * Add all the dairy ingredients in the category
	 */
	public void addDairy() {
		dairy.add("Butter");
		dairy.add("Milk");
		dairy.add("Skim Milk");
		dairy.add("Cheddar Cheese");
		dairy.add("Cream Cheese");
		dairy.add("Parmesan Cheese");
		dairy.add("Ricotta Cheese");
		dairy.add("Mozzarella Cheese");
		dairy.add("Sour Cream");
		dairy.add("ButterMilk");
		dairy.add("Half-and-Half Cream");
		dairy.add("Swiss Cheese");
		dairy.add("Heavy Whipping Cream");
		dairy.add("Provolone Cheese");
		dairy.add("Heavy Cream");
		dairy.add("Curd Cottage Cheese");
	}
	
	/**
	 * Add all the misc. ingredients in the category
	 */
	public void addMisc() {
		misc.add("Cream of Chicken Soup");
		misc.add("Cream of Mushroom Soup");
		misc.add("Dry Onion Soup");
		misc.add("Brown Sugar");
		misc.add("Ketchup");
		misc.add("Salt");
		misc.add("Black Pepper");
		misc.add("Vanilla Extract");
		misc.add("Baking Soda");
		misc.add("Choclate Chips");
		misc.add("Nutmeg");
		misc.add("Cayenne Pepper");
		misc.add("Olive Oil");
		misc.add("Baking Powder");
		misc.add("Cinnamon");
		misc.add("Vegetable Oil");
		misc.add("Chicken Broth");
		misc.add("Soy Sauce");
		misc.add("Chile-Garlic Sauce");
		misc.add("Oyster Sauce");
		misc.add("Tamarind Paste");
		misc.add("Fish Sauce");
		misc.add("Palm Sugar");
		misc.add("Serrano Pepper");
		misc.add("Cloves");
		misc.add("Margarine");
		misc.add("Beef Stock");
		misc.add("Thyme");
		misc.add("Bay Leaves");
		misc.add("Bay Leaf");
		misc.add("White Wine");
		misc.add("Mayonnaise");
		misc.add("Basil");
		misc.add("Celery Salt");
		misc.add("Cooking spray"); 
		misc.add("Instant Vanilla Pudding Mix");
		misc.add("Tomato Sauce");
		misc.add("Fennel Seeds");
		misc.add("Italian Seasoning");
		misc.add("Cocoa Powder");
		misc.add("Honey");
		misc.add("Confesctioners Sugar");
		misc.add("Caraway Seeds");
		misc.add("Celery Seed");
		misc.add("Unbaked Pie Crusts");
		misc.add("White Vinegar");
		misc.add("Double Crust Pie");
		misc.add("Barbeque Sauce");
		misc.add("Italian Salad Dressing");
		misc.add("Worcestershire Sauce");
		misc.add("Paprika");
		misc.add("Cider Vinegar");
		misc.add("Beer");
		misc.add("Chili Powder");
		misc.add("Oregano");
		misc.add("Cumin");
		misc.add("Hot Pepper Sauce");
		misc.add("Garlic Powder");
		misc.add("Taco Seasoning");
		misc.add("Spahgetti Sauce");
		misc.add("Molasses");
		misc.add("Red Pepper Flakes");
		misc.add("Yellow Mustard");
		misc.add("Devils Food Cake Mix");
		misc.add("Instant Chocolate Pudding Mix");
		misc.add("Lemon Pepper");
		misc.add("Ranch Dressing");
		misc.add("Pepper Sauce");
		misc.add("Salsa");
		misc.add("Marsala Wine");
		misc.add("Cooking Cherry");
		misc.add("Enchilada Sauce");
		misc.add("Quinoa");
		misc.add("Vegetable Broth");
		misc.add("Prepared Mustard");
		misc.add("Root Beer");
		misc.add("Barbecue Sauce");
		misc.add("Maple Syrup");
		misc.add("Beef Broth");
		misc.add("Condensed Golden Mushroom Soup");
		misc.add("French Onion Soup");
		misc.add("Mustard Powder");
		misc.add("Garlic Salt");
		misc.add("Balsamic Vinegar");
		misc.add("Rosemary");
		misc.add("Sesame Seeds");
		misc.add("Poppy Seeds");
		misc.add("White Wine Vinegar");
		misc.add("Italian-style salad");
		misc.add("Hot Sauce");
		misc.add("Corn Syrup");
		misc.add("Almond Extract");
		misc.add("Food Coloring");
		misc.add("Whipped Topping");
		misc.add("Creamed Corn");
		misc.add("Curry Powder");
		misc.add("Coconut Milk");
		misc.add("Sugar");
		misc.add("Water"); 
		
	}
	
	public ArrayList<String> getFruits() {
		return fruits;
	}

	public void setFruits(ArrayList<String> fruits) {
		this.fruits = fruits;
	}

	public ArrayList<String> getVegetables() {
		return vegetables;
	}

	public void setVegetables(ArrayList<String> vegetables) {
		this.vegetables = vegetables;
	}

	public ArrayList<String> getGrains() {
		return grains;
	}

	public void setGrains(ArrayList<String> grains) {
		this.grains = grains;
	}

	public ArrayList<String> getProteinsWithoutMeat() {
		return proteinsWithoutMeat;
	}

	public void setProteinsWithoutMeat(ArrayList<String> proteinsWithoutMeat) {
		this.proteinsWithoutMeat = proteinsWithoutMeat;
	}

	public ArrayList<String> getMeat() {
		return meat;
	}

	public void setMeat(ArrayList<String> meat) {
		this.meat = meat;
	}

	public ArrayList<String> getDairy() {
		return dairy;
	}

	public void setDairy(ArrayList<String> dairy) {
		this.dairy = dairy;
	}

	public ArrayList<String> getMisc() {
		return misc;
	}

	public void setMisc(ArrayList<String> misc) {
		this.misc = misc;
	}
	
	/**
	 * Get the category of an input ingredient
	 * @param ingredient - name of ingredient
	 * @return which category the ingredient is in
	 */
	public String getCategory(String ingredient) {
		if(fruits.indexOf(ingredient)!=-1) {
			return "fruit";  
		}
		if(vegetables.indexOf(ingredient)!=-1) {
			return "vegetables"; 
		}
		if(grains.indexOf(ingredient)!=-1) {
			return "grains"; 
		}
		if(proteinsWithoutMeat.indexOf(ingredient)!=-1) { 
			return "proteins without meat"; 
		}
		if(meat.indexOf(ingredient)!=-1) {
			return "meat"; 
		}
		if(dairy.indexOf(ingredient)!=-1) {
			return "dairy"; 
		}
		else {
			return "misc"; 
		}
	}

	
	
}