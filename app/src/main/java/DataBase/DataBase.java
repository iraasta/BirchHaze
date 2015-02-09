package DataBase;

/**
 * Created by maciejgalos on 28.01.15.
 */
public class DataBase {
    private static volatile DataBase instance =null;
    public static DataBase getInstance(){
        if(instance == null){
            synchronized (DataBase.class){
                instance = new DataBase();
            }
        }
        return  instance;
    }
    private DataBase(){
        currentCity = new City();
        JSONString = null;
    }

    private String JSONString;
    public void setJSONString(String s){
        JSONString = s;
    }
    public String getJSONString(){
        return JSONString;
    }

    private City currentCity;
    public City setCurrentCity(City city){
        this.currentCity = city;
        return city;
    }
    public City getCity(){
        return currentCity;
    }



}
