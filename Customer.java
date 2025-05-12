/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;

/**
 *
 * @author ahmed
 */
import java.util.ArrayList;
import java.util.List;

public class Customer {
     private int customerID;
    private String username;
    private String password;
    private ReadOnlyUser ROU;
    private List<CustomerInstance> instances = new ArrayList<>();

    public Customer(int customerID, String username, String password) {
        this.customerID = customerID;
        this.username = username;
        this.password = password;
    }
        public Customer(int customerID, String username, String password, ReadOnlyUser ROU) {
        this.customerID = customerID;
        this.username = username;
        this.password = password;
        this.ROU = ROU;
    }

    public ReadOnlyUser getROU() {
        return ROU;
    }

    public void setROU(ReadOnlyUser ROU) {
        this.ROU = ROU;
    }
        

   public void addInstance(CustomerInstance instance) {
       instances.add(instance);
  }

   public List<CustomerInstance> getInstances() {
      return instances;
  }

    public int getCustomerID() {
        return customerID;
    }

    public String getUsername() {
        return username;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setInstances(List<CustomerInstance> instances) {
       this.instances = instances;
   }



    public String getPassword() {
        return password;
    }
      public int getUserID() {
            return ROU.getUserID();
        }

    
    public String getName() {

            return ROU.getName();
        }

    
    public String getEmail() {

            return ROU.getEmail();
        }

    public String getLocation() {
            return ROU.getLocation();
        }

    
    public String getPhone() {
            return ROU.getPhone();
        }

    
    public String getCity() {
            return ROU.getCity();
        }

    
    public String getStreet() {
            return ROU.getStreet();
        }

    
}
