package utils;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.stream.*;
import java.util.stream.IntStream.Builder;

public class Utils {
    // the first 1_000_000 primes <= 15485863
    private static final Path PRIMES_PATH = Paths.get("src", "main", "docs",
            "primes1.txt");
    private static TreeSet<Long> primes;

    static {
        try (Stream<String> lines = Files.lines(PRIMES_PATH)) {
            primes = Arrays
                    .stream(lines.map(l -> l.trim()).filter(l -> !l.isEmpty()).skip(1)
                            .collect(Collectors.joining(" ")).split("\\s+"))
                    .mapToLong(Long::valueOf).boxed()
                    .collect(Collectors.toCollection(TreeSet::new));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ;

    public static LongStream getPrimesFromFile(LongPredicate filterCondition)
            throws IOException {
        try (Stream<String> lines = Files.lines(PRIMES_PATH)) {
            return Arrays
                    .stream(lines.map(l -> l.trim()).filter(l -> !l.isEmpty()).skip(1)
                            .collect(Collectors.joining(" ")).split("\\s+"))
                    .mapToLong(Long::valueOf).filter(filterCondition);
        }
    }

    public static Stream<Long> getPrimes(Predicate<Long> filterCondition) {
        return primes.stream().filter(filterCondition);
    }

    public static Boolean isPrime(long candidate) {
        Predicate<Long> identical = prime -> prime == Math.abs(candidate);
        return getPrimes(identical).count() == 1;
    }

    public static Boolean allPrimeErastothenes(List<Long> candidates) {
        return candidates.stream().allMatch(Utils::isPrimeErastothenes);
    }

    public static Boolean isPrimeErastothenes(long candidate) {
        return candidate > 1
                && LongStream.rangeClosed(2, (long) Math.sqrt(candidate))
                .noneMatch(divisor -> candidate % divisor == 0);
    }

    public static Boolean isPrimeErastothenes(String candidate) {
        return isPrime(Long.parseLong(candidate));
    }

    public static boolean isPandigital(Long candidate) {
        String numbers = "123456789";
        String candidateString = candidate.toString();
        int candidateLength = candidateString.length();
        return candidateLength <= numbers.length()
                && candidateString.chars().sorted().boxed()
                .map(ch -> String.valueOf(Character.getNumericValue(ch)))
                .collect(Collectors.joining())
                .equals(numbers.substring(0, candidateLength));
    }

    public static int[] readNumberTriangle(Path path) throws IOException {
        try (Stream<String> lines = Files.lines(path)) {
            return Arrays.stream(lines.map(l -> l.trim())
                    .collect(Collectors.joining(" ")).split("\\s+"))
                    .mapToInt(Integer::valueOf).toArray();
            // .peek(i -> System.out.println(i))
        }
    }

    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static long gcdBinary(long a, long b) {
        if (a == b) {
            return a;
        }
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        if (a % 2 == 0 && b % 2 == 0) {
            return 2 * gcdBinary(a / 2, b / 2);
        }
        if (a % 2 == 0) {
            return gcdBinary(a / 2, b);
        } else if (b % 2 == 0) {
            return gcdBinary(a, b / 2);
        }
        if (a > b) {
            return gcdBinary((a - b) / 2, b);
        }
        return gcdBinary((b - a) / 2, a);
    }

    public static void timeGcdImplementations(long maxValue) {
        Instant start = Instant.now();

        LongStream.rangeClosed(2, maxValue).boxed().map(a -> LongStream.range(1, a).map(b -> gcd(a, b)));
        System.out.println("gcd       " + Duration.between(start, start = Instant.now()));

        LongStream.rangeClosed(2, maxValue).boxed().map(a -> LongStream.range(1, a).map(b -> gcdBinary(a, b)));
        System.out.println("gcdBinary " + Duration.between(start, Instant.now()));

    }

    public static BigInteger factorial(int n) {
        BigInteger result = Stream
                .iterate(BigInteger.ONE, bi -> bi.add(BigInteger.ONE)).limit(n)
                .reduce(BigInteger.ONE, (bi1, bi2) -> bi1.multiply(bi2));
        return result;
    }

    public static IntStream factorize(Integer n) {
        Builder builder = IntStream.builder().add(1);
        if (isPrime(Long.valueOf(n))) {
            return builder.add(n).build().sorted();
        }
        int max = (int) Math.floor(Math.sqrt(n));
        for (int i = 2; i <= max; i++) {
            if (n % i == 0) {
                builder.add(i);
                int div = n / i;
                if (div != i) {
                    builder.add(n / i);
                }
            }
        }
        return builder.build().sorted();
    }

    public static LongStream primeFactors(Long n) {
        java.util.stream.LongStream.Builder builder = LongStream.builder();
        if (isPrime(n)) {
            return builder.add(n).build();
        }
        long max = n / 2;
        List<Long> primeCandidates = getPrimes(p -> p <= max)
                .collect(Collectors.toList());
        for (Long prime : primeCandidates) {
            while (n % prime == 0) {
                builder.add(prime);
                n = n / prime;
            }
        }
        return builder.build().sorted();
    }

    public static boolean isPalindrome(String candidate) {
        List<String> asList = Arrays
                .asList(candidate.substring(0, candidate.length() / 2).split(""));
        Collections.reverse(asList);
        return candidate.endsWith(String.join("", asList));
    }

    public static boolean isPalindrome(int product) {
        return isPalindrome(String.valueOf(product));
    }

    public static void main(String[] args) throws IOException {
        timePrimeMethods();
    }

    private static void timePrimeMethods() throws IOException {
        for (long i = 1; i < 100_000; i++) {
            if (isPandigital(i)) {
                System.out.println(i);
            }
        }
        System.out.println();

        LongPredicate pred = p -> true;
        Predicate<Long> pre = p -> true;

        Instant start = Instant.now();

        getPrimesFromFile(pred).limit(10_000);

        System.out.println(
                "getPrimesFromFile" + Duration.between(start, start = Instant.now()));

        getPrimes(pre).limit(10_000);
        System.out.println("getPrimes" + Duration.between(start, Instant.now()));
    }

    /**
     * stolen from stack-overflow
     */
    public static <A, B, C> Stream<C> zip(Stream<? extends A> a,
                                          Stream<? extends B> b,
                                          BiFunction<? super A, ? super B, ? extends C> zipper) {
        Objects.requireNonNull(zipper);
        @SuppressWarnings("unchecked")
        Spliterator<A> aSpliterator = (Spliterator<A>) Objects.requireNonNull(a)
                .spliterator();
        @SuppressWarnings("unchecked")
        Spliterator<B> bSpliterator = (Spliterator<B>) Objects.requireNonNull(b)
                .spliterator();

        // Zipping looses DISTINCT and SORTED characteristics
        int both = aSpliterator.characteristics() & bSpliterator.characteristics()
                & ~(Spliterator.DISTINCT | Spliterator.SORTED);
        int characteristics = both;

        long zipSize = ((characteristics & Spliterator.SIZED) != 0)
                ? Math.min(aSpliterator.getExactSizeIfKnown(),
                bSpliterator.getExactSizeIfKnown())
                : -1;

        Iterator<A> aIterator = Spliterators.iterator(aSpliterator);
        Iterator<B> bIterator = Spliterators.iterator(bSpliterator);
        Iterator<C> cIterator = new Iterator<C>() {
            @Override
            public boolean hasNext() {
                return aIterator.hasNext() && bIterator.hasNext();
            }

            @Override
            public C next() {
                return zipper.apply(aIterator.next(), bIterator.next());
            }
        };

        Spliterator<C> split = Spliterators.spliterator(cIterator, zipSize,
                characteristics);
        return (a.isParallel() || b.isParallel())
                ? StreamSupport.stream(split, true)
                : StreamSupport.stream(split, false);
    }

    public static BigInteger sumOfDigits(int target) {
        return sumOfDigits(BigInteger.valueOf(target));
    }

    public static BigInteger sumOfDigits(long target) {
        return sumOfDigits(BigInteger.valueOf(target));
    }

    public static BigInteger sumOfDigits(BigInteger target) {
        return sumOfDigits(target, BigInteger.ZERO);
    }

    private static BigInteger sumOfDigits(BigInteger target, BigInteger accumulator) {
        if (target == BigInteger.ZERO) {
            return accumulator;
        }
        BigInteger[] parts = target.divideAndRemainder(BigInteger.TEN);
        return sumOfDigits(parts[0], accumulator.add(parts[1]));
    }

    public static long phi(long n)
    {
        // Initialize result as n
        long result = n;

        // Consider all prime factors
        // of n and subtract their
        // multiples from result
        for (long p = 2; p * p <= n; ++p)
        {

            // Check if p is
            // a prime factor.
            if (n % p == 0)
            {

                // If yes, then update
                // n and result
                while (n % p == 0)
                    n /= p;
                result -= result / p;
            }
        }

        // If n has a prime factor
        // greater than sqrt(n)
        // (There can be at-most
        // one such prime factor)
        if (n > 1)
            result -= result / n;
        return result;
    }

    public static String tagAsOrderedString(long target) {
        return String.valueOf(target).chars().sorted().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining());
    }
}
