
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
  private String name = "";
  private ArrayList<ToDo> toDoList;
  private String  dueDate =  "";
  private String description="";
  private int id;  
 
  public ToDo(){
  }
  
  public ToDo(String todo,  String strdesc){
      name=todo;
      //dueDate = ;
      description = strdesc;
  }
public int getId() {  
    return id;  
}  
public void setId(int id) {  
    this.id = id;  
}  
public String getName(){    
    return name;
}
  public String getDate(){
      return dueDate;
  }
  
  public String getDescription(){    
    return description;
} 
public void setName(String strname){    
    name = strname;
}
  public void setDate(String strdate){
      dueDate = strdate;
  }
  
  public void setDescription(String strdesc){    
    description = strdesc;
}
//  public String Name(){    
//    return strToDo;
//}
//  public String Date(){
//      return toDoDate;
//  }
//  
//  public String Description(){    
//    return strDesc;
//}
  public void setList(ArrayList<ToDo> td){
     toDoList = td; 
  }

  public void addToDo(){
      if(toDoList==null){
          toDoList = new ArrayList<ToDo>();
      }
      toDoList.add(this);
  }
  public ArrayList<ToDo> getToDoList() {
      return toDoList;
  }
  public void DeleteToDo(){
      toDoList.remove(this);
  }
  public String toString(){
      return this.getDate() + " | " + this.getName() + " | " + this.getDescription() ;
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
