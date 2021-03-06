package taojava.labs.sorting;

import java.io.PrintWriter;

import java.util.Comparator;
import java.util.Random;

/**
 * Tools for analyzing sorters.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 */
public class SorterAnalyzer
{
  // +---------------+---------------------------------------------------
  // | Configuration |
  // +---------------+

  /**
   * The number of repetitions we do in gathering statistics.
   */
  static final int REPETITIONS = 20;

  /**
   * The smallest array size we use.
   */
  static final int SMALLEST = 10000;

  /**
   * The largest array size we use.
   */
  static final int LARGEST = 80000;

  /**
   * The amount we scale the array size between tests.
   */
  static final int SCALE = 2;

  // +-----------+-------------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * A comparator for integers.
   */
  public static final Comparator<Integer> standardIntComparator =
      (left, right) -> left.compareTo(right);

  /**
   * Build arrays of random integer values.
   */
  public static final ArrayBuilder<Integer> randomIntArrBuilder = (length) ->
    {
      Integer[] vals = new Integer[length];
      Random random = new Random();
      for (int i = 0; i < length; i++)
        vals[i] = random.nextInt(length);
      return vals;
    }; // randomIArrayBuilder

  /** 
   * Build arrays of integer values in increasing order.
   */
  public static final ArrayBuilder<Integer> increasingIntArrBuilder =
      (length) ->
        {
          Integer[] vals = new Integer[length];
          for (int i = 0; i < length; i++)
            vals[i] = i;
          return vals;
        };

  public static final ArrayBuilder<Integer> mostlyOrderedIntArrBuilder =
      (length) ->
        {
          Integer[] vals = new Integer[length];
          for (int i = 0; i < length; i++)
            {
              vals[i] = i;
            }
          for (int i = 0; i < length; i += 10)
            {
              int tmp = vals[i];
              vals[i] = vals[i + 1];
              vals[i + 1] = tmp;
            }
          return vals;
        };
  public static final ArrayBuilder<Integer> reverseIntArrBuilder = (length) ->
    {
      Integer[] vals = new Integer[length];
      for (int i = 0; i < length; i++)
        {
          vals[i] = length - i;
        }

      return vals;
    };

  // +--------------+----------------------------------------------------
  // | Class Fields |
  // +--------------+

  // +---------------+---------------------------------------------------
  // | Class Methods |
  // +---------------+

  /**
   * Determine the amount of time sorter takes to sort an array of
   * the given size created by builder.
   *
   * @param sorter
   *   The sorting routine we are testing.
   * @param builder
   *   The constructor for the array we will sort.
   * @param order
   *   The comparator we use in sorting.
   * @param size
   *   The size of the array that we sort.
   *   
   * @return the number of milliseconds that sorting took, or 
   *   Long.MAX_VALUE if the sorter breaks.
   */
  public static <T> long basicAnalysis(Sorter<T> sorter, Comparator<T> order,
                                       ArrayBuilder<T> builder, int size)
  {
    // Prepare for timing
    SimpleTimer timer = new SimpleTimer();

    // Build the array.
    T[] values = builder.build(size);

    // Run the garbage collector so that garbage collection
    // is less likely to be counted as part of the time for
    // sorting.
    gc();

    // Start the timer.  (Duh.)
    timer.start();

    // Do the real work.
    try
      {
        sorter.sort(values, order);
      } // try            
    catch (Throwable error)
      {
        // Sorting failed with some error.  Return -1 to
        // indicate failure.
        return Long.MAX_VALUE;
      } // catch

    // Stop the timer.
    timer.pause();

    // And report the time taken
    return timer.elapsed();
  } // basicAnalysis(Sorter<T>, ArrayBuilder<T>, int)

  /**
   * Repeatedly perform basic analysis and gather statistics
   * (e.g., minimum time, maximum time, average time.
   * 
   * @param sorter: A Sorter of type T
   * @param order: a comparator
   * @param builder: an Array builder of type T
   * @param size: int
   * @param repetitions: how many times the new long
   * @return: new array
   */
   
  public static <T> long[] compoundAnalysis(Sorter<T> sorter,
                                            Comparator<T> order,
                                            ArrayBuilder<T> builder, int size,
                                            int repetitions)
  {
    long min = (long) basicAnalysis(sorter, order, builder, size);
    long max = min;
    long sum = min;
    long[] results = new long[repetitions];
    long approx = 0;
    long remainder = 0;

    for (int i = 0; i < repetitions; i++)
      {
        int result = (int) basicAnalysis(sorter, order, builder, size);
        results[i] = result;
        if (i == 0)
          {
            min = result;
          }//if
        else if (result < min)
          {
            min = result;
          }//else if
        if (result > max)
          {
            max = result;
          }//if 
        sum += (result / repetitions);
        remainder += (result % repetitions);
      } // for

    long avg = remainder / repetitions;
    remainder = remainder % repetitions;

    if (Utils.sign(avg) == -(Utils.sign(remainder)))
      avg += Utils.sign(remainder);

    return new long[] { min, max, avg };
  }// compoundAnalysis(Sorter<T>, ArrayBuilder<T>, int, int)

  /**
   * Given a variety of sorters and builders, does some analysis
   * of each sorter/builder pair using a variety of array sizes
   * and prints out the results.
   *
   * @param pen
   *   Where to send the output
   * @param sorters
   *   The objects that do the sorting
   * @param sorterNames
   *   The names of those sorters
   * @param builders
   *   The objects to build the arrays
   * @param builderNames
   *    The names of those builders
   */
  public static <T> void combinedAnalysis(PrintWriter pen, Sorter<T>[] sorters,
                                          String[] sorterNames,
                                          Comparator<T> order,
                                          ArrayBuilder<T> builders[],
                                          String[] builderNames)
  {
    pen.printf("%-16s%-16s%-16s%-16s%-16s         %-16s\n",
               "Sorter", "Builder", "Input Size",
               "Min Time", "Max Time", "Average Time");
    pen.printf("%-16s%-16s%-16s%-16s%-16s         %-16s\n",
               "------", "-------", "------------",
               "------------", "------------", "------------");
    for (int a = 0; a < sorters.length; a++)
      {
        for (int b = 0; b < builders.length; b++)
          {
            for (int size = SMALLEST; size <= LARGEST; size *= SCALE)
              {
                long[] stats =
                    compoundAnalysis(sorters[a], order, builders[b], size,
                                     REPETITIONS);
                pen.printf("%-16s%-16s%12d    %12d     %12d     %12d\n",
                           sorterNames[a],
                           builderNames[b], size, stats[0], stats[1], 
                           stats[2]);
              } // for size
          } // for builder : builders
      }// for
  } // combinedAnalysis(PrintWRiter, Sorter<T>, String[], ...)

  /**
   * Force garbage collection to the best of our ability.
   */
  public static void gc()
  {
    // Right now, we use the quick and dirty "suggest garbage
    // collection".  Over the long term, we will probably try
    // something like "get the pid and execute 'jcmd <pid> GC.run'"
    // The pid *might* be in the environment.
    System.gc();
  } // gc()
} // class SorterAnalyzer
