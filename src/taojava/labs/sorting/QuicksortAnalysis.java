package taojava.labs.sorting;

import java.io.PrintWriter;

public class QuicksortAnalysis
{

  public static void main(String[] args)
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    @SuppressWarnings("unchecked")
    Sorter<Integer>[] sorters =
        (Sorter<Integer>[]) new Sorter[] {new NewQuicksorter<Integer>(), 
                                          new QuickSorterMedian<Integer>(),
                                          new QuickSorterMid<Integer>(),
                                          new QuickSorterRandom<Integer>()};
    String[] sorterNames = {"NewQuicksorter" ,"Median", 
                            "Middle Value", "Random"};

    @SuppressWarnings("unchecked")
    ArrayBuilder<Integer>[] builders =
        (ArrayBuilder<Integer>[]) new ArrayBuilder[] {
                                    SorterAnalyzer.randomIntArrBuilder,
                                    SorterAnalyzer.increasingIntArrBuilder, 
                                    SorterAnalyzer.mostlyOrderedIntArrBuilder,
                                    SorterAnalyzer.reverseIntArrBuilder};
    String[] builderNames = { "Random", "Increasing", 
                              "Mostly Ordered", "Reverse" };

    SorterAnalyzer.combinedAnalysis(pen, sorters, sorterNames,
                                    SorterAnalyzer.standardIntComparator,
                                    builders, builderNames);
  }//main
}// class QuicksortAnalysis

/* Out of the four different implementations we found that selecting the 
 * median of three randomly generated elements to be the most efficient 
 * method. Using the median element of the array was the least efficient
 * method. Our analysis shows that quicksort works poorest with a random
 * array, and none of the remaining array types show a statistically
 * significant difference in terms of efficiency. Specifically we found
 * that using the median as the pivot on a random array of size 80000 was
 * the least efficient.
 */
