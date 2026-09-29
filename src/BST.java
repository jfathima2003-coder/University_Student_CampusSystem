
public class BST {

    private class Node {

        Student student;
        Node left;
        Node right;

        Node(Student student) {
            this.student = student;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public boolean insert(Student student) {

        if (student == null) {
            return false;
        }

        if (root == null) {
            root = new Node(student);
            return true;
        }

        return insertNode(root, student);
    }

    private boolean insertNode(Node current, Student student) {

        int comparison = student.getStudentId()
                .compareTo(current.student.getStudentId());

        if (comparison == 0) {
            return false;
        }

        if (comparison < 0) {

            if (current.left == null) {
                current.left = new Node(student);
                return true;
            }

            return insertNode(current.left, student);

        } else {

            if (current.right == null) {
                current.right = new Node(student);
                return true;
            }

            return insertNode(current.right, student);
        }
    }

    public Student search(String studentId) {

        if (studentId == null) {
            return null;
        }

        Node current = root;

        while (current != null) {

            int comparison = studentId.compareTo(
                    current.student.getStudentId()
            );

            if (comparison == 0) {
                return current.student;
            }

            if (comparison < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public boolean delete(String studentId) {

        if (studentId == null) {
            return false;
        }

        if (search(studentId) == null) {
            return false;
        }

        root = deleteNode(root, studentId);

        return true;
    }

    private Node deleteNode(Node current, String studentId) {

        if (current == null) {
            return null;
        }

        int comparison = studentId.compareTo(
                current.student.getStudentId()
        );

        if (comparison < 0) {

            current.left = deleteNode(
                    current.left,
                    studentId
            );

        } else if (comparison > 0) {

            current.right = deleteNode(
                    current.right,
                    studentId
            );

        } else {

            if (current.left == null
                    && current.right == null) {

                return null;
            }

            if (current.left == null) {
                return current.right;
            }

            if (current.right == null) {
                return current.left;
            }

            Node successor = findMinimum(current.right);

            current.student = successor.student;

            current.right = deleteNode(
                    current.right,
                    successor.student.getStudentId()
            );
        }

        return current;
    }

    private Node findMinimum(Node node) {

        Node current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    public void displayInOrder() {

        if (root == null) {

            System.out.println(
                    "No students found in BST."
            );

            return;
        }

        System.out.println(
                "\nStudents sorted by Student ID:"
        );

        inOrder(root);
    }

    private void inOrder(Node current) {

        if (current == null) {
            return;
        }

        inOrder(current.left);

        System.out.println(current.student);

        inOrder(current.right);
    }
}

