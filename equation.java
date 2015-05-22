import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;
import java.lang.String;
import java.util.Scanner;
import java.io.PrintStream;

/**
 * Created by kirill on 22.05.2015.
 */
class Equation {

    private double discriminant;

    public void getSolve(double a, double b, double c) {
        System.out.format(solver(a, b, c));
    }

    public void getSolve(String infile) throws FileNotFoundException {

        File file = new File(infile);
        double a = 0;
        double b = 0;
        double c = 0;
        try {
            Scanner scanner = new Scanner(file);

            String inputData = scanner.nextLine();
            a = Double.parseDouble(inputData);
            inputData = scanner.nextLine();
            b = Double.parseDouble(inputData);
            inputData = scanner.nextLine();
            c = Double.parseDouble(inputData);

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        PrintStream out = new PrintStream("output.txt");

        out.print(solver(a, b, c));
        out.close();
    }

    /**
     * @return calculates discriminant if it's possible and returns case of root number for equation:
     * 0 - incorrect equation
     * 1 - infinite number of roots
     * 2 - linear equation
     * 3 - two real roots
     * 4 - one real root
     * 5 - two complex roots
     */
    private int calcD(double a, double b, double c) {
        if (a == 0 && b == 0 && c != 0) {
            return 0;                               //incorrect equation
        } else if (a == 0 && b == 0 && c == 0) {
            return 1;                               //infinite number of roots
        } else if (a == 0 && b != 0 && c != 0) {
            return 2;                               //linear equation
        } else {
            discriminant = b * b - 4 * a * c;
            if (discriminant > 0) {
                return 3;                           //two real roots
            } else if (discriminant == 0) {
                return 4;                           //one real root
            } else {    //if (discriminant < 0)
                return 5;                           //two complex roots
            }
        }
    }

    private String solver(double a, double b, double c) {
        String answer;
        switch (calcD(a, b, c)) {
            case 0: {  //incorrect equation
                answer = "incorrect equation:%n A and B can't be a zero at the same time if C is not a zero";
                break;
            }
            case 1: {  //infinite number of roots
                answer = "result is an infinite number of roots";
                break;
            }
            case 2: {   //linear equation one real root
                double root1 = -c / b;
                answer = "equation is linear. it has one real root " + root1;
                break;
            }
            case 3: {  //two real roots
                double root1 = (-b + Math.sqrt(discriminant)) / 2 * a;
                double root2 = (-b - Math.sqrt(discriminant)) / 2 * a;
                answer = "equation has two real roots " + root1 + " and " + root2;
                break;
            }
            case 4: {   //one real root
                double root1 = -b / 2 * a;
                answer = "equation has one real root " + root1;
                break;
            }
            case 5: {  //two complex roots
                double root_r = -b / 2 * a;
                double root_i = (-b - Math.sqrt(-discriminant)) / 2 * a;
                if (root_i > 0) {
                    answer = "equation has two complex roots " + root_r + " +i" + root_i + " and " + root_r + " -i" + root_i;
                } else {
                    answer = "equation has two complex roots " + root_r + " -i" + Math.abs(root_i) + " and " + root_r + " +i" + Math.abs(root_i);
                }
                break;
            }
            default: {
                answer = "discriminant calculation error";
            }
        }
        return answer;
    }


}




