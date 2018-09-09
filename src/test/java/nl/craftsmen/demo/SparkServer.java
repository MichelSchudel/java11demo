package nl.craftsmen.demo;

import static spark.Spark.get;

public class SparkServer {

    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");
    }
}
