
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joann
 */
public class Validate {
    
    
    public Boolean isValidDate(String strDate){
        DateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
        
        try{
          sdf.parse(strDate);
          return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public String checkValid(String strDate){
       String msg = "";
       if(!isValidDate(strDate)){
            msg = "Date is not in a valid format (mm/dd/yyyy)";
       }
       return msg;
    }
    public String checkLength(String str){
        String msg="";
        try{
            if(str ==null){
               msg = "No value was entered.";
            }
            else{
                if(str.trim().length() ==0){
                     msg = "No value was entered.";
                }
            }
        }
        catch(Exception e){
             msg = "No value was entered.";
        }
        return msg;
    }
}
