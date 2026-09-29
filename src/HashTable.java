public class HashTable {

    private static final int TABLE_SIZE = 10;

    private class Entry {

        String studentId;
        Student student;
        Entry next;

        Entry(Student student) {
            this.student = student;
            this.studentId = student.getStudentId();
            this.next = null;
        }
    }

    private final Entry[] table;

    public HashTable() {
        table = new Entry[TABLE_SIZE];
    }

    private int hash(String studentId) {

        int hashValue = 0;

        for (int i = 0; i < studentId.length(); i++) {
            hashValue = 31 * hashValue + studentId.charAt(i);
        }

        return Math.abs(hashValue % TABLE_SIZE);
    }

    public boolean insert(Student student) {

        if (student == null) {
            return false;
        }

        String studentId = student.getStudentId();
        int index = hash(studentId);

        Entry current = table[index];

        while (current != null) {

            if (current.studentId.equals(studentId)) {
                return false;
            }

            current = current.next;
        }

        Entry newEntry = new Entry(student);

        newEntry.next = table[index];
        table[index] = newEntry;

        return true;
    }

    public Student search(String studentId) {

        if (studentId == null || studentId.isEmpty()) {
            return null;
        }

        int index = hash(studentId);

        Entry current = table[index];

        while (current != null) {

            if (current.studentId.equals(studentId)) {
                return current.student;
            }

            current = current.next;
        }

        return null;
    }

    public boolean delete(String studentId) {

        if (studentId == null || studentId.isEmpty()) {
            return false;
        }

        int index = hash(studentId);

        Entry current = table[index];
        Entry previous = null;

        while (current != null) {

            if (current.studentId.equals(studentId)) {

                if (previous == null) {
                    table[index] = current.next;
                } else {
                    previous.next = current.next;
                }

                return true;
            }

            previous = current;
            current = current.next;
        }

        return false;
    }

    public boolean update(Student student) {

        if (student == null) {
            return false;
        }

        String studentId = student.getStudentId();
        int index = hash(studentId);

        Entry current = table[index];

        while (current != null) {

            if (current.studentId.equals(studentId)) {

                current.student = student;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public void displayTable() {

        System.out.println("\n========== HASH TABLE ==========");

        for (int i = 0; i < TABLE_SIZE; i++) {

            System.out.print("Index " + i + ": ");

            Entry current = table[i];

            if (current == null) {
                System.out.println("Empty");
                continue;
            }

            while (current != null) {

                System.out.print(current.studentId);

                if (current.next != null) {
                    System.out.print(" -> ");
                }

                current = current.next;
            }

            System.out.println();
        }

        System.out.println("================================");
    }
}