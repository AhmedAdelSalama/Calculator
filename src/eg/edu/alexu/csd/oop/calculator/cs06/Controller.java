package eg.edu.alexu.csd.oop.calculator.cs06;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Controller implements Calculator {
     double firstOperand=0;
     double secondOperand=0;
     int operation =-1;
     LinkedList<String> history = new LinkedList<>();
     ListIterator<String> iterator = history.listIterator(history.size());
     boolean forward = true;
//     String[] history1 = new String[5];
//     int iterator1 = -1;


    @FXML
    private Button next;

    @FXML
    private Button nine;

    @FXML
    private Button minus;

    @FXML
    private Button six;

    @FXML
    private Button previous;

    @FXML
    private Button one;

    @FXML
    private Button clear;

    @FXML
    private Button save;

    @FXML
    private Button seven;

    @FXML
    private Label label1;
    @FXML
    private Label label2;

    @FXML
    private Button two;

    @FXML
    private Button three;

    @FXML
    private Button plus;

    @FXML
    private Button point;

    @FXML
    private Button eight;

    @FXML
    private Button zero;

    @FXML
    private Button equal;

    @FXML
    private Button load;

    @FXML
    private Button four;

    @FXML
    private Button divide;

    @FXML
    private Button multiply;

    @FXML
    private Button five;
    @FXML
    private Button negative;

    @FXML
    private void checkDisbled(){
        point.setDisable(label1.getText().contains("."));
        negative.setDisable(!label1.getText().isEmpty()||label1.getText().contains("-"));
        plus.setDisable(label1.getText().isEmpty()|| (!label2.getText().isEmpty()&&label2.getText().endsWith(" ")));
        minus.setDisable(label1.getText().isEmpty()|| (!label2.getText().isEmpty()&&label2.getText().endsWith(" ")));
        multiply.setDisable(label1.getText().isEmpty()|| (!label2.getText().isEmpty()&&label2.getText().endsWith(" ")));
        divide.setDisable(label1.getText().isEmpty()|| (!label2.getText().isEmpty()&&label2.getText().endsWith(" ")));
        equal.setDisable(label1.getText().isEmpty()||!label2.getText().endsWith(" "));
        previous.setDisable(history.isEmpty()|| history.size()==1 ||!iterator.hasPrevious());
        next.setDisable(history.isEmpty() ||history.size()==1 ||!iterator.hasNext());

    }

    @FXML
    void OnActionHandler(ActionEvent event) throws IOException {
        if( label2.getText().split(" ").length == 3 &&event.getSource()!= multiply&&event.getSource()!= divide
                &&event.getSource()!= plus&&event.getSource()!= minus){
            label2.setText("");
            label1.setText("");
        }
        if(event.getSource()== one){
            label1.setText(label1.getText()+"1");
        }else if (event.getSource()== two){
            label1.setText(label1.getText()+"2");
        }else if (event.getSource()== three){
            label1.setText(label1.getText()+"3");
        }else if (event.getSource()== four){
            label1.setText(label1.getText()+"4");
        }else if (event.getSource()== five){
            label1.setText(label1.getText()+"5");
        }else if (event.getSource()== six){
            label1.setText(label1.getText()+"6");
        }else if (event.getSource()== seven){
            label1.setText(label1.getText()+"7");
        }else if (event.getSource()== eight){
            label1.setText(label1.getText()+"8");
        }else if (event.getSource()== nine){
            label1.setText(label1.getText()+"9");
        }else if (event.getSource()== zero){
            label1.setText(label1.getText()+"0");
        }else if (event.getSource()== clear){
            label1.setText("");
        }else if (event.getSource()== point){
            label1.setText(label1.getText()+".");
        }else if(event.getSource()== negative){
            label1.setText(label1.getText()+"-");
        }else if (event.getSource()== plus){
            firstOperand = Double.parseDouble(label1.getText());
            operation=1;
            label2.setText(label1.getText()+" + ");
            label1.setText("");
        }else if (event.getSource()== minus){
            firstOperand = Double.parseDouble(label1.getText());
            operation=2;
            label2.setText(label1.getText()+" - ");
            label1.setText("");
        }else if (event.getSource()== multiply){
            firstOperand = Double.parseDouble(label1.getText());
            operation=3;
            label2.setText(label1.getText()+" * ");
            label1.setText("");
        }else if (event.getSource()== divide){
            firstOperand = Double.parseDouble(label1.getText());
            operation=4;
            label2.setText(label1.getText()+" / ");
            label1.setText("");
        }else if (event.getSource()== equal){
            label2.setText(label2.getText()+label1.getText());
            iterator.add(label2.getText());
            secondOperand = Double.parseDouble(label1.getText());
            double ans = 0;
            equal.setDisable(true);
            switch (operation){
                case 1:
                    ans = firstOperand + secondOperand ;
                    label1.setText(String.valueOf(ans));
                    break;
                case 2:
                    ans = firstOperand - secondOperand ;
                    label1.setText(String.valueOf(ans));
                    break;
                case 3:
                    ans = firstOperand * secondOperand ;
                    label1.setText(String.valueOf(ans));
                    break;
                case 4:
                    try {
                        ans = firstOperand / secondOperand;
                    }catch (Exception e){
                        label1.setText("Maths Error");
                    }
                    label1.setText(String.valueOf(ans));
                    break;


            }
        }else if (event.getSource()== previous){
            showPrev();
        }else if (event.getSource()== next){
            showNext();

        }else if (event.getSource()== save){
            save();
        }else if (event.getSource()== load) {
            load();
        }
        checkDisbled();
        if(event.getSource()== equal ||event.getSource()== load
                ||event.getSource()== next ||event.getSource()== previous){
            negative.setDisable(false);
            point.setDisable(false);
        }
    }

    @Override
    public void input(String s) {
        try {
                history.add(s);
                if(history.size()>5){
                    history.removeFirst();
                }
                iterator = history.listIterator(history.size());
                forward = true;
        }catch (Throwable e){
            e.getMessage();
        }
    }

    @Override
    public String getResult() {
        String[] arr ;
        try{
        if (current().contains("+")) {
            int i = current().indexOf("+");
            arr = new String[]{current().substring(0, i), current().substring(i + 1, current().length())};
            return Double.toString(calculate(arr[0] + " + " + arr[1]));
        } else if (current().contains("*")) {
            int i = current().indexOf("*");
            arr = new String[]{current().substring(0, i), current().substring(i + 1, current().length())};
            return Double.toString(calculate(arr[0] + " * " + arr[1]));
        } else if (current().contains("/")) {
            int i = current().indexOf("/");
            arr = new String[]{current().substring(0, i), current().substring(i + 1, current().length())};
            return Double.toString(calculate(arr[0] + " / " + arr[1]));
        }else if (current().contains("-")) {
            int i = current().indexOf("-");
            if(i==0){
                String s;
                for(i=1 ; i < current().length();i++){
                    String string = String.valueOf(current().charAt(i));
                    if(string.equals("-"))
                        break;
                }
            }
            arr = new String[]{current().substring(0, i), current().substring(i + 1, current().length())};
            return Double.toString(calculate(arr[0] + " - " + arr[1]));
        }
        }catch (Throwable e){
        e.getMessage();
    }
        return null;
    }

    @Override
    public String current() {
        try {
            if (!forward) {
                forward = true;
                if (iterator.hasNext())
                    return iterator.next();
            }
            forward = false;
            if (iterator.hasPrevious())
                return iterator.previous();
//            return history1[iterator1];
        } catch (Throwable e) {
            return e.getMessage();
        }
        return null;
    }

    @Override
    public String prev() {
        try {
//            iterator1--;
//            return history1[iterator1];
            if (forward) {
                if (iterator.hasPrevious()) {
                    iterator.previous();
                }
                forward = false;
            }
            return iterator.previous();
        }catch (Throwable e){
            return e.getMessage();
        }
    }
    @FXML
    public void showPrev(){
        try {
            if (forward) {
                if (iterator.hasPrevious()) {
                    iterator.previous();
                }
                forward = false;
            }
            label2.setText(iterator.previous());
            label1.setText(Double.toString(calculate(label2.getText())));
        }catch (Throwable e){
            e.getMessage();
        }
    }

    @Override
    public String next() {
        try {
            if (!forward) {
                if (iterator.hasNext()) {
                    iterator.next();
                }
                forward = true;
            }
            return iterator.next();
        }catch (Throwable e){
            return e.getMessage();
        }
    }
    @FXML
    public void showNext(){
        try {
            if (!forward) {
                if (iterator.hasNext()) {
                    iterator.next();
                }
                forward = true;
            }
            label2.setText(iterator.next());
            label1.setText(Double.toString(calculate(label2.getText())));
        }catch (Throwable e){
            e.getMessage();
        }

    }


    @Override
    public void save() {
        try {
            PrintWriter writer = new PrintWriter("history.txt", "UTF-8");
            writer.println(iterator.nextIndex());
            Iterator<String> i = history.iterator();
            while (i.hasNext()) {
                writer.println(i.next());
            }
            writer.close();
        }catch (Throwable e) {
            e.getMessage();
        }
    }

    @Override
    public void load()  {
        int i = 0;
        try {
            history.clear();
            File file = new File("history.txt");
            Scanner sc = null;
            sc = new Scanner(file);
            if(sc.hasNextLine()) {
                 i = Integer.parseInt(sc.nextLine());
            }
            while (sc.hasNextLine()) {
                history.add(sc.nextLine());
            }
            iterator = history.listIterator(i);
            forward = false;
            label2.setText(history.getLast());
            label1.setText(Double.toString(calculate(history.getLast())));
        }catch (Throwable e) {
            e.getMessage();
        }
    }


    @FXML
    public double calculate(String line) {
        try {
            String[] arr = line.split(" ");
            firstOperand = Double.parseDouble(arr[0]);
            secondOperand = Double.parseDouble(arr[2]);
            double ans = 0;
            if (arr[1].equals("+")) {
                ans = firstOperand + secondOperand;
//            label1.setText(String.valueOf(ans));

            } else if (arr[1].equals("-")) {
                ans = firstOperand - secondOperand;
//            label1.setText(String.valueOf(ans));

            } else if (arr[1].equals("*")) {
                ans = firstOperand * secondOperand;
//            label1.setText(String.valueOf(ans));
            } else {
                ans = firstOperand / secondOperand;
            }
//        label1.setText(String.valueOf(ans));
            return ans;

    }catch (Throwable e){
        e.getMessage();
    }
      return -1;
    }
}
