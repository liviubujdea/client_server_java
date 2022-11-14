public class Human implements IHuman, Comparable<Human> {

    protected String nume;
    protected String prenume;
    protected String facultate;

    protected int varsta;

    public Human(String nume, String prenume, String facultate, int varsta) {
        this.nume = nume;
        this.prenume = prenume;
        this.facultate = facultate;
        this.varsta = varsta;
    }

    @Override
    public void greeting() {;}
    @Override
    public void doWork() {;}

    @Override
    public int compareTo(Human o) {
        return this.varsta-o.varsta;
    }
    @Override
    public String toString() {
        return "Nume "+this.nume+" "+this.prenume+", Fac. "+this.facultate+
                ", Varsta "+this.varsta;
    }
}
