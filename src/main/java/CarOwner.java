import java.util.Objects;

public class CarOwner {
    private int id;
    private String name;

    public CarOwner(String name, int id){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId(){
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarOwner carOwner = (CarOwner) o;
        return id == carOwner.id && Objects.equals(name, carOwner.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
