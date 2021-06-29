package cinema;


import java.util.Scanner;

public class Cinema {
    static char seatS = 'S';
    static char seatB = 'B';
    static int seatPrice = 10;
    static int secondHalfSeatPrice = 8;
    static int totalNumOfSeats;
    static int firstHalfRows;
    static int secondHalfRows;
    static int rowNum;
    static int seatNum;
    static int numOfRows;
    static int numOfSeats;

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        numOfRows = scn.nextInt();
        System.out.println("Enter the number of seats in each row:");
        numOfSeats = scn.nextInt();
        totalNumOfSeats = numOfRows * numOfSeats;
        Character[][] cinema = new Character[numOfRows][numOfSeats];
        fillTheCinema(cinema, numOfRows, numOfSeats);
        while (true) {
            showMenu();
            int menuOpt = scn.nextInt();
            switch (menuOpt) {
                case 1:
                    printCinema(cinema, numOfSeats);
                    break;
                case 2:
                    makeBooking(cinema, numOfRows, numOfSeats, totalNumOfSeats);
                    break;
                case 3:
                    showStatistics(cinema, totalNumOfSeats, numOfRows, numOfSeats);
                    break;
                case 0:
                    return;
                default:
                    break;
            }
        }
    }


    public static void printCinema(Character[][] cinema, int numOfSeats) {
        System.out.println("\nCinema:");
        System.out.print("  ");
        for (int e = 1; e <= numOfSeats; e++) {
            System.out.print(e + " ");
        }
        System.out.println();
        int count = 1;
        for (Character[] characters : cinema) {
            System.out.print(count++ + " ");
            for (Character character : characters) {
                System.out.print(character + " ");
            }
            System.out.println();
        }
    }

    public static void fillTheCinema(Character[][] cinema, int numOfRows, int numOfSeats) {
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfSeats; j++) {
                cinema[i][j] = seatS;
            }
        }
    }

    public static void makeBooking(Character[][] cinema, int numOfRows, int numOfSeats, int totalNumOfSeats) {
        Scanner scn = new Scanner(System.in);
        boolean isOccupied = true;
        boolean isOutOfBounds = true;
        while (isOutOfBounds) {
            while (isOccupied) {
                System.out.println("\nEnter a row number:");
                rowNum = scn.nextInt();
                System.out.println("Enter a seat number in that row:");
                seatNum = scn.nextInt();
                if (rowNum >= 0 && rowNum <= numOfRows && seatNum >= 0 && seatNum <= numOfSeats) {
                    isOutOfBounds = false;
                    rowNum -= 1;
                    seatNum -= 1;
                    if (cinema[rowNum][seatNum] != seatB) {
                        isOccupied = false;
                        cinema[rowNum][seatNum] = seatB;
                        if (totalNumOfSeats < 60) {
                            checkTicketPriceForSmallCinema(seatPrice);
                        } else {
                            checkTicketPriceForBigCinema(numOfRows, rowNum);
                        }
                    } else {
                        isOccupied = true;
                        System.out.println("\nThat ticket has already been purchased!");
                    }
                } else {
                    isOutOfBounds = true;
                    System.out.println("\nWrong input!");
                }
            }
        }
    }

    public static void checkTicketPriceForBigCinema(int numOfRows, int rowNum) {
        firstHalfRows = numOfRows / 2;
        secondHalfRows = numOfRows - firstHalfRows;
        rowNum += 1;
        if (rowNum < secondHalfRows) {
            System.out.printf("%nTicket price: $%d%n", seatPrice);
        } else {
            System.out.printf("%nTicket price: $%d%n", secondHalfSeatPrice);
        }
    }

    public static void checkTicketPriceForSmallCinema(int seatPrice) {
        System.out.printf("%nTicket price: $%d%n", seatPrice);
    }

    public static void showMenu() {
        System.out.println("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
    }

    public static void showStatistics(Character[][] cinema, int totalNumOfSeats, int numOfRows, int numOfSeats) {
        int count = 0;
        double percentage;
        int currentIncome;
        int totalIncome;
        if (totalNumOfSeats < 60) {
            for (Character[] characters : cinema) {
                for (Character character : characters) {
                    if (character == seatB) {
                        count++;
                    }
                }
            }
            System.out.println();
            System.out.printf("Number of purchased tickets: %d", count);
            System.out.println();
            percentage = (double) count * (double) 100 / (double) totalNumOfSeats;
            System.out.printf("Percentage: %.2f", percentage);
            System.out.print("%");
            System.out.println();
            currentIncome = count * seatPrice;
            System.out.printf("Current income: $%d", currentIncome);
            System.out.println();
            totalIncome = numOfRows * numOfSeats * seatPrice;
            System.out.printf("Total income: $%d", totalIncome);
            System.out.println();
        } else {
            int countVip = 0;
            int countNotVip = 0;
            System.out.println();
            firstHalfRows = numOfRows / 2;
            secondHalfRows = numOfRows - firstHalfRows;
            totalIncome = (firstHalfRows * numOfSeats * seatPrice) + (secondHalfRows * numOfSeats * secondHalfSeatPrice);
            firstHalfRows -= 1;
            secondHalfRows -= 1;
            for (int i = 0; i < cinema.length; i++) {
                for (int j = 0; j < cinema[i].length; j++) {
                    if (i <= firstHalfRows && cinema[i][j] == seatB) {
                        countVip++;
                    } else if (i > firstHalfRows && cinema[i][j] == seatB) {
                        countNotVip++;
                    }
                }
            }
            int numOfPurchasedTickets = countVip + countNotVip;
            System.out.printf("Number of purchased tickets: %d", numOfPurchasedTickets);
            System.out.println();
            percentage = (double) numOfPurchasedTickets * (double) 100 / (double) totalNumOfSeats;
            System.out.printf("Percentage: %.2f", percentage);
            System.out.print("%");
            System.out.println();
            currentIncome = (countVip * seatPrice) + (countNotVip * secondHalfSeatPrice);
            System.out.printf("Current income: $%d", currentIncome);
            System.out.println();
            System.out.printf("Total income: $%d", totalIncome);
            System.out.println();
        }
    }
}

