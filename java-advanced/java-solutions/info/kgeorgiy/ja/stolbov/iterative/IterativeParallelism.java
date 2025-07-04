package info.kgeorgiy.ja.stolbov.iterative;

import info.kgeorgiy.java.advanced.iterative.ScalarIP;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * The IterativeParallelism class implements the processing of lists in several streams.
 * It provides methods for searching for indexes of maximum and minimum values,
 * as well as for searching for indexes of elements satisfying predicates.
 *
 * @author Specia1Forces
 */
public class IterativeParallelism implements ScalarIP {

    /**
     * The standard constructor
     */
    public IterativeParallelism() {
    }


    /**
     * Executes the task in multiple threads.
     *
     * @param threads number of threads to complete the task
     * @param values  list of values to be processed
     * @param task    a task that needs to be completed on subscriptions
     * @param <T>     the type of items in the list
     * @param <R>     task result type
     * @return list of results received from each thread
     * @throws InterruptedException if the current thread is interrupted
     */
    private <T, R> List<R> executeInParallel(int threads, List<T> values, Task<T, R> task) throws InterruptedException {
        List<R> results = new ArrayList<>(Collections.nCopies(threads, null));
        List<Thread> threadList = new ArrayList<>();

        int size = values.size() / threads;
        int modSize = values.size() % threads;

        int start = 0;
        for (int i = 0; i < threads; i++) {
            int end = start + size;

            if (modSize > 0) {
                end = start + size + 1;
                modSize--;
            }

            final int threadInd = i;
            final int startInd = start;
            final int endInd = end;
            Thread thread = new Thread(() -> {
                results.set(threadInd, task.process(values, startInd, endInd));
            });

            threadList.add(thread);
            start = end;
        }

        for (Thread thread : threadList) {
            thread.start();
        }

        for (Thread thread : threadList) {
            thread.join();
        }

        return results;

    }

    /**
     * Interface for a generalized task.
     *
     * @param <T> the type of items in the list
     * @param <R> task result type
     */
    @FunctionalInterface
    private interface Task<T, R> {
        /**
         * Processes a sublist of values.
         *
         * @param sublist sublist of values
         * @param start   sublist start index
         * @param end     index of the end of the sublist
         * @return result of processing subscription
         */
        R process(List<T> sublist, int start, int end);
    }

    /**
     * Finds the index of the first maximum value in the list.
     *
     * @param threads    number of threads to complete the task
     * @param values     list of values
     * @param comparator is a comparator for comparing values
     * @param <T>        the type of items in the list
     * @return the index of the first maximum value, or -1 if the list is empty
     * @throws InterruptedException if the current thread is interrupted
     */
    @Override
    public <T> int argMax(int threads, List<T> values, Comparator<? super T> comparator) throws InterruptedException {
        List<AbstractMap.SimpleEntry<Long, T>> results = executeInParallel(
                threads,
                values,
                (list, start, end) -> indexedStream(list, start, end)
                        .max(Map.Entry.comparingByValue(comparator))
                        .orElse(null)
        );

        return results
                .stream()
                .filter(Objects::nonNull)
                .max(Map.Entry.comparingByValue(comparator))
                .map(AbstractMap.SimpleEntry::getKey)
                .orElse(-1L).intValue();
    }

    /**
     * Finds the index of the first minimum value in the list.
     *
     * @param threads    number of threads to complete the task
     * @param values     list of values
     * @param comparator is a comparator for comparing values
     * @param <T>        the type of items in the list
     * @return the index of the first minimum value, or -1 if the list is empty
     * @throws InterruptedException if the current thread is interrupted
     */
    @Override
    public <T> int argMin(int threads, List<T> values, Comparator<? super T> comparator) throws InterruptedException {
        List<AbstractMap.SimpleEntry<Long, T>> results = executeInParallel(
                threads,
                values,
                (list, start, end) -> indexedStream(list, start, end)
                        .min(Map.Entry.comparingByValue(comparator))
                        .orElse(null)
        );

        return results
                .stream()
                .filter(Objects::nonNull)
                .min(Map.Entry.comparingByValue(comparator))
                .map(AbstractMap.SimpleEntry::getKey)
                .orElse(-1L).intValue();

    }

    /**
     * Finds the index of the first element satisfying the specified predicate.
     *
     * @param threads   number of threads to complete the task
     * @param values    list of values
     * @param predicate predicate for filtering values
     * @param <T>       the type of items in the list
     * @return the index of the first element satisfying the predicate, or -1 if there is no such element.
     * @throws InterruptedException if the current thread is interrupted
     */
    @Override
    public <T> int indexOf(int threads, List<T> values, Predicate<? super T> predicate) throws InterruptedException {
        List<AbstractMap.SimpleEntry<Long, T>> results = executeInParallel(
                threads,
                values,
                (list, start, end) -> indexedStream(list, start, end)
                        .filter(entry -> predicate.test(entry.getValue()))
                        .findFirst()
                        .orElse(null)
        );

        return results.stream()
                .filter(Objects::nonNull)
                .findFirst()
                .map(AbstractMap.SimpleEntry::getKey)
                .orElse(-1L).intValue();

    }

    /**
     * Finds the index of the last element satisfying the specified predicate.
     *
     * @param threads   number of threads to complete the task
     * @param values    list of values
     * @param predicate predicate for filtering values
     * @param <T>       the type of items in the list
     * @return the index of the last element satisfying the predicate, or -1 if there is no such element.
     * @throws InterruptedException if the current thread is interrupted
     */
    @Override
    public <T> int lastIndexOf(int threads, List<T> values, Predicate<? super T> predicate) throws InterruptedException {

        List<AbstractMap.SimpleEntry<Long, T>> results = executeInParallel(
                threads,
                values,
                (list, start, end) -> indexedStream(list, start, end)
                        .filter(entry -> predicate.test(entry.getValue()))
                        .reduce((a, b) -> b)
                        .orElse(null)
        );


        return results
                .stream()
                .filter(Objects::nonNull)
                .reduce((a, b) -> b)
                .map(AbstractMap.SimpleEntry::getKey)
                .orElse(-1L).intValue();

    }


    /**
     * Summarizes the indexes of the elements satisfying the specified predicate.
     *
     * @param threads   number of threads to complete the task
     * @param values    list of values
     * @param predicate a predicate for filtering values
     * @param <T>       the type of items in the list
     * @return is the sum of the indexes of the elements satisfying the predicate
     * @throws InterruptedException if the current thread is interrupted
     */
    @Override
    public <T> long sumIndices(int threads, List<? extends T> values, Predicate<? super T> predicate) throws InterruptedException {

        List<Long> results = executeInParallel(
                threads,
                values,
                (list, start, end) -> indexedStream(list, start, end)
                        .filter(entry -> predicate.test(entry.getValue()))
                        .map(AbstractMap.SimpleEntry::getKey)
                        .reduce(0L, Long::sum)
        );


        return results.stream().reduce(0L, (Long::sum));
    }

    /**
     * Creates a stream of index-value pairs from the specified sublist.
     *
     * @param list  the list of elements to process.
     * @param start the starting index (inclusive) of the sublist.
     * @param end   the ending index (exclusive) of the sublist.
     * @param <T>   the type of elements in the list.
     * @return a {@code Stream} of {@code AbstractMap.SimpleEntry<Integer, T>} representing
     * the index and value pairs within the specified range.
     */
    private <T> Stream<AbstractMap.SimpleEntry<Long, T>> indexedStream(List<T> list, int start, int end) {
        return LongStream.range(start, end)
                .mapToObj(i -> new AbstractMap.SimpleEntry<>(i, list.get((int) i)));
    }
}