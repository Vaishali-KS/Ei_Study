package creational.builder;

public class Director {
    private ComputerBuilder builder;

    public Director(ComputerBuilder builder) {
        this.builder = builder;
    }

    public void constructComputer() {
        builder.buildCPU();
        builder.buildRAM();
        builder.buildStorage();
    }

    public Computer getComputer() {
        return builder.getComputer();
    }
}
