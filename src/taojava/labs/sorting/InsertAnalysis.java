package taojava.labs.sorting;

import java.io.PrintWriter;

public class InsertAnalysis
{

  public static void main(String[] args)
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    @SuppressWarnings("unchecked")
    Sorter<Integer>[] sorters =
        (Sorter<Integer>[]) new Sorter[] { new InsertionSorter<Integer>(),
                                          new InsertionSortInLineSwap<Integer>(),
                                          new InsertionSortShift<Integer>()};
    String[] sorterNames = { "InsertionSort", "In Line Swap", "Shifting" };

    @SuppressWarnings("unchecked")
    ArrayBuilder<Integer>[] builders =
        (ArrayBuilder<Integer>[]) new ArrayBuilder[] {
                                                      SorterAnalyzer.randomIntArrBuilder,
                                                      SorterAnalyzer.increasingIntArrBuilder,
                                                      SorterAnalyzer.mostlyOrderedIntArrBuilder,
                                                      SorterAnalyzer.reverseIntArrBuilder};
    String[] builderNames = { "Random", "Increasing", "Mostly Ordered", "Reverse" };

    SorterAnalyzer.combinedAnalysis(pen, sorters, sorterNames,
                                    SorterAnalyzer.standardIntComparator,
                                    builders, builderNames);
  }//main
}//class InsertAnalysis
