//TASK 4
//Online Movie Ticket Booking System
//Description : Build an Online Movie Ticket Booking System that allows users to browse movies, book tickets, and view showtimes. Include features for seat selection and ticket cancellation.

import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

class Movie {
    private String title;
    private List<String> showtimes;

    public Movie(String title, List<String> showtimes) {
        this.title = title;
        this.showtimes = showtimes;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getShowtimes() {
        return showtimes;
    }

    @Override
    public String toString() {
        return title + " - Showtimes: " + String.join(", ", showtimes);
    }
}


class Seat {
    private int row;
    private int number;
    private boolean isBooked;

    public Seat(int row, int number) {
        this.row = row;
        this.number = number;
        this.isBooked = false;
    }

    public int getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void book() {
        if (!isBooked) {
            isBooked = true;
            System.out.println("Seat " + row + "-" + number + " has been booked.");
        } else {
            System.out.println("Seat " + row + "-" + number + " is already booked.");
        }
    }

    public void cancelBooking() {
        if (isBooked) {
            isBooked = false;
            System.out.println("Seat " + row + "-" + number + " booking has been canceled.");
        } else {
            System.out.println("Seat " + row + "-" + number + " is not booked.");
        }
    }

    @Override
    public String toString() {
        return "Row: " + row + ", Seat: " + number + ", " + (isBooked ? "Booked" : "Available");
    }
}

class Theater {
    private List<Seat> seats;

    public Theater(int rows, int seatsPerRow) {
        seats = new ArrayList<>();
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= seatsPerRow; j++) {
                seats.add(new Seat(i, j));
            }
        }
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public Seat getSeat(int row, int number) {
        for (Seat seat : seats) {
            if (seat.getRow() == row && seat.getNumber() == number) {
                return seat;
            }
        }
        return null;
    }

    public void displaySeats() {
        System.out.println("Theater Seating:");
        for (Seat seat : seats) {
            System.out.println(seat);
        }
    }
}

public class MovieTicketBookingSystem {
    private List<Movie> movies;
    private Map<String, Theater> theaters;

    public MovieTicketBookingSystem() {
        movies = new ArrayList<>();
        theaters = new HashMap<>();
    }

    public void addMovie(Movie movie, Theater theater) {
        movies.add(movie);
        for (String showtime : movie.getShowtimes()) {
            theaters.put(movie.getTitle() + "_" + showtime, theater);
        }
    }

    public void browseMovies() {
        System.out.println("Available Movies:");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    public void selectSeatAndBook(String movieTitle, String showtime, int row, int seatNumber) {
        String key = movieTitle + "_" + showtime;
        Theater theater = theaters.get(key);
        if (theater != null) {
            Seat seat = theater.getSeat(row, seatNumber);
            if (seat != null) {
                seat.book();
            } else {
                System.out.println("Invalid seat selection.");
            }
        } else {
            System.out.println("Invalid movie or showtime.");
        }
    }

    public void cancelBooking(String movieTitle, String showtime, int row, int seatNumber) {
        String key = movieTitle + "_" + showtime;
        Theater theater = theaters.get(key);
        if (theater != null) {
            Seat seat = theater.getSeat(row, seatNumber);
            if (seat != null) {
                seat.cancelBooking();
            } else {
                System.out.println("Invalid seat selection.");
            }
        } else {
            System.out.println("Invalid movie or showtime.");
        }
    }

    public void displayTheater(String movieTitle, String showtime) {
        String key = movieTitle + "_" + showtime;
        Theater theater = theaters.get(key);
        if (theater != null) {
            theater.displaySeats();
        } else {
            System.out.println("Invalid movie or showtime.");
        }
    }

    public static void main(String[] args) {
        MovieTicketBookingSystem bookingSystem = new MovieTicketBookingSystem();

        // Creating sample movies and theaters
        Movie movie1 = new Movie("Avengers: Endgame", Arrays.asList("10:00 AM", "02:00 PM", "06:00 PM"));
        Movie movie2 = new Movie("The Lion King", Arrays.asList("11:00 AM", "03:00 PM", "07:00 PM"));

        Theater theater1 = new Theater(5, 5); // 5 rows, 5 seats per row
        Theater theater2 = new Theater(3, 4); // 3 rows, 4 seats per row

        bookingSystem.addMovie(movie1, theater1);
        bookingSystem.addMovie(movie2, theater2);

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Online Movie Ticket Booking System ---");
            System.out.println("1. Browse Movies");
            System.out.println("2. View Theater Seating");
            System.out.println("3. Book TicketCancel Ticket");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = sc.nextInt();
            sc.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    bookingSystem.browseMovies();
                    break;
                case 2:
                    System.out.print("Enter movie title: ");
                    String movieTitle = sc.nextLine();
                    System.out.print("Enter showtime: ");
                    String showtime = sc.nextLine();
                    bookingSystem.displayTheater(movieTitle, showtime);
                    break;
                   
                case 3:
                    System.out.print("Enter movie title: ");
                    movieTitle = sc.nextLine();
                    System.out.print("Enter showtime: ");
                    showtime = sc.nextLine();
                    System.out.print("Enter row number: ");
                    int row = sc.nextInt();
                    System.out.print("Enter seat number: ");
                    int seatNumber = sc.nextInt();
                    bookingSystem.selectSeatAndBook(movieTitle, showtime, row, seatNumber);
                    break;
                   
                case 4:
                    System.out.print("Enter movie title: ");
                    movieTitle = sc.nextLine();
                    System.out.print("Enter showtime: ");
                    showtime = sc.nextLine();
                    System.out.print("Enter row number: ");
                    row = sc.nextInt();
                    System.out.print("Enter seat number: ");
                    seatNumber = sc.nextInt();
                    bookingSystem.cancelBooking(movieTitle, showtime, row, seatNumber);
                    break;
                    
                case 5:
                    System.out.println("Thank you for using the Movie Ticket Booking System!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

