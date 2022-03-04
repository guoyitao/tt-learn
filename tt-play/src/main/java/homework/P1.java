package homework;

import java.util.Scanner;

/**
 * This is a program hat will read from the user the sky condition (string) and the current temperature (int).
 * Your program will inform the user if they need a jacket and/or sunglasses.
 * The user will need a jacket if the temperature is lower than 15 (inclusive)
 * and they will need sunglasses if the sky is "sunny".
 * If neither condition is true the user will need nothing.
 *
 * @authorXiaoyiWang date:07/10/2021
 */
public class P1 {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String nameOfSky = kb.next();
        int numberOfTemper = kb.nextInt();

        if ("sunny".equals(nameOfSky)) {
            if (numberOfTemper > 15){
                System.out.println("You'll need sunglasses");
            }else if (numberOfTemper <= 15) {
                System.out.println("You'll need a jacket and sunglasses");
                System.out.println();
            }
        }else {
            System.out.println("You'll need nothing");
            System.out.println();
        }
    }
}
 
 