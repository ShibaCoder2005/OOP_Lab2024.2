import java.util.Scanner;

public class EquationSolver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Solve a linear equation (ax + b = 0)");
            System.out.println("2. Solve a system of linear equations (2 variables)");
            System.out.println("3. Solve a quadratic equation (ax^2 + bx + c = 0)");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    solveLinearEquation(scanner);
                    break;
                case 2:
                    solveLinearSystem(scanner);
                    break;
                case 3:
                    solveQuadraticEquation(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void solveLinearEquation(Scanner scanner) {
        System.out.println("Enter coefficients a and b for equation ax + b = 0:");
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        
        if (a == 0) {
            System.out.println(b == 0 ? "Infinite solutions" : "No solution");
        } else {
            System.out.println("Solution: x = " + (-b / a));
        }
    }
    
    private static void solveLinearSystem(Scanner scanner) {
        System.out.println("Enter coefficients a11, a12, b1, a21, a22, b2 for the system:");
        double a11 = scanner.nextDouble(), a12 = scanner.nextDouble(), b1 = scanner.nextDouble();
        double a21 = scanner.nextDouble(), a22 = scanner.nextDouble(), b2 = scanner.nextDouble();
        
        double D = a11 * a22 - a21 * a12;
        double D1 = b1 * a22 - b2 * a12;
        double D2 = a11 * b2 - a21 * b1;
        
        if (D == 0) {
            if (D1 == 0 && D2 == 0) {
                System.out.println("Infinite solutions");
            } else {
                System.out.println("No solution");
            }
        } else {
            System.out.println("Solution: x1 = " + (D1 / D) + ", x2 = " + (D2 / D));
        }
    }
    
    private static void solveQuadraticEquation(Scanner scanner) {
        System.out.println("Enter coefficients a, b, c for equation ax^2 + bx + c = 0:");
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        
        if (a == 0) {
            System.out.println("This is not a quadratic equation.");
            solveLinearEquation(scanner);
            return;
        }
        
        double delta = b * b - 4 * a * c;
        
        if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            System.out.println("Two distinct solutions: x1 = " + x1 + ", x2 = " + x2);
        } else if (delta == 0) {
            System.out.println("One double root: x = " + (-b / (2 * a)));
        } else {
            System.out.println("No real solution");
        }
    }
}
