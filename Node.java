package ds_project2;

/**
 *
 * @author Mahmoud
 */
public class Node {//Represents a node in a polynomial linked list.

    double Coefficient;
    double Exponent;
    Node next;

    public Node(double Coefficient, double Exponent) {
        this.Coefficient = Coefficient;
        this.Exponent = Exponent;
        this.next = null;
    }

    public double getCoefficient() {
        return Coefficient;
    }

    public double getExponent() {
        return Exponent;
    }

}
