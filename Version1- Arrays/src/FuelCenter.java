import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class FuelCenter {
    //fuel pumps
    static String[] pump1= new String[6];
    static String[] pump2= new String[6];
    static String[] pump3= new String[6];

    //fuel capacity
    static int fuelStock= 6600;

    public static void main(String[]args){
        //console menu
        String option=" ";

        //initialize all fuel queues first
        initializeFuelQueues();

        while(!(option.equals("999") | option.equals("EXT"))){
            System.out.println("--FUEL CENTER-- says Choose an option: ");
            //warning message
            if(fuelStock==500){
                System.out.println("Fuel stock has reached 500 liters! Enter 109 or AFS to add fuel.");
            }
            System.out.println("""
                    100 or VFQ: View all Fuel Queues.
                    101 or VEQ: View all Empty Queues.
                    102 or ACQ: Add customer to a Queue.
                    103 or RCQ: Remove a customer from a Queue. (From a specific location)
                    104 or PCQ: Remove a served customer.
                    105 or VCS: View customers sorted in alphabetical order.
                    106 or SPD: Store program data into file.
                    107 or LPD: Load Program Data from file.
                    108 or STK: View Remaining Fuel Stock.
                    109 or AFS: Add Fuel Stock.
                    999 or EXT: Exit the Program.""");
            Scanner obj= new Scanner(System.in);
            option= obj.nextLine();

            switch (option) {
                case "100", "VFQ" ->
                    //view all fuel queues
                        viewFuelQueues();
                case "101", "VEQ" ->
                    //view all empty queues
                        viewEmptyQueues();
                case "102", "ACQ" ->
                    //add customer to queue
                        addCustomer();
                case "103", "RCQ" ->
                    //remove customer from queue
                        removeCustomer();
                case "104", "PCQ" ->
                    //remove a served customer
                        removeServed();
                case "105", "VCS" ->
                    //view customers sorted in alphabetical order
                        viewCustomers();
                case "106", "SPD" ->
                    //store program data into a file
                        writeToFile();
                case "107", "LPD" ->
                    //load program data from a file
                        readFromFile();
                case "108", "STK" ->
                    //view remaining fuel stock
                        viewFuelStock();
                case "109", "AFS" ->
                    //add fuel stock
                        addFuel();
            }
        }
    }
    public static void initializeFuelQueues(){
        for(int i=0;i<=5;i++){
            pump1[i]= "empty";
        }
        for(int i=0;i<=5;i++){
            pump2[i]= "empty";
        }
        for(int i=0;i<=5;i++){
            pump3[i]= "empty";
        }
    }
    public static void viewFuelQueues(){
        System.out.print("Pump 1: ");
        for(int i=0;i<=5;i++){
            System.out.print(pump1[i]+" ");
        }
        System.out.print("\n");
        System.out.print("Pump 2: ");
        for(int i=0;i<=5;i++) {
            System.out.print(pump2[i]+" ");
        }
        System.out.print("\n");
        System.out.print("Pump 3: ");
        for(int i=0;i<=5;i++) {
            System.out.print(pump3[i]+" ");
        }
        System.out.print("\n");
        System.out.print("\n");
    }
    public static void viewEmptyQueues(){
        boolean empty1 = false;
        boolean empty2 = false;
        boolean empty3 = false;

        for(int i=0;i<=5;i++){
            if (pump1[i].equals("empty")) {
                empty1 = true;
                break;
            }
        }
        for(int i=0;i<=5;i++){
            if (pump2[i].equals("empty")) {
                empty2 = true;
                break;
            }
        }
        for(int i=0;i<=5;i++){
            if(pump3[i].equals("empty")){
                empty3=true;
                break;
            }
        }

        if(empty1){
            System.out.println("Fuel Queue 1 is empty. ");
        }
        if(empty2){
            System.out.println("Fuel Queue 2 is empty. ");
        }
        if(empty3){
            System.out.println("Fuel Queue 3 is empty. ");
        }
        System.out.print("\n");
    }
    public static void addCustomer(){
        System.out.println("Enter customer's name: ");
        Scanner obj= new Scanner(System.in);
        String name= obj.nextLine();
        System.out.println("Which queue do you wish to add into (1,2, or 3?");
        int number= obj.nextInt();
        System.out.println("Okay. Which position in the queue (1-6)? ");
        int number2= obj.nextInt();
        number2-=1;

        if(number==1){
            pump1[number2]=name;
        }
        if(number==2){
            pump2[number2]=name;
        }
        if(number==3){
            pump2[number2]=name;
        }
        System.out.println("Added Successfully!");
    }
    public static void removeCustomer(){
        System.out.println("From which queue do you wish to remove? (1,2 or 3)");
        Scanner obj= new Scanner(System.in);
        int queue= obj.nextInt();
        System.out.println("Okay. Which position? (1-6)");
        int position= obj.nextInt();
        position-=1;

        if(queue==1){
            pump1[position]="empty";
        }
        if(queue==2){
            pump2[position]="empty";
        }
        if(queue==3){
            pump3[position]="empty";
        }
        System.out.println("Removed Successfully. ");
    }
    public static void removeServed(){
        System.out.println("Enter the name of the served customer? ");
        Scanner obj= new Scanner(System.in);
        String name= obj.nextLine();
        System.out.println("Which queue does the customer belong to? (1-3)?");
        int queue= obj.nextInt();
        queue-=1;

        //update fuel stock
        fuelStock-=10;

        if(queue==0){
            for(int i = 0; i<=5; i++){
                if(pump1[i].equals(name)){
                    pump1[i]= "empty";
                    System.out.println("Removed Successfully!");
                }
                else{
                    System.out.println("Customer named "+name+" was not found in queue "+queue+".");
                }
            }
        }
        if(queue==1){
            for(int i = 0; i<=5; i++){
                if(pump2[i].equals(name)){
                    pump2[i]= "empty";
                    System.out.println("Removed Successfully!");
                }
                else{
                    System.out.println("Customer named "+name+" was not found in queue "+queue+".");
                }
            }
        }
        if(queue==2){
            for(int i = 0; i<=5; i++){
                if(pump3[i].equals(name)){
                    pump3[i]= "empty";
                    System.out.println("Removed Successfully!");
                }
                else{
                    System.out.println("Customer named "+name+" was not found in queue "+queue+".");
                }
            }
        }
    }
    public static void viewCustomers(){
        System.out.println("Sorting customers in each queue alphabetically...");
        Arrays.sort(pump1);
        System.out.print("Pump 1: ");
        for(int i=0;i<=5;i++){
            System.out.print(pump1[i]+" ");
        }
        System.out.println(" ");
        Arrays.sort(pump2);
        System.out.print("Pump 2: ");
        for(int i=0;i<=5;i++){
            System.out.print(pump2[i]+" ");
        }
        System.out.println(" ");
        Arrays.sort(pump3);
        System.out.print("Pump 3: ");
        for(int i=0;i<=5;i++){
            System.out.print(pump3[i]+" ");
        }
        System.out.println(" \n");
    }
    public static void writeToFile(){
        System.out.println("Writing to file fuelCenter.txt...");
        try {
            FileWriter writer= new FileWriter("Save_to_File.txt");
            writer.write("Fuel Queue 1: "+Arrays.toString(pump1)+"\nFuel Queue 2: "+Arrays.toString(pump2)+
                    "\nFuel Queue 3: "+Arrays.toString(pump3));
            writer.close();
        } catch (IOException e) {
            System.out.println("Error occurred. "+e.getMessage());
        }
        System.out.println("Written to file. ");
    }
    public static void readFromFile(){
        File file= new File("Save_to_File.txt");
        System.out.println("Reading from file fuelCenter.txt...");
        try {
            Scanner reader= new Scanner(file);
            while(reader.hasNextLine()){
                String data= reader.nextLine();
                System.out.println(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred. "+e.getMessage());
        }
        System.out.println(" ");
    }
    public static void viewFuelStock(){
        System.out.println("Fuel Available= "+fuelStock);
    }
    public static void addFuel(){
        System.out.println("How many liters of fuel are you adding? ");
        Scanner obj= new Scanner(System.in);
        int liters= obj.nextInt();
        fuelStock+=liters;
        System.out.println("Fuel Added. Availability= "+fuelStock);
    }
}
