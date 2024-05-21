package ds_project2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Mahmoud
 */
public class DS_Project2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean flag = true;
        ArrayList<PolynomialLinkedList> polynomials = new ArrayList<>();
        while (flag) {
            System.out.println("choose operation \n 1. Enter polynomial \n 2. add two polynomials \n 3. Subtract two polynomials \n 4. Multiplicat two polynomials \n 5. Display polynomial \n 6. Evaluate polynomial for a specific value of x \n 0.exit");
            int choice = input.nextInt();
            switch (choice) {
                case 0 -> {
                    flag = false;
                }
                case 1 -> {
                    System.out.println("Enter number of terms");
                    int termsNumber = input.nextInt();
                    int count = 1;
                    PolynomialLinkedList p1 = new PolynomialLinkedList();
                    while (count <= termsNumber) {
                        System.out.println("Enter coefficient for term " + count);
                        double coefficient = input.nextInt();
                        System.out.println("Enter exponent for term " + count);
                        double exponent = input.nextInt();
                        p1.addNode(coefficient, exponent);
                        count++;
                    }
                    polynomials.add(p1);
                }
                case 2 -> {
                    int size = polynomials.size();
                    try {
                        (polynomials.get(size - 2).add(polynomials.get(size - 1))).Display();
                    } catch (Exception e) {
                        System.out.println(" need atleast two polynomials to preform addition");
                    }
                }
                case 3 -> {
                    int size = polynomials.size();
                    try {
                        (polynomials.get(size - 2).subtract(polynomials.get(size - 1))).Display();
                    } catch (Exception e) {
                        System.out.println(" need atleast two polynomials to preform subtraction");
                    }
                }
                case 4 -> {
                    int size = polynomials.size();
                    try {
                        (polynomials.get(size - 2).multiply(polynomials.get(size - 1))).Display();
                    } catch (Exception e) {
                        System.out.println(" need atleast two polynomials to preform multiplication");
                    }
                }
                case 5 -> {
                    int size = polynomials.size();
                    try {
                        polynomials.get(size - 1).Display();
                    } catch (Exception e) {
                        System.out.println(" need to insert a polynomial First ");
                    }
                }
                case 6 -> {
                    int size = polynomials.size();
                    try {
                        System.out.println("Enter value of x");
                        int x = input.nextInt();
                        System.out.println(polynomials.get(size - 1).evaluate(x));
                    } catch (Exception e) {
                        System.out.println("need to insert a polynomial First ");
                    }
                }

            }
        }

    }

}
