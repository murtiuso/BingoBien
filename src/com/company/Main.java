package com.company;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        //CONSTANTS
        final int MIN = 1;
        final int MAX = 90;
        final int ROWS = 3;
        final int POS = 5;
        final int TEN = 10;
        final int NUMS = ROWS * POS;
        final int FIRSTLINE = 0;
        final int SECONDLINE = 5;
        final int THIRDLINE = 10;

        //BOLOOLEAN
        boolean bol = false;
        boolean bin = true;

        //VARIABLES
        int numbr;
        int line = 3;
        int count;

        //ARRAYS
        int plays[][];
        int lineWin[][];
        int bingWin[][];
        int bingo[] = new int[MAX];

        System.out.println("\n Welcome to the Classic Bingo \n");
        System.out.printf(" Insert number of games : ");

        numbr = input.nextInt();            // Insert number of game plays. Número de cartones

        plays = new int[numbr][NUMS];       // and select for two arrays. Cada cartón con sus 15 espacios.
        lineWin = new int[numbr][POS];      // in first dimension the prev Int
        bingWin = new int[numbr][ROWS];

        // Se rellena cada uno de los cartones con números aleatorios de menor a mayor sin que se repitan
        for (int i = 0; i < plays.length; i++) {
            fillSortedArray(plays[i], NUMS, MIN, MAX);
        }

        //  Función para imprimir el cartón o los cartones con los que se juega
        printArray (plays, POS, ROWS);

        //  Función que
        balls(bingo, MAX);

        do {
            for (int i = 0; i < bingo.length; i++) {                      //Bucle para contar bolas

                System.out.println(" The ball number " + ((i + 1) + " is : " + bingo[i]));

                for (int j = 0; j < numbr; j++) { //Cuenta los cartones
                    for (int m = 0; m < NUMS; m++) { //Cuenta los números del cartón
                        if (bingo[i] == plays[j][m]) { // Comprobar bola con todas las posiciones del cartón
                            if (m >= THIRDLINE) {      //Las 5 últimas posiciones
                                lineWin[j][2]++; // si la bola pertenece a la tercera linea, el numero de carton J la linea 2 ++
                                System.out.println("Encontrado en el cartón " + (j + 1) + " en la linea 3");

                                if (lineWin[j][2] == 5) { // cuando lo de arriba valga 5 tenemos linea
                                    bingWin[j][0]++;
                                    if (bingWin[j][0] != 2) {
                                        System.out.println("linea en 3");
                                    }
                                }
                            } else if (m >= SECONDLINE && m < THIRDLINE) {       //Las posiciones entre 5 y 10 del cartón
                                lineWin[j][1]++;
                                System.out.println("Encontrado en el cartón " + (j + 1) + "  en la linea 2");

                                if (lineWin[j][1] == 5) {
                                    bingWin[j][0]++;

                                    if (bingWin[j][0] != 2) {
                                        System.out.println("linea en 2");
                                    }
                                }
                            } else if (m >= FIRSTLINE && m < SECONDLINE) {        //Las primeras 5 posiciones del cartón
                                lineWin[j][0]++;
                                System.out.println("Encontrado en el cartón " + (j + 1) + "  en la linea 1");

                                if (lineWin[j][0] == 5) {
                                    bingWin[j][0]++;

                                    if (bingWin[j][0] != 2) {
                                        System.out.println("linea en 1");
                                    }
                                }
                            }
                            for (int k = 0; k < ROWS; k++) {
                                if (bingWin[j][k] == ROWS) {
                                    System.out.println("Bingo en el cartón " + (j + 1));
                                }
                            }
                        }
                    }
                }
            }
        }
        while (!bin) ;
    }
    // crea un cartón con números ordendos aleatorios de menor a mayor sin repeticiones por cada vez que se llame a esta función
    static void fillSortedArray(int[] plays, int numsCopy, int minCopy, int maxCopy) {

        int arrayCopy[] = new int[numsCopy];
        boolean bol = false;

        do {
            for (int j = 0; j < arrayCopy.length; j++) {                //generate numbers
                arrayCopy[j] = (int) (Math.random() * (maxCopy) + minCopy);
            }
            Arrays.sort(arrayCopy);                                     //order numbers
            for (int k = 1; k < arrayCopy.length; k++) {                    //Check that any number content in array is repeated
                if (arrayCopy[k] == arrayCopy[k - 1]) {
                    bol = false;
                    break;
                } else if (k <= arrayCopy.length) {
                    bol = true;
                }
            }
        } while (!bol);
        for (int c = 0; c < arrayCopy.length; c++) {             // When all numbers content in arrayCopy are different,
            plays[c] = arrayCopy[c];                            // we charge the arraycopy  to our principal array
        }
    }

    /**
     * Función para imprimir el cartón o los cartones con los que se juega
     * @param array es el array plays, que vienen a ser los cartones
     * @param colsCopy: copia de POS
     * @param rowsCopy  Copia de las filas
     */
    static void printArray(int array[][], int colsCopy, int rowsCopy) {

        for (int i = 0; i < array.length; i++) {

            System.out.println("\n Game number " + (i + 1));
            System.out.println();

            for (int j = 0; j < array[i].length; j++) {

                System.out.printf("%3d", array[i][j]);

                if ((j + 1) % colsCopy == 0) { // Esta parte hace que se impriman las casillas del array de 5 en 5; cuando j vale 4, + 1 == 5, 5 % 5 == 0 y entonces salto de línea y así
                    System.out.println();
                }
            }
            System.out.println();
        }
    }

    /**
     *  En esta función se rellena un array que contendrá las bolas que saldrán y su orden. Simula el sacar bola.
     * @param bingoCopy
     * @param MAX
     */
    static void  balls (int[] bingoCopy,final int MAX) {

        int num;
        int maxAux=MAX;
        int ballsSorted[]=new int [MAX];
        int ballsCopy [] = new int [MAX];

        Random  rnd = new Random();

        for (int i = 0; i < ballsSorted.length; i++) {
            ballsSorted[i] = i + 1;
        }
        for (int i = 0; i < ballsCopy.length; i++) {
            // probar con el otro random a ver si da el mismo resultado
            num = rnd.nextInt(maxAux);
            ballsCopy[i] = ballsSorted[num];
            ballsSorted[num] = ballsSorted[maxAux - 1];
            maxAux--;
        }
        for (int i = 0; i < ballsCopy.length; i++) {
            bingoCopy[i]= ballsCopy[i];
        }
    }
}