package org.example;


public class Main {
    public static void main(String[] args) {
        if(args.length==1)
            System.out.println("Valid: "+JsonAWSVerifier.verifyAsterisk(args[0]));
        else
            System.out.println("Please specify path to json");
    }
}