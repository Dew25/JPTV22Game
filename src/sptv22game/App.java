/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sptv22game;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Melnikov
 */
public class App {

    public void run() {
        System.out.println("---- Game ----");
        Random random = new Random();
        boolean repeat = true;
        do{
            int myNumber = random.nextInt(9-0+1)-0;
            int userNumber = 10;
            System.out.println("Задумано число в диапазоне от 0 до 9, угадай какое (3 попытки)");
            Scanner scanner = new Scanner(System.in);
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
                    break;
                }else{
                    if(atempt > 2){
                        System.out.println("Ты проиграл :(, задумано число: " + myNumber);
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
    
}
