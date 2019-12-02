package com.ayubkaoukaou.webscrapingproject.mavenproject1;

/**
 *
 * @author kaouk
 */
public interface CheeseTypes {
    
    /** List of cheese types. */
    final String[] CHEESE_TYPES = {"cheddar", "stilton", "manchego", "mozzarella", "chevre", "ricotta", "brie", "camembert", "cambozola", "parmigiano"};
    
    /** Get the cheese type.
     * @param description
     * @return  */
    public static String getType(String description){
        for(String type : CHEESE_TYPES){
            if(description.toLowerCase().contains(type))
                return type;
        }
        return "undefined";
    }
     /**Get the cheese ID. */
    public static int getTypeId(String type){
        for(int i=0; i<CHEESE_TYPES.length; ++i){
            if(type == CHEESE_TYPES[i]);
                return i+1;
        }
        return -1;//MAKE SURE THAT THIS MATCHES INDEX IN DATABASE
    }
    
    
}
