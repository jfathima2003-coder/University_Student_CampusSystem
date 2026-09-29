public class StudentLinkedList {

    private class Node {

        Student student;
        Node next;

        Node(Student student) {
            this.student = student;
            this.next = null;
        }
    }

    private Node head;

    // Add Student
    public boolean addStudent(Student student) {

        if (student == null || student.getStudentId() == null) {
            return false;
        }

        if (searchStudent(student.getStudentId()) != null) {
            return false;
        }

        Node newNode = new Node(student);

        if (head == null) {
            head = newNode;
            return true;
        }

        Node current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;

        return true;
    }

    // Search Student
    public Student searchStudent(String studentId) {

        if (studentId == null) {
            return null;
        }

        Node current = head;

        while (current != null) {

            if (current.student.getStudentId().equals(studentId)) {
                return current.student;
            }

            current = current.next;
        }

        return null;
    }

    // Update Student
    public boolean updateStudent(
            String studentId,
            String name,
            String programme,
            double marks) {

        Student student = searchStudent(studentId);

        if (student == null) {
            return false;
        }

        student.setName(name);
        student.setProgramme(programme);
        student.setMarks(marks);

        return true;
    }

    // Delete Student
    public boolean deleteStudent(String studentId) {

        if (head == null) {
            return false;
        }

        if (head.student.getStudentId().equals(studentId)) {
            head = head.next;
            return true;
        }

        Node current = head;

        while (current.next != null) {

            if (current.next.student
                    .getStudentId()
                    .equals(studentId)) {

                current.next = current.next.next;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    // Display All Students
    public void displayStudents() {

        if (head == null) {
            System.out.println("No student records found.");
            return;
        }

        Node current = head;

        System.out.println("\n========== STUDENT RECORDS ==========");

        while (current != null) {

            System.out.println(current.student);

            current = current.next;
        }

        System.out.println("=====================================");
    }
}