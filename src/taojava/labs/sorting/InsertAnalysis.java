package taojava.labs.sorting;
//Runs slowly so give it time.
import java.io.PrintWriter;

public class InsertAnalysis
{

  public static void main(String[] args)
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    @SuppressWarnings("unchecked")
    Sorter<Integer>[] sorters =
        (Sorter<Integer>[]) new Sorter[] {
                                       new InsertionSorter<Integer>(),
                                       new InsertionSortInLineSwap<Integer>(),
                                       new InsertionSortShift<Integer>()};
    String[] sorterNames = { "InsertionSort", "In Line Swap", "Shifting",};
   

    @SuppressWarnings("unchecked")
    ArrayBuilder<Integer>[] builders =
        (ArrayBuilder<Integer>[]) new ArrayBuilder[] {
                                    SorterAnalyzer.randomIntArrBuilder,
                                    SorterAnalyzer.increasingIntArrBuilder,
                                    SorterAnalyzer.mostlyOrderedIntArrBuilder,
                                    SorterAnalyzer.reverseIntArrBuilder };
    String[] builderNames =
        { "Random", "Increasing", "Mostly Ordered", "Reverse" };

    SorterAnalyzer.combinedAnalysis(pen, sorters, sorterNames,
                                    SorterAnalyzer.standardIntComparator,
                                    builders, builderNames);
  }//main
}//class InsertAnalysis
/* We found that the shifting was far more efficient than In Line Swap and 
 * Insertion Sort. This makes sense because the latter two essentially use 
 * the same code. The only difference is that In Line Swap has its own swap
 * in the code whereas Insertion Sort calls a swap function. All three worked
 * best with an increasing array and worst with reverse. This is because an 
 * increasing array is already in order and we only need to make comparisons. 
 * In a reverse array we have to perform swaps on every element in the array.
 */
