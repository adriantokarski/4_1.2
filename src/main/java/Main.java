/*
Kod bazowy programu Commit4_0: 
• Program dodaje do prostej bazy danych (pliku db.txt) dane odnośnie Studentów.
• Studenci dodawani są w klasie Main.
• Wszyscy studenci są wypisywani na końcu klasy Main.
• Klasa Service obsługuje odczyt i zapis do pliku bazy danych.
• Klasa Student reprezentuje pojedynczego studenta (Imię, Wiek).
*/

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      Service s = new Service();

      System.out.print("Podaj imię nowego studenta: ");
      String imie = scanner.nextLine().trim();

      int wiek = 0;
      boolean validAge = false;
      while (!validAge) {
        System.out.print("Podaj wiek nowego studenta: ");
        try {
          wiek = scanner.nextInt();
          scanner.nextLine(); 
          if (wiek > 0) {
            validAge = true;
          } else {
            System.out.println("Wiek musi być liczbą dodatnią.");
          }
        } catch (InputMismatchException e) {
          System.out.println("Nieprawidłowy format wieku. Podaj liczbę całkowitą.");
          scanner.nextLine();
        }
      }

      if (!imie.isEmpty()) {
        s.addStudent(new Student(imie, wiek));
        System.out.println("Dodano studenta do bazy.");
      } else {
        System.out.println("Imię nie może być puste — nie dodano nowego studenta.");
      }

      System.out.print("\nCzy wypisać wszystkich studentów z bazy? (t/n): ");
      String odpowiedz = scanner.nextLine().trim().toLowerCase();

      if (odpowiedz.equals("t") || odpowiedz.equals("tak")) {
        System.out.println("\nLista studentów w bazie:");
        var students = s.getStudents();
        for (Student current : students) {
          System.out.println(current.ToString());
        }
      } else {
        System.out.println("Pominięto wypisywanie listy studentów.");
      }
    } catch (IOException e) {
      System.out.println("Błąd operacji na pliku bazy danych: " + e.getMessage());
    }
  }
}