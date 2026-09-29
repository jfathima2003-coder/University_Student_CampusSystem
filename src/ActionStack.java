public class ActionStack {

    private class Node {
        String action;
        Node next;

        Node(String action) {
            this.action = action;
            this.next = null;
        }
    }

    private Node top;

    // Push a new action
    public void push(String action) {

        Node newNode = new Node(action);

        newNode.next = top;
        top = newNode;
    }

    // Pop the most recent action
    public String pop() {

        if (top == null) {
            return null;
        }

        String action = top.action;
        top = top.next;

        return action;
    }

    // Peek at the most recent action
    public String peek() {

        if (top == null) {
            return null;
        }

        return top.action;
    }

    // Check whether stack is empty
    public boolean isEmpty() {

        return top == null;
    }

    // Display all recent actions
    public void displayActions() {

        if (top == null) {
            System.out.println("No recent actions found.");
            return;
        }

        Node current = top;

        System.out.println("\n========== RECENT ACTIONS ==========");

        while (current != null) {

            System.out.println("- " + current.action);

            current = current.next;
        }

        System.out.println("=====================================");
    }
}