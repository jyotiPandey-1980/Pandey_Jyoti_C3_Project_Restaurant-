import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    
	@BeforeEach 
    public void setUpResturant() {
    	 LocalTime openingTime = LocalTime.parse("10:30:00");
         LocalTime closingTime = LocalTime.parse("22:00:00");
         restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
         restaurant.addToMenu("Sweet corn soup",119);
         restaurant.addToMenu("Vegetable lasagne", 269);
    	}
		
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
     @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
    	 LocalTime openingTime1 = LocalTime.now().minus( 1 , ChronoUnit.HOURS );
         LocalTime closingTime1 = LocalTime.now().plus( 1 , ChronoUnit.HOURS );
         Restaurant restaurant1 =new Restaurant("Amelie's cafe","Chennai",openingTime1,closingTime1);
         
         assertEquals(true,restaurant1.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
    	 LocalTime openingTime1 = LocalTime.now().plus( 1 , ChronoUnit.HOURS );
         LocalTime closingTime1 = LocalTime.now().plus( 4 , ChronoUnit.HOURS );
         Restaurant restaurant1 =new Restaurant("Amelie's cafe","Mumbai",openingTime1,closingTime1);
         
         assertEquals(false,restaurant1.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
	
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
	
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
       
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>Total calculated Price<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_totalAmount_withSelectedItem_price(){

        restaurant.selectItemByName("Sweet corn soup");
       
        assertEquals(119,restaurant.calculateAmount());
        
        restaurant.selectItemByName("Vegetable lasagne");
       
        assertEquals(388,restaurant.calculateAmount());
    }
  
    @Test
    public void removing_item_to_menu_havingTwoItems_should_decrease_totalAmount_withUnSelectedItem_price(){
    	
    	  restaurant.selectItemByName("Sweet corn soup");
    	  restaurant.selectItemByName("Vegetable lasagne");
       
    	  restaurant.UnSelectItemByName("Sweet corn soup");
     
    	  assertEquals(269,restaurant.calculateAmount());
        
    }
    
    @Test
    public void removing_item_to_menu_havingOneItem_should_decrease_totalAmount_withUnSelectedItem_price(){
    	
    	  restaurant.selectItemByName("Sweet corn soup");
    	
    	  restaurant.UnSelectItemByName("Sweet corn soup");
     
    	  assertEquals(0,restaurant.calculateAmount());
    }
  //>>>>>>>>>>>>>>>>>>>>>>>>>>>Total calculated Price<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
  
}