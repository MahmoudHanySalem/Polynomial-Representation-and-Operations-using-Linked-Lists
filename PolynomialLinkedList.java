package ds_project2;

/**
 *
 * @author Mahmoud
 */
public class PolynomialLinkedList {

    Node head;

    public PolynomialLinkedList() {//Initializes an empty polynomial linked list by setting the head to null
        this.head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        if (isEmpty()) {
            return 0;
        }
        Node current = head;
        int count = 0;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public void addNode(double Coefficient, double Exponent) {//Adds a new node to the linked list representing a term in the polynomial.
        if (Coefficient == 0) {
            return;
        }
        Node newNode = new Node(Coefficient, Exponent);
        if (isEmpty()) {
            head = newNode;
            return;
        }
        if (head.next == null) {
            if (Exponent > head.Exponent) {
                newNode.next = head;
                head = newNode;
                return;
            }
            if (Exponent == head.Exponent) {
                head.Coefficient += Coefficient;
                return;
            }
            head.next = newNode;
            return;
        }
        Node pre = head;
        if (Exponent > head.Exponent) {
            newNode.next = head;
            head = newNode;
            return;
        }
        while (pre != null) {
            if (Exponent == pre.Exponent) {
                pre.Coefficient += Coefficient;
                return;
            }
            pre = pre.next;
        }
        pre = head;
        while (pre.next != null) {
            if (Exponent > pre.next.Exponent) {
                newNode.next = pre.next;
                pre.next = newNode;
                return;
            }
            pre = pre.next;
        }
        pre.next = newNode;
    }

    public double[] remove() {//remove a term and return it's coefficient and exponent
        if (isEmpty()) {
            return new double[]{-1, -1};
        }
        Node secondLast = head;
        while (secondLast.next.next != null) {
            secondLast = secondLast.next;
        }
        double arr[] = {secondLast.next.Coefficient, secondLast.next.Exponent};
        secondLast.next = null;
        return arr;
    }

    public PolynomialLinkedList add(PolynomialLinkedList list) {//This method aims to add two polynomial linked lists
        PolynomialLinkedList sum = new PolynomialLinkedList();
        if (isEmpty() && list.isEmpty()) {
            return sum;
        }
        if (!isEmpty() && list.isEmpty()) {
            return this;
        }
        if (isEmpty() && !list.isEmpty()) {
            return list;
        }
        Node current1 = head;
        Node current2 = list.head;
        while (current1 != null || current2 != null) {
            if (current1 == null && current2 != null) {
                sum.addNode(current2.Coefficient, current2.Exponent);
                current2 = current2.next;
                continue;
            }
            if (current1 != null && current2 == null) {
                sum.addNode(current1.Coefficient, current1.Exponent);
                current1 = current1.next;
                continue;
            }
            if (current1.Exponent == current2.Exponent) {
                double Coefficient = current1.Coefficient + current2.Coefficient;
                sum.addNode(Coefficient, current1.Exponent);
                current1 = current1.next;
                current2 = current2.next;
            } else if (current1.Exponent > current2.Exponent) {
                sum.addNode(current1.Coefficient, current1.Exponent);
                current1 = current1.next;
            } else {
                sum.addNode(current2.Coefficient, current2.Exponent);
                current2 = current2.next;
            }
        }
        return sum;
    }

    public PolynomialLinkedList subtract(PolynomialLinkedList list) {//This method aims to subtract two polynomial linked lists
        PolynomialLinkedList result = new PolynomialLinkedList();
        if (isEmpty() && list.isEmpty()) {
            return result;
        }
        if (!isEmpty() && list.isEmpty()) {
            return this;
        }
        if (isEmpty() && !list.isEmpty()) {
            PolynomialLinkedList negativeList = new PolynomialLinkedList();
            Node curNode = list.head;
            while (curNode != null) {
                negativeList.addNode(-curNode.Coefficient, curNode.Exponent);
                curNode = curNode.next;
            }
            return negativeList;
        }
        Node current1 = head;
        Node current2 = list.head;
        while (current1 != null || current2 != null) {
            if (current1 == null && current2 != null) {
                result.addNode(-current2.Coefficient, current2.Exponent);
                current2 = current2.next;
                continue;
            }
            if (current1 != null && current2 == null) {
                result.addNode(current1.Coefficient, current1.Exponent);
                current1 = current1.next;
                continue;
            }
            if (current1.Exponent == current2.Exponent) {
                double Coefficient = current1.Coefficient - current2.Coefficient;
                result.addNode(Coefficient, current1.Exponent);
                current1 = current1.next;
                current2 = current2.next;
            } else if (current1.Exponent > current2.Exponent) {
                result.addNode(current1.Coefficient, current1.Exponent);
                current1 = current1.next;
            } else {
                result.addNode(-current2.Coefficient, current2.Exponent);
                current2 = current2.next;
            }
        }
        return result;
    }

    public PolynomialLinkedList multiply(PolynomialLinkedList list) {//This method multiplies two polynomial linked lists together.
        PolynomialLinkedList product = new PolynomialLinkedList();
        Node current1 = head;
        Node current2 = list.head;
        while (current1 != null) {
            while (current2 != null) {
                double coefficient = current1.Coefficient * current2.Coefficient;
                double exponent = current1.Exponent + current2.Exponent;
                product.addNode(coefficient, exponent);
                current2 = current2.next;
            }
            current2 = list.head;
            current1 = current1.next;
        }
        return product;
    }

    public double evaluate(double x) {//This method evaluates the polynomial at a given value of x.
        double evaluation = 0;
        Node currNode = head;
        while (currNode != null) {
            evaluation += (currNode.Coefficient) * (Math.pow(x, currNode.Exponent));
            currNode = currNode.next;
        }
        return evaluation;
    }

    public void Display() {//Displays the polynomial expression in a human-readable format.
        Node currNode = head;
        while (currNode != null) {
            double coefficient = currNode.Coefficient;
            double exponent = currNode.Exponent;
            double abs = Math.abs(coefficient);
            if (coefficient == 0) {
                currNode = currNode.next;
            } else {
                if (exponent == 0) {
                    if (currNode != head) {
                        System.out.print(abs);
                    } else {
                        System.out.print(coefficient);
                    }
                } else {
                    if (exponent == 1) {
                        if (coefficient != 1 && coefficient != -1) {
                            if (currNode != head) {
                                System.out.print(abs + "x");
                            } else {
                                System.out.print(coefficient + "x");
                            }
                        } else {
                            if (currNode != head) {
                                System.out.print("x");
                            } else {
                                if (coefficient == 1) {
                                    System.out.print("x");
                                } else {
                                    System.out.print("-x");
                                }

                            }
                        }
                    } else {
                        if (coefficient == 1 || coefficient == -1) {
                            if (currNode != head) {
                                System.out.print("x^" + exponent);
                            } else {
                                if (coefficient == 1) {
                                    System.out.print("x^" + exponent);
                                } else {
                                    System.out.print("-x^" + exponent);
                                }
                            }
                        } else {
                            if (currNode != head) {
                                System.out.print(abs + "x^" + exponent);
                            } else {
                                System.out.print(coefficient + "x^" + exponent);
                            }
                        }
                    }
                }
            }
            if (currNode.next != null && currNode.next.Coefficient != 0) {
                if (currNode.next.Coefficient > 0) {
                    System.out.print(" + ");
                } else {
                    System.out.print(" - ");
                }
            }
            currNode = currNode.next;
        }
        System.out.println("");
    }
}
