package nl.craftsmen.demo;

public class MultiplierProgram {

    public static void main(String[] args) {
        Multiplier multiplier = new Multiplier();
        System.out.println(args[0] + " multiplied by itself yields " + multiplier.multiply(Integer.valueOf(args[0])));
    }


    static class Multiplier {

        int multiply(int x) {
            return x * x;
        }
    }
}
