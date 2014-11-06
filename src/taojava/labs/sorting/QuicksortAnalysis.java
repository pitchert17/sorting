package taojava.labs.sorting;

import java.io.PrintWriter;

public class QuicksortAnalysis
{

  public static void main(String[] args)
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    @SuppressWarnings("unchecked")
    Sorter<Integer>[] sorters =
        (Sorter<Integer>[]) new Sorter[] { new Quicksorter<Integer>(),
                                          new NewQuicksorter<Integer>(), 
                                          new QuickSorterMedian<Integer>(),
                                          new QuickSorterMid<Integer>(),
                                          new QuickSorterRandom<Integer>()};
    String[] sorterNames = { "Quicksorter", "NewQuicksorter" ,"Median", "Middle Value", "Random"};

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
}// class QuicksortAnalysis
