public class Passenger{
    private String firstName;
    private String lastName;
    private String vehicleNum;
    private int litersRequired;

    public Passenger(String firstName, String lastName, String vehicleNum, int litersRequired){
        this.firstName= firstName;
        this.lastName= lastName;
        this.vehicleNum= vehicleNum;
        this.litersRequired= litersRequired;
    }
    public Passenger(String firstName){
        this.firstName=firstName;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getVehicleNum(){
        return vehicleNum;
    }
    public int getLitersRequired(){
        return litersRequired;
    }

    public void setFirstName(String firstName){
        this.firstName= firstName;
    }
    public void setLastName(String lastName){
        this.lastName= lastName;
    }
    public void setVehicleNum(String vehicleNum){
        this.vehicleNum= vehicleNum;
    }
    public void setLitersRequired(int litersRequired){
        this.litersRequired= litersRequired;
    }
}