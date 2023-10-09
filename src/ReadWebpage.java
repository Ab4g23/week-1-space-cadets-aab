import java.net.*;
import java.io.*;

public class ReadWebpage {
    public static void main(String[] args) {
        //getting email id input from the user
        Toolbox toolbox = new Toolbox();
        String emailid = "";
        emailid = toolbox.readStringFromCmd();


        //Reading the webpage into a string
        URL website = null;
        URLConnection connect = null;

        try {
            //find webpage and read to string
            String myurl = "https://www.ecs.soton.ac.uk/people/";
            myurl = myurl + emailid;

            website = new URL(myurl);
            connect = website.openConnection();

            InputStreamReader reader = new InputStreamReader(connect.getInputStream(), "UTF8");
            BufferedReader buffer = new BufferedReader(reader);

            while (true) { // read a line off the website
                String line = "";
                line = buffer.readLine();

                if (line != null && line.indexOf("\"name\":") != -1 && line.indexOf("University") == -1) {
                    //find the line with the person's name

                    int nameStart = line.indexOf("name") + 8;
                    int ifPrint = -1;
                    String name = "";

                    System.out.println("");

                    for (int i = nameStart; i < line.length(); i++) {
                        //save just the name and print to console

                        if (line.substring(i,i+1) == "\"") {
                            break;
                        } else {
                            name = name + line.substring(i,i+1);
                        }
                    }

                    name = name.substring(0, name.length() - 2);
                    System.out.println(name);

                } else if (line == null){
                    break;
                }
            }
        }

        catch(Exception e) {
            e.printStackTrace();
        }

    }
}