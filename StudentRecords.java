import java.io.*;

class StudentRecords {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void addRecords() throws IOException {
        // Create or Modify a file for Database
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("studentRecords.txt", true)));
        String name, Year, fname, mname, address, dob, gen;
        int age;
        long telephoneNo;
        double c;
        String s, regNo;

        boolean addMore = false;
        // Read Data
        do {
            System.out.print("\nEnter name: ");
            name = br.readLine();

            System.out.print("Enter Father's Name: ");
            fname = br.readLine();

            System.out.print("Enter Mother's Name: ");
            mname = br.readLine();

            System.out.print("Enter the Address: ");
            address = br.readLine();

            int yearInput;
            do {
                System.out.print("Enter the Year (1-4): ");
                yearInput = Integer.parseInt(br.readLine());
                if (yearInput < 1 || yearInput > 4) {
                    System.out.println("Invalid year. Please enter a value from 1 to 4.");
                }
            } while (yearInput < 1 || yearInput > 4);
            Year = String.valueOf(yearInput);

            System.out.print("Enter the Date of Birth (dd/mm/yy) : ");
            dob = br.readLine();

            System.out.print("Enter the Age: ");
            age = Integer.parseInt(br.readLine());

            System.out.print("Enter the Telephone No.: ");
            telephoneNo = Long.parseLong(br.readLine());

            String regNoInput;
            boolean validRegNo = false;
            do {
                System.out.print("Enter the registration number as APXXXXXXXXXXX: ");
                regNoInput = br.readLine();
                if (regNoInput.matches("AP\\d{11}")) {
                    validRegNo = true;
                } else {
                    System.out.println("Invalid registration number. Please enter a valid registration number as APXXXXXXXXXXX (AP followed by 11 digits): ");
                }
            } while (!validRegNo);
            regNo = regNoInput;

            System.out.print("Enter the gender : ");
            gen = br.readLine();

            double cgpaInput;
            do {
                System.out.print("\nEnter the CGPA (0.000000 - 10.000000): ");
                cgpaInput = Double.parseDouble(br.readLine());
                if (cgpaInput < 0.000000 || cgpaInput > 10.000000) {
                    System.out.println("Invalid CGPA. Please enter a value from 0.000000 to 10.000000.");
                }
            } while (cgpaInput < 0.000000 || cgpaInput > 10.000000);
            c = cgpaInput;

            // Print to File
            pw.println(regNo);
            pw.println(name);
            pw.println(fname);
            pw.println(mname);
            pw.println(address);
            pw.println(Year);
            pw.println(dob);
            pw.println(age);
            pw.println(telephoneNo);
            pw.println(gen);
            pw.println(c);

            System.out.print("\nRecords added successfully!\n\nDo you want to add more records? (y/n): ");
            s = br.readLine();
            if (s.equalsIgnoreCase("y")) {
                addMore = true;
                System.out.println();
            } else
                addMore = false;
        } while (addMore);
        pw.close();
        showMenu();
    }

    public void readRecords() throws IOException {
        try {
            // Open the file
            BufferedReader file = new BufferedReader(new FileReader("studentRecords.txt"));
            String regNoInput;
            boolean validRegNo = false;
            do {
                System.out.print("\nEnter the registration number to display the records: ");
                regNoInput = br.readLine();
                if (regNoInput.matches("AP\\d{11}")) {
                    validRegNo = true;
                } else {
                    System.out.println("Invalid registration number. Please enter a valid registration number as APXXXXXXXXXXX (AP followed by 11 digits): ");
                }
            } while (!validRegNo);
            String regNo = regNoInput;

            String record;
            boolean found = false;
            // Read records from the file
            while ((record = file.readLine()) != null) {
                if (record.equals(regNo)) {
                    System.out.println("---------- Displaying the records for registration number " + regNo + " --------");
                    System.out.println("Name: " + file.readLine());
                    System.out.println("Father's Name: " + file.readLine());
                    System.out.println("Mother's Name: " + file.readLine());
                    System.out.println("Address: " + file.readLine());
                    System.out.println("Year: " + file.readLine());
                    System.out.println("Date of Birth: " + file.readLine());
                    System.out.println("Age: " + Integer.parseInt(file.readLine()));
                    System.out.println("Tel. No.: " + Long.parseLong(file.readLine()));
                    System.out.println("Gender: " + file.readLine());
                    System.out.println("CGPA: " + Double.parseDouble(file.readLine()));
                    System.out.println();
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("No records found for registration number " + regNo);
            }
            file.close();
            showMenu();
        } catch (FileNotFoundException e) {
            System.out.println("\nERROR: File not Found!");
        }
    }

    public void clearRecords() throws IOException {
        try {
            BufferedReader file = new BufferedReader(new FileReader("studentRecords.txt"));
            String regNoInput;
            boolean validRegNo = false;
            do {
                System.out.print("\nEnter the registration number to clear the records: ");
                regNoInput = br.readLine();
                if (regNoInput.matches("AP\\d{11}")) {
                    validRegNo = true;
                } else {
                    System.out.println("Invalid registration number. Please enter a valid registration number as APXXXXXXXXXXX (AP followed by 11 digits): ");
                }
            } while (!validRegNo);
            String regNo = regNoInput;

            StringBuilder records = new StringBuilder();
            String record;
            boolean found = false;
            // Read records from the file
            while ((record = file.readLine()) != null) {
                if (record.equals(regNo)) {
                    // Skip the records for the specified registration number
                    for (int i = 0; i < 10; i++) {
                        file.readLine();
                    }
                    found = true;
                } else {
                    records.append(record).append("\n");
                }
            }
            file.close();

            // Write the updated records back to the file
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("studentRecords.txt")));
            pw.print(records.toString());
            pw.close();

            if (found) {
                System.out.println("Records for registration number " + regNo + " cleared successfully!");
            } else {
                System.out.println("No records found for registration number " + regNo);
            }
            for (int i = 0; i < 999999999; i++); // Wait for some time
            showMenu();
        } catch (FileNotFoundException e) {
            System.out.println("\nERROR: File not Found!");
        }
    }

    public void displayAllRecords() throws IOException {
        try {
            BufferedReader file = new BufferedReader(new FileReader("studentRecords.txt"));
            String record;
            int count = 0;
            System.out.println("---------- Displaying all student records ----------");
            while ((record = file.readLine()) != null) {
                System.out.println("Registration Number: " + record);
                System.out.println("Name: " + file.readLine());
                System.out.println("Father's Name: " + file.readLine());
                System.out.println("Mother's Name: " + file.readLine());
                System.out.println("Address: " + file.readLine());
                System.out.println("Year: " + file.readLine());
                System.out.println("Date of Birth: " + file.readLine());
                System.out.println("Age: " + Integer.parseInt(file.readLine()));
                System.out.println("Tel. No.: " + Long.parseLong(file.readLine()));
                System.out.println("Gender: " + file.readLine());
                System.out.println("CGPA: " + Double.parseDouble(file.readLine()));
                System.out.println();
                count++;
            }
            file.close();
            if (count == 0) {
                System.out.println("No records found!");
            }
            showMenu();
        } catch (FileNotFoundException e) {
            System.out.println("\nERROR: File not Found!");
        }
    }

    public void clearAllRecords() throws IOException {
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("studentRecords.txt")));
            pw.close();
            System.out.println("All student records cleared successfully!");
            for (int i = 0; i < 999999999; i++); // Wait for some time
            showMenu();
        } catch (IOException e) {
            System.out.println("\nERROR: Unable to clear all student records!");
        }
    }

    public void showMenu() throws IOException {
        System.out.println("1 : Add Records");
        System.out.println("2 : Display Records");
        System.out.println("3 : Clear Records");
        System.out.println("4 : Display All Records");
        System.out.println("5 : Clear All Records");
        System.out.println("6 : Exit");

        System.out.print("\nYour Choice : ");
        int choice = Integer.parseInt(br.readLine());
        switch (choice) {
            case 1:
                addRecords();
                break;
            case 2:
                readRecords();
                break;
            case 3:
                clearRecords();
                break;
            case 4:
                displayAllRecords();
                break;
            case 5:
                clearAllRecords();
                break;
            case 6:
                System.exit(1);
                break;
            default:
                System.out.println("\nInvalid Choice!");
                break;
        }
    }

    public static void main(String args[]) throws IOException {
        StudentRecords call = new StudentRecords();
        call.showMenu();
    }
}