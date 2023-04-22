import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class FuelCenter {

    //arrays/fuel queues of type Passenger
    private static Passenger[] queue1= new Passenger[6];
    private static Passenger[] queue2= new Passenger[6];
    private static Passenger[] queue3= new Passenger[6];
    private static Passenger[] queue4= new Passenger[6];
    private static Passenger[] queue5= new Passenger[6];

    //fuel stock
    private static int fuelStock= 6600;

    //liters required by a customer
    private static int fuelForCustomer=0;

    //income of each fuel queue
    private static double queue1_Income;
    private static double queue2_Income;
    private static double queue3_Income;
    private static double queue4_Income;
    private static double queue5_Income;

    //circular queue implementation for waiting list if all fuel queues are full
    private static int maxSize=5; //size of queue
    private static Passenger [] circularQueue = new Passenger[maxSize];
    private static int front= 0; //front pointer
    private static int rear= -1; //back pointer
    private static int items= 0; //no. of items of the queue

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
                    110 or IFQ: View Income of Each Fuel Queue.
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
                case "110", "IFQ" ->
                        calcQueueIncome();
            }
        }
    }
    public static void initializeFuelQueues(){
        for(int i=0;i<=5;i++){
            queue1[i]= new Passenger("empty");
        }
        for(int i=0;i<=5;i++){
            queue2[i]= new Passenger("empty");
        }
        for(int i=0;i<=5;i++){
            queue3[i]= new Passenger("empty");
        }
        for(int i=0;i<=5;i++){
            queue4[i]= new Passenger("empty");
        }
        for(int i=0;i<=5;i++){
            queue5[i]= new Passenger("empty");
        }
    }
    public static void viewFuelQueues(){
        System.out.print("Pump 1: ");
        for(int i=0;i<=5;i++){
            System.out.print(queue1[i].getFirstName()+" ");
        }
        System.out.print("\n");
        System.out.print("Pump 2: ");
        for(int i=0;i<=5;i++) {
            System.out.print(queue2[i].getFirstName()+" ");
        }
        System.out.print("\n");
        System.out.print("Pump 3: ");
        for(int i=0;i<=5;i++) {
            System.out.print(queue3[i].getFirstName()+" ");
        }
        System.out.print("\n");
        System.out.print("Pump 4: ");
        for(int i=0;i<=5;i++) {
            System.out.print(queue4[i].getFirstName()+" ");
        }
        System.out.print("\n");
        System.out.print("Pump 5: ");
        for(int i=0;i<=5;i++) {
            System.out.print(queue5[i].getFirstName()+" ");
        }
        System.out.print("\n");
        System.out.print("\n");
    }
    public static void viewEmptyQueues(){
        boolean empty1 = false;
        boolean empty2 = false;
        boolean empty3 = false;
        boolean empty4= false;
        boolean empty5= false;

        for(int i=0;i<=5;i++){
            if (queue1[i].getFirstName().equals("empty")) {
                empty1 = true;
                break;
            }
        }
        for(int i=0;i<=5;i++){
            if (queue2[i].getFirstName().equals("empty")) {
                empty2 = true;
                break;
            }
        }
        for(int i=0;i<=5;i++){
            if (queue3[i].getFirstName().equals("empty")) {
                empty3 = true;
                break;
            }
        }
        for(int i=0;i<=5;i++){
            if (queue4[i].getFirstName().equals("empty")) {
                empty4 = true;
                break;
            }
        }
        for(int i=0;i<=5;i++){
            if (queue5[i].getFirstName().equals("empty")) {
                empty5 = true;
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
        if(empty4){
            System.out.println("Fuel Queue 4 is empty. ");
        }
        if(empty5){
            System.out.println("Fuel Queue 5 is empty. ");
        }
        System.out.print("\n");
    }
    public static void addCustomer(){
        Scanner obj= new Scanner(System.in);

        System.out.println("Enter the details of the passenger below. ");
        System.out.println("First Name: ");
        String firstName= obj.nextLine();
        System.out.println("Last Name: ");
        String lastName= obj.nextLine();
        System.out.println("Vehicle Number: ");
        String vehicleNum= obj.nextLine();
        System.out.println("How many liters of petrol? ");
        int litersRequired= obj.nextInt();

        //identifying the fuel queue with the minimum length

        //indices of all 5 queues
        int i=0;
        int j=0;
        int k=0;
        int l=0;
        int m=0;

        //arrays to store indices at which loops break
        int[] values= new int[5];
        int[] values2= new int[5];

        while(queue1[i].getFirstName() != "empty"){
            i++;
            values[0]= i;
            values2[0]=i;
        }
        while(queue2[j].getFirstName() != "empty"){
            j++;
            values[1]= j;
            values2[1]=j;
        }
        while(queue3[k].getFirstName() != "empty"){
            k++;
            values[2]= k;
            values2[2]=k;
        }
        while(queue4[l].getFirstName() != "empty"){
            l++;
            values[3]= l;
            values2[3]=l;
        }
        while(queue5[m].getFirstName() != "empty"){
            m++;
            values[4]= m;
            values2[4]=m;
        }

        Arrays.sort(values);
        int queuePosition=values[0]; //minimum index out of all queues

        //identifying to which queue the minimum index belongs to
        if(queuePosition==values2[0]){
            queue1[queuePosition]=new Passenger(firstName, lastName, vehicleNum, litersRequired);
        }
        else if(queuePosition==values2[1]){
            queue2[queuePosition]=new Passenger(firstName, lastName, vehicleNum, litersRequired);
        }
        else if(queuePosition==values2[2]){
            queue3[queuePosition]=new Passenger(firstName, lastName, vehicleNum, litersRequired);
        }
        else if(queuePosition==values2[3]){
            queue4[queuePosition]=new Passenger(firstName, lastName, vehicleNum, litersRequired);
        }
        else if(queuePosition==values2[4]){
            queue5[queuePosition]=new Passenger(firstName, lastName, vehicleNum, litersRequired);
        }

        System.out.println("Added Successfully!");

        //waiting list implementation for adding passengers

        //checking if all fuel queues are full
        //5 denotes the last index of each queue
        if (values2[0]==5 && values2[1]==5 && values2[2]==5 &&
                values2[3]==5 && values2[4]==5){

            //check if waiting list is full
                if(items==maxSize){
                    System.out.println("Waiting list queue is full right now. ");
                }
                else{
                    if(rear == maxSize-1){
                        rear=-1;

                        circularQueue[++rear]= new Passenger(firstName, lastName, vehicleNum, litersRequired);
                        items++;
                        System.out.println("Passenger has been added to the waiting queue.");
                        System.out.println(Arrays.toString(circularQueue));
                    }
                }
        }
    }
    public static void removeCustomer(){
        System.out.println("From which queue do you wish to remove? (1,2,3,4 or 5)");
        Scanner obj= new Scanner(System.in);
        int queue= obj.nextInt();
        System.out.println("Okay. Which position? (1-6)");
        int index= obj.nextInt();
        index-=1;

        if(queue==1){
            queue1[index].setFirstName("empty");
            queue1[index].setLastName(null);
            queue1[index].setVehicleNum(null);
            queue1[index].setLitersRequired(0);
        }
        if(queue==2){
            queue2[index].setFirstName("empty");
            queue2[index].setLastName(null);
            queue2[index].setVehicleNum(null);
            queue2[index].setLitersRequired(0);
        }
        if(queue==3){
            queue3[index].setFirstName("empty");
            queue3[index].setLastName(null);
            queue3[index].setVehicleNum(null);
            queue3[index].setLitersRequired(0);
        }
        if(queue==4){
            queue4[index].setFirstName("empty");
            queue4[index].setLastName(null);
            queue4[index].setVehicleNum(null);
            queue4[index].setLitersRequired(0);
        }
        if(queue==5){
            queue5[index].setFirstName("empty");
            queue5[index].setLastName(null);
            queue5[index].setVehicleNum(null);
            queue5[index].setLitersRequired(0);
        }
        System.out.println("Removed Successfully. ");
    }
    public static void removeServed(){
        Scanner obj= new Scanner(System.in);
        System.out.println("Which queue does the served customer belong to? (1-6)?");
        int queue= obj.nextInt();

        /*
        update fuel stock within each fuel queue based on the fuel liters required by the passenger in the
        first index since first index will be served first.
        Calculate fuel income of each pump by 430 pounds/liter * customer's purchase.
        */

        if(queue==1){
            fuelStock-=queue1[0].getLitersRequired();
            queue1_Income+= queue1[0].getLitersRequired()*430;

            queue1[0].setFirstName("empty");
            queue1[0].setLastName(null);
            queue1[0].setVehicleNum(null);
            queue1[0].setLitersRequired(0);
            System.out.println("Removed Successfully!");

            //sending passenger to queue1 from waiting list
            if(items==0){
                System.out.println("Waiting list is empty so not adding anyone automatically. ");
            }
            else{
                queue1[5]= circularQueue[front++];
                if (front==maxSize){
                    front=0;
                    items--;
                }
            }
        }
        if(queue==2){

            fuelStock-=queue2[0].getLitersRequired();
            queue2_Income+= queue2[0].getLitersRequired()*430;

            queue2[0].setFirstName("empty");
            queue2[0].setLastName(null);
            queue2[0].setVehicleNum(null);
            queue2[0].setLitersRequired(0);
            System.out.println("Removed Successfully!");

            //sending passenger to queue1 from waiting list
            if(items==0){
                System.out.println("Waiting list is empty so not adding anyone automatically. ");
            }
            else{
                queue2[5]= circularQueue[front++];
                if (front==maxSize){
                    front=0;
                    items--;
                }
            }
        }
        if(queue==3){

            fuelStock-=queue3[0].getLitersRequired();
            queue3_Income+= queue3[0].getLitersRequired()*430;

            queue3[0].setFirstName("empty");
            queue3[0].setLastName(null);
            queue3[0].setVehicleNum(null);
            queue3[0].setLitersRequired(0);
            System.out.println("Removed Successfully!");

            //sending passenger to queue1 from waiting list
            if(items==0){
                System.out.println("Waiting list is empty so not adding anyone automatically. ");
            }
            else{
                queue3[5]= circularQueue[front++];
                if (front==maxSize){
                    front=0;
                    items--;
                }
            }
        }
        if(queue==4){

            fuelStock-=queue4[0].getLitersRequired();
            queue4_Income+= queue4[0].getLitersRequired()*430;

            queue4[0].setFirstName("empty");
            queue4[0].setLastName(null);
            queue4[0].setVehicleNum(null);
            queue4[0].setLitersRequired(0);
            System.out.println("Removed Successfully!");

            //sending passenger to queue1 from waiting list
            if(items==0){
                System.out.println("Waiting list is empty so not adding anyone automatically. ");
            }
            else{
                queue4[5]= circularQueue[front++];
                if (front==maxSize){
                    front=0;
                    items--;
                }
            }
        }
        if(queue==5){

            fuelStock-=queue5[0].getLitersRequired();
            queue5_Income+= queue5[0].getLitersRequired()*430;

            queue5[0].setFirstName("empty");
            queue5[0].setLastName(null);
            queue5[0].setVehicleNum(null);
            queue5[0].setLitersRequired(0);
            System.out.println("Removed Successfully!");

            //sending passenger to queue1 from waiting list
            if(items==0){
                System.out.println("Waiting list is empty so not adding anyone automatically. ");
            }
            else{
                queue5[5]= circularQueue[front++];
                if (front==maxSize){
                    front=0;
                    items--;
                }
            }
        }
    }
    public static void calcQueueIncome(){
        System.out.println("Income of each fuel queue in pounds -> ");
        System.out.println("Queue 1: "+queue1_Income+"\nQueue2: "+queue2_Income+"\nQueue3: "+queue3_Income+
                "\nQueue4: "+queue4_Income+"\nQueue5: "+queue5_Income);
    }
    public static void viewCustomers(){
        System.out.println("Sorting customers in each queue alphabetically by first name...");

        //storing first names in arrays
        String[] firstnames1= new String[6];
        String[] firstnames2= new String[6];
        String[] firstnames3= new String[6];
        String[] firstnames4= new String[6];
        String[] firstnames5= new String[6];

        for(int i=0;i<=5;i++){
            firstnames1[i]= queue1[i].getFirstName();
        }
        for(int i=0;i<=5;i++){
            firstnames2[i]= queue2[i].getFirstName();
        }
        for(int i=0;i<=5;i++){
            firstnames3[i]= queue3[i].getFirstName();
        }
        for(int i=0;i<=5;i++){
            firstnames4[i]= queue4[i].getFirstName();
        }
        for(int i=0;i<=5;i++){
            firstnames5[i]= queue5[i].getFirstName();
        }

        //sorting now
        Arrays.sort(firstnames1);
        System.out.print("Pump 1: ");
        for(int i=0;i<=5;i++){
            System.out.print(firstnames1[i]+" ");
        }
        Arrays.sort(firstnames2);
        System.out.println(" ");
        System.out.print("Pump 2: ");
        for(int i=0;i<=5;i++){
            System.out.print(firstnames2[i]+" ");
        }
        Arrays.sort(firstnames3);
        System.out.println(" ");
        System.out.print("Pump 3: ");
        for(int i=0;i<=5;i++){
            System.out.print(firstnames3[i]+" ");
        }
        Arrays.sort(firstnames4);
        System.out.println(" ");
        System.out.print("Pump 4: ");
        for(int i=0;i<=5;i++){
            System.out.print(firstnames4[i]+" ");
        }
        Arrays.sort(firstnames5);
        System.out.println(" ");
        System.out.print("Pump 5: ");
        for(int i=0;i<=5;i++){
            System.out.print(firstnames5[i]+" ");
        }
        System.out.println(" \n");
    }
    public static void writeToFile(){
        //storing required data first

        //storing first names in arrays
        String[] firstnames1= new String[6];
        String[] firstnames2= new String[6];
        String[] firstnames3= new String[6];
        String[] firstnames4= new String[6];
        String[] firstnames5= new String[6];

        //storing last names in arrays
        String[] lastnames1= new String[6];
        String[] lastnames2= new String[6];
        String[] lastnames3= new String[6];
        String[] lastnames4= new String[6];
        String[] lastnames5= new String[6];

        //storing vehicle numbers in arrays
        String[] vehicleNumbers1= new String[6];
        String[] vehicleNumbers2= new String[6];
        String[] vehicleNumbers3= new String[6];
        String[] vehicleNumbers4= new String[6];
        String[] vehicleNumbers5= new String[6];

        //storing petrol required in arrays
        int[] liters1= new int[6];
        int[] liters2= new int[6];
        int[] liters3= new int[6];
        int[] liters4= new int[6];
        int[] liters5= new int[6];

        for(int i=0;i<=5;i++){
            firstnames1[i]= queue1[i].getFirstName();
            lastnames1[i]= queue1[i].getLastName();
            vehicleNumbers1[i]= queue1[i].getVehicleNum();
            liters1[i]= queue1[i].getLitersRequired();
        }
        for(int i=0;i<=5;i++){
            firstnames2[i]= queue2[i].getFirstName();
            lastnames2[i]= queue2[i].getLastName();
            vehicleNumbers2[i]= queue2[i].getVehicleNum();
            liters2[i]= queue2[i].getLitersRequired();
        }
        for(int i=0;i<=5;i++){
            firstnames3[i]= queue3[i].getFirstName();
            lastnames3[i]= queue3[i].getLastName();
            vehicleNumbers3[i]= queue3[i].getVehicleNum();
            liters3[i]= queue3[i].getLitersRequired();
        }
        for(int i=0;i<=5;i++){
            firstnames4[i]= queue4[i].getFirstName();
            lastnames4[i]= queue4[i].getLastName();
            vehicleNumbers4[i]= queue4[i].getVehicleNum();
            liters4[i]= queue4[i].getLitersRequired();
        }
        for(int i=0;i<=5;i++){
            firstnames5[i]= queue5[i].getFirstName();
            lastnames5[i]= queue5[i].getLastName();
            vehicleNumbers5[i]= queue5[i].getVehicleNum();
            liters5[i]= queue5[i].getLitersRequired();
        }

        System.out.println("Writing to file fuelCenter.txt...");
        try {
            FileWriter writer= new FileWriter("Save_to_File.txt");
            writer.write("Fuel Queue 1-> Passenger Details for the 6 slots: "+"\n"+
                    Arrays.toString(firstnames1)+"\n"+Arrays.toString(lastnames1)+"\n"+
                    Arrays.toString(vehicleNumbers1)+"\n"+Arrays.toString(liters1)+"\nIncome: "+queue1_Income+" \n\n"+
                    "Fuel Queue 2-> Passenger Details for the 6 slots: "+"\n"+ Arrays.toString(firstnames2)+
                    "\n"+Arrays.toString(lastnames2)+"\n"+ Arrays.toString(vehicleNumbers2)+"\n"+
                    Arrays.toString(liters2)+"\nIncome: "+queue2_Income+" \n\n"+
                    "Fuel Queue 3-> Passenger Details for the 6 slots: "+"\n"+
                    Arrays.toString(firstnames3)+"\n"+Arrays.toString(lastnames3)+"\n"+
                    Arrays.toString(vehicleNumbers3)+"\n"+Arrays.toString(liters3)+"\nIncome: "+queue3_Income+" \n\n"+
                    "Fuel Queue 4-> Passenger Details for the 6 slots: "+"\n"+
                    Arrays.toString(firstnames4)+"\n"+Arrays.toString(lastnames4)+"\n"+
                    Arrays.toString(vehicleNumbers4)+"\n"+Arrays.toString(liters4)+"\nIncome: "+queue4_Income+" \n\n"+
                    "Fuel Queue 5-> Passenger Details for the 6 slots: "+"\n"+
                    Arrays.toString(firstnames5)+"\n"+Arrays.toString(lastnames5)+"\n"+
                    Arrays.toString(vehicleNumbers5)+"\n"+Arrays.toString(liters5)+"\nIncome: "+queue5_Income+" \n"
                    );
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
        System.out.println("How many liters of fuel  are you adding? ");
        Scanner obj= new Scanner(System.in);
        int liters= obj.nextInt();
        fuelStock+=liters;
        System.out.println("Fuel Added. Availability= "+fuelStock);
    }
}
