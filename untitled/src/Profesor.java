public class Profesor extends Human {

    private final String materie;

    public Profesor(String nume, String prenume, String facultate, int varsta, String materie) {
        super(nume,prenume,facultate,varsta);
        this.materie=materie;
    }

    public void greeting(){}
    public void doWork() {}

    @Override
    public String toString() {
        return "Prof. "+this.nume+" "+this.prenume+", Fac. "+this.facultate+
                ", Varsta "+this.varsta+", Materia "+this.materie;
    }

    public int compareTo(Profesor o) {
        return this.varsta-o.varsta;
    }
}
