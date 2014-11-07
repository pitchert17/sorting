package taojava.labs.sorting;

import java.io.PrintWriter;

public class MergeAnalysis
{

  public static void main(String[] args)
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    @SuppressWarnings("unchecked")
    Sorter<Integer>[] sorters =
        (Sorter<Integer>[]) new Sorter[] { new MergeSorter<Integer>(),
                                          new MergeSorterB<Integer>(),
                                          new IterativeMergeSorter<Integer>()};
    String[] sorterNames = { "MergeSorter", "MergeSorterB", 
                             "Iterative" };

    @SuppressWarnings("unchecked")
    ArrayBuilder<Integer>[] builders =
        (ArrayBuilder<Integer>[]) new ArrayBuilder[] {
                                     SorterAnalyzer.randomIntArrBuilder,
                                     SorterAnalyzer.increasingIntArrBuilder,
                                     SorterAnalyzer.mostlyOrderedIntArrBuilder,
                                     SorterAnalyzer.reverseIntArrBuilder};
    String[] builderNames = { "Random", "Increasing", "Mostly Ordered", 
                              "Reverse" };

    SorterAnalyzer.combinedAnalysis(pen, sorters, sorterNames,
                                    SorterAnalyzer.standardIntComparator,
                                    builders, builderNames);
  }//main
}//class MergeAnalysis
/*We observed that MergeSorterB was the most efficient with Iterative as a 
 *close second. Of the array types we obseved that MergeSorterB performs 
 *worst with a random array and found no significant difference with the
 *remaining array types. Iterative has not clear best or worst array type. 
 *We did find that it performed similarly to MergeSorterB for arrays of size
 *10000, 20000, and 40000. However, with arrays of size 80000 Iterative 
 *performed far worse than MergeSorterB. 
 * 
 */
