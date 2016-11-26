/*
import java.io.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {

    public static void main(String[] args) throws IOException{

        ArrayList<Rule> rules = new ArrayList<>();
        ArrayList<Tape> tapes = new ArrayList<>();

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter location of Rules to Use: ");
        String ruleLoc = reader.nextLine();
        System.out.printf("Enter location of Tapes to Use: ");
        String tapeLoc = reader.nextLine();


        FileReader in = null;
        ArrayList<String> file = new ArrayList<>();
        try {
            in = new FileReader(ruleLoc);
            char c;
            String s = "";
            while ((c = (char)in.read()) != (char)-1) {
                if(c != '\n'){
                    s += c;
                }
                else{
                    file.add(s);
                    s = "";
                }
            }
            file.add(s);
            String[] parts = {""};
            String[] pieces = {""};
            for(String line : file){
                parts = line.split("/");
                int preState = parseInt(parts[0]);
                pieces = parts[1].split(",");
                String[] preCondition = new String[pieces.length];
                for(int l = 0; l < pieces.length; l++){
                    preCondition[l] = pieces[l];
                }
                pieces = parts[2].split(",");
                String[] postCondition = new String[pieces.length];
                for(int l = 0; l < pieces.length; l++){
                    postCondition[l] = pieces[l];
                }
                pieces = parts[3].split(",");
                char[] shift = new char[pieces.length];
                for(int l = 0; l < pieces.length; l++){
                    shift[l] = pieces[l].charAt(0);
                }
                int postState = parseInt(parts[4]);
                Rule rule = new Rule(preState, preCondition, postCondition, shift, postState);
                rules.add(rule);
            }

            in.close();



            in = new FileReader(tapeLoc);
            file = new ArrayList<>();
            s = "";
            while ((c = (char)in.read()) != (char)-1) {
                if(c != '\n' && c!=(char)13){
                    s += c;
                }
                else if(c != (char)13){
                    file.add(s);
                    s = "";
                }
            }
            file.add(s);
            for(String line : file){
                ArrayList<String> inputTape = new ArrayList<>();
                System.out.println(line);
                pieces = line.split("/");
                for(int l = 2; l < pieces.length; l++) {
                    String d = pieces[l];
                    inputTape.add(d);
                }
                int pos = parseInt(pieces[1]);
                if(pos == -1){
                    pos = inputTape.size() - 1;
                }
                Tape inTape = new Tape(inputTape, pieces[0], pos);
                tapes.add(inTape);
            }

            TuringMachine tm = new TuringMachine(rules, tapes, "-");
            tm.runMachine();
        }
        finally {
            if (in != null) {
                in.close();
            }
        }
    }


}
*/

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {

    public static void main(String[] args) throws IOException{

        ArrayList<Rule> rules = new ArrayList<>();
        ArrayList<Tape> tapes = new ArrayList<>();

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter location of Rules to Use: ");
        String ruleLoc = reader.nextLine();
        System.out.printf("Enter location of Tapes to Use: ");
        String tapeLoc = reader.nextLine();


        FileReader in = null;
        ArrayList<String> file = new ArrayList<>();
        try {
            in = new FileReader(ruleLoc);
            char c;
            String s = "";
            while ((c = (char)in.read()) != (char)-1) {
                if(c != '\n'){
                    s += c;
                }
                else{
                    file.add(s);
                    s = "";
                }
            }
            file.add(s);

            String[] parts = {""};
            String[] pieces = {""};

            for(String line : file){
                try {
                    if (line.charAt(0) != '/' && line.length() > 0) {
                        line = line.substring(1);
                        int preState = parseInt(line.substring(0,line.indexOf(',')));
                        line = line.substring(line.indexOf('{')+1);
                        String[] preCondition = line.substring(0,line.indexOf('}')).split(",");
                        line = line.substring(line.indexOf('{')+1);
                        String[] postCondition = line.substring(0,line.indexOf('}')).split(",");
                        line = line.substring(line.indexOf('{')+1);
                        String[] shift = line.substring(0,line.indexOf('}')).split(",");
                        char[] move = new char[shift.length];
                        for (int l = 0;l < shift.length;l++) {
                            move[l] = shift[l].charAt(0);
                        }
                        line = line.substring(line.indexOf('}')+2);
                        int postState = parseInt(line.substring(0,line.indexOf(')')));

                        Rule rule = new Rule(preState, preCondition, postCondition, move, postState);
                        rules.add(rule);
                    }
                }
                catch (IndexOutOfBoundsException e){
                    System.out.println("I'm not a rule: " + line);
                }
                catch (NumberFormatException e){
                    System.out.println("I'm not a rule: " + e);
                }
            }

            in.close();



            in = new FileReader(tapeLoc);
            file = new ArrayList<>();
            s = "";
            while ((c = (char)in.read()) != (char)-1) {
                if(c != '\n' && c!=(char)13){
                    s += c;
                }
                else if(c != (char)13){
                    file.add(s);
                    s = "";
                }
            }
            file.add(s);
            for(String line : file){
                ArrayList<String> inputTape = new ArrayList<>();
                System.out.println(line);
                pieces = line.split(",");
                line = pieces[1];
                for(int l = 0; l < line.length(); l++) {
                    String d = "" + line.charAt(l);
                    inputTape.add(d);
                }
                int pos = parseInt(pieces[0]);
                if(pos == -1){
                    pos = inputTape.size() - 1;
                }
                Tape inTape = new Tape(inputTape, " ", pos);
                tapes.add(inTape);
            }

            TuringMachine tm = new TuringMachine(rules, tapes, " ");
            tm.runMachine();
        }
        finally {
            if (in != null) {
                in.close();
            }
        }
    }


}
