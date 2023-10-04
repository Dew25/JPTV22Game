/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sptv22game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Melnikov
 */
public class App {
    private File file;
    private Properties prop;

    public App() {
        prop = new Properties();
    }
    
    public void run(){
        int balance=Integer.parseInt(loadBalance());
        
        System.out.println("---- Game ----");
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int min = 0;
        int max = 9;
        boolean repeat = true;
        String nextGame = "y";
    
        do{
            if(balance < 1){
                System.out.printf("У вас нулевой баланс, хотите пополнить? (y/n)[n]: ", balance);
                nextGame = scanner.nextLine();
                if(nextGame.equals("y")){
                    System.out.println("Добавьте жетонов: ");
                    balance += scanner.nextInt();scanner.nextLine();
                }else{
                    break;
                }
            }
            System.out.println("Баланс: "+balance);
            int myNumber = random.nextInt(max-min+1)+min;
            int userNumber = 10;
            System.out.println("Задумано число в диапазоне от 0 до 9, угадай какое (3 попытки)");
            int atempt = 1;
            do{
                System.out.print("Попытка № " + atempt + ": ");
                do{
                    try {
                        userNumber = scanner.nextInt();
                        scanner.nextLine();
                        break;
                    } catch (Exception e) {
                        System.out.print("Недопустимы символ, еще раз: ");
                        scanner.nextLine();
                    }
                }while(true);
                if(myNumber == userNumber){
                    System.out.println("Ты выиграл! Ура!");
                    balance++;
                    saveBalance(balance);
                    break;
                }else{
                    if(atempt > 2){
                        System.out.println("Ты проиграл :(, задумано число: " + myNumber);
                        balance--;
                        saveBalance(balance);
                        break;
                    }else{
                        if(myNumber > userNumber){
                            System.out.println("Неправильно, попробуй еще (задумано число больше твоего)");
                        }else{
                            System.out.println("Неправильно, попробуй еще (задумано число меньше твоего)");

                        }
                    }
                }

                atempt++;
            }while(true);
            System.out.print("Press \"q\" for exit, or another key to continue: ");
            String q = scanner.nextLine();
            if(q.equals("q")){
                repeat = false;
            }
        }while(repeat);
        System.out.println("---- end Game ----");

    }

    private String loadBalance() {
        file = new File("settings");
        InputStream in = null;
        try{
            in = new FileInputStream(file);
            prop.load(in);
            String strBalance = prop.getProperty("balance");
            if(strBalance == null){
                return "0";
            }else{
                return strBalance;
            }
        } catch (IOException ex) {
            return saveBalance(10);
        }finally {
            try {
                if(in != null)
                in.close();
            } catch (IOException ex) {
                return "";
            }
        }
        
    }

    private String saveBalance(int balance){
        OutputStream out = null;
        try {
            file = new File("settings");
            out = new FileOutputStream(file);
            prop.setProperty("balance", ((Integer)balance).toString());
            prop.store(out, null);
        } catch (FileNotFoundException ex) {
            return "10";
        } catch (IOException ex) {
            return "10";
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                
            }
        }
        return "0";
    }
    
    
}
