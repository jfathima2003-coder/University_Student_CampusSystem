
public class ServiceQueue {

    private class Node {

        ServiceRequest request;
        Node next;

        Node(ServiceRequest request) {
            this.request = request;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;

    // Add request to Queue
    public void enqueue(ServiceRequest request) {

        if (request == null) {
            return;
        }

        Node newNode = new Node(request);

        if (rear == null) {

            front = newNode;
            rear = newNode;

        } else {

            rear.next = newNode;
            rear = newNode;
        }
    }

    // Remove and return the first request
    public ServiceRequest dequeue() {

        if (front == null) {
            return null;
        }

        ServiceRequest request = front.request;

        front = front.next;

        if (front == null) {
            rear = null;
        }

        return request;
    }

    // View the first request
    public ServiceRequest peek() {

        if (front == null) {
            return null;
        }

        return front.request;
    }

    // Check whether Queue is empty
    public boolean isEmpty() {

        return front == null;
    }

    // Display all requests
    public void displayQueue() {

        if (front == null) {

            System.out.println(
                    "No service requests found."
            );

            return;
        }

        Node current = front;

        System.out.println(
                "\n========== SERVICE REQUEST QUEUE =========="
        );

        while (current != null) {

            System.out.println(current.request);

            current = current.next;
        }

        System.out.println(
                "==========================================="
        );
    }
}

