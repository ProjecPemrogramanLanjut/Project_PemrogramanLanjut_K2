/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekbesar;

public class Session {
    private static String username;
    private static String id;
    private static String level;
    
    public static String get_username(){
        return username;
    }
    public static void set_username(String username){
        Session.username = username;
    }
    public static String get_id(){
        return id;
    }
    public static void set_id(String id){
        Session.id = id;
    }
    public static String get_level(){
        return level;
    }
    public static void set_level(String level){
        Session.level = level;
    }
}
