
import java.time.LocalDate;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joann
 */
public class ToDo {
  private String strToDo = "";
  private ArrayList<ToDo> toDoList;
  private String  toDoDate =  "";
  private String strDesc="";
  
  public String Name(){    
    return strToDo;
}
  public String Date(){
      return toDoDate;
  }
  
  public String Description(){    
    return strDesc;
}
  public void setList(ArrayList<ToDo> td){
     toDoList = td; 
  }
  public String getName(){
      return strToDo;
  }
  public void addToDo(){
      if(toDoList==null){
          toDoList = new ArrayList<ToDo>();
      }
      toDoList.add(this);
  }
  public ToDo(String todo, String tdDate, String desc){
      strToDo=todo;
      toDoDate = tdDate;
      strDesc = desc;
  }
  public ArrayList<ToDo> getToDoList() {
      return toDoList;
  }
  public void DeleteToDo(){
      toDoList.remove(this);
  }
  public String toString(){
      return this.Date() + " | " + this.Name() + " | " + this.Description() ;
  }
      public ToDo fromString(String string)
    {
        ToDo td = null;
 
        if (string == null)
        {
            return td;
        }
 
        int commaIndex = string.indexOf(" | ");
 
        if (commaIndex == -1)
        {
            td = new ToDo(string, null,null);
        }
        else
        {
            String tdDate = string.substring(commaIndex );
            String Name = string.substring(0, commaIndex);
            commaIndex = tdDate.indexOf(" | ");            
            String tdDesc = string.substring(commaIndex );
            td = new ToDo(Name, tdDate, tdDesc);
        }
 
        return td;
    }
}
