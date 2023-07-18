package Creational.Singleton;

public class Database {
    public static Database database;

    private Database(){

    }

    public static Database getDatabase(){
        if(database==null) database = new Database();
        return  database;
    }
}
