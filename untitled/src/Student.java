public class Student extends Human{

    private final int an;

    public Student(String nume, String prenume, String facultate, int varsta, int an) {
        super(nume,prenume,facultate,varsta);
        this.an=an;

    }

    public void greeting(){}
    public void doWork() {}

    @Override
    public String toString() {
        return "Stud. "+this.nume+" "+this.prenume+", An "+this.an+", Fac. "+this.facultate+
                ", Varsta "+this.varsta;
    }

    public int compareTo(Student o) {
        return this.varsta-o.varsta;
    }
}
