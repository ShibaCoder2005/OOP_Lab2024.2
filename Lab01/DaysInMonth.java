import java.util.Scanner;

public class DaysInMonth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter month (1-12 or jan, feb,...): ");
        String month = scanner.next();
        
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        
        int days;
        switch (month) {
            case "1": case "january": case "jan":
                days = 31; break;
            case "2": case "february": case "feb":
                days = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ? 29 : 28;
                break;
            case "3": case "march": case "mar":
                days = 31; break;
            case "4": case "april": case "apr":
                days = 30; break;
            case "5": case "may":
                days = 31; break;
            case "6": case "june": case "jun":
                days = 30; break;
            case "7": case "july": case "jul":
                days = 31; break;
            case "8": case "august": case "aug":
                days = 31; break;
            case "9": case "september": case "sep":
                days = 30; break;
            case "10": case "october": case "oct":
                days = 31; break;
            case "11": case "november": case "nov":
                days = 30; break;
            case "12": case "december": case "dec":
                days = 31; break;
            default:
                System.out.println("Invalid month input!");
                return;
        }
        
        System.out.println("Number of days in " + month + " " + year + ": " + days);
        scanner.close();
    }
}
