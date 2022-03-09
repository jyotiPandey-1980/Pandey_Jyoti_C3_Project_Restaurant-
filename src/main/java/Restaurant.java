import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
     private List<Item> menu = new ArrayList<Item>();
    public CopyOnWriteArrayList<Item> selectedMenu = new CopyOnWriteArrayList<Item>();
    private int totalAmount;

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
    	if(getCurrentTime().isAfter(openingTime) && getCurrentTime().isBefore(closingTime)) {
    		return true;
    	}else {
    		return false;
    	}
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    //Function to Select Item
    public List<Item> selectItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
            	selectedMenu.add(item);
        }
        
       
        return selectedMenu;
    }
    
    //Function to UnSelect Item
    public List<Item> UnSelectItemByName(String itemName){
    	
        for(Item item: selectedMenu) {
            if(item.getName().equals(itemName))
            	selectedMenu.remove(item);
        }
        	return selectedMenu;
       
    }
    
    //calculate totalAmount
    public int calculateAmount(){
    	totalAmount = 0;
    	if(selectedMenu.size() !=0) {
        for(Item item: selectedMenu) {
        	 System.out.println(" Item Price :"+item.getPrice());
        	totalAmount += item.getPrice();
        	 System.out.println("Selected Item :"+totalAmount);
        }
    	}
        return totalAmount;
    }
   
}
