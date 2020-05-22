package ca.jrvs.apps.practice;

import static java.util.Arrays.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamExcImp implements LambdaStreamExc {
    @Override
    public Stream<String> createStrStream(String... strings) {
        return stream(strings);
    }


    @Override
    public Stream<String> toUpperCase(String... strings) {
        return stream(strings).map(String::toUpperCase);
    }

    @Override
    public Stream<String> filter(Stream<String> stringStream, String pattern) {
        return stringStream.filter(string -> !string.contains(pattern));
    }

    @Override
    public IntStream createIntStream(int[] arr) {
        return stream(arr);
    }

    @Override
    public <E> List<E> toList(Stream<E> stream) {
        return stream.collect(Collectors.toList());
    }

    @Override
    public List<Integer> toList(IntStream intStream) {
        return intStream.boxed().collect(Collectors.toList());
    }

    @Override
    public IntStream createIntStream(int start, int end) {
        return IntStream.range(start, end);
    }

    @Override
    public DoubleStream squareRootIntStream(IntStream intStream) {
        return intStream.mapToDouble(Math::sqrt);
    }

    @Override
    public IntStream getOdd(IntStream intStream) {
        return intStream.filter(x -> x % 2 == 1);
    }

    @Override
    public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
        return (x -> System.out.println(prefix + x + suffix));
    }

    @Override
    public void printMessages(String[] messages, Consumer<String> printer) {
        stream(messages).forEach(printer::accept);
    }

    @Override
    public void printOdd(IntStream intStream, Consumer<String> printer) {
        printMessages(
                getOdd(intStream).mapToObj(String::valueOf).toArray(String[]::new), printer
        );
    }

    @Override
    public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
        return ints.flatMap(list -> list.stream().map(x -> x * x));
    }

    //public static void main(String[] args){
    //LambdaStreamExcImp lambdaStreamExc= new LambdaStreamExcImp();
    //System.out.println(lambdaStreamExc.filter(lambdaStreamExc.createStrStream("five"),"fi").toString());
    //System.out.println(lambdaStreamExc.getLambdaPrinter("Filtered out 'fi' ", "\n"));
    //lambdaStreamExc.printMessages(lambdaStreamExc.filter(lambdaStreamExc.createStrStream("five"),"fi"));
    // }
    public static void main(String[] args) {
        LambdaStreamExcImp lambdaStreamExcImp = new LambdaStreamExcImp();
        String[] strings = new String[]{"Farnaz", "Jarvis", "Edward"};

        lambdaStreamExcImp.printMessages(lambdaStreamExcImp.toUpperCase(strings).toArray(String[]::new), lambdaStreamExcImp.getLambdaPrinter("UpperCases are ", "\n"));

        lambdaStreamExcImp.printMessages(lambdaStreamExcImp.filter(lambdaStreamExcImp.createStrStream(strings), "F")
                .toArray(String[]::new), lambdaStreamExcImp.getLambdaPrinter("The ones without 'F':", "\n"));

        lambdaStreamExcImp.printMessages(lambdaStreamExcImp.squareRootIntStream(lambdaStreamExcImp.createIntStream(4, 9)).mapToObj(String::valueOf)
                .toArray(String[]::new), lambdaStreamExcImp.getLambdaPrinter("Square Root of numbers between 4 to 9 are :", "\n"));

        lambdaStreamExcImp.printMessages(lambdaStreamExcImp.getOdd(lambdaStreamExcImp.createIntStream(4, 9)).mapToObj(String::valueOf).toArray(String[]::new), lambdaStreamExcImp.getLambdaPrinter("odd numbers Between 4 to 9 are :", "\n"));

        lambdaStreamExcImp.printMessages(lambdaStreamExcImp.flatNestedInt(
                Stream.of(
                        Arrays.asList(4, 6, 9)
                )
                ).map(String::valueOf).toArray(String[]::new),
                lambdaStreamExcImp.getLambdaPrinter("Squared is:  ", "\n"));


    }
}
