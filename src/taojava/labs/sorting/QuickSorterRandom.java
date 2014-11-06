package taojava.labs.sorting;

import java.util.Comparator;
import java.util.Random;

public class QuickSorterRandom<T>
  extends NewQuicksorter<T>
  {
    @Override
    /**
     * Select a random element of the subarray as the pivot.
     */
    public T selectPivot(T[] vals, Comparator<T> order, int lb, int ub)
    {
      Random rand = new Random();
      int randomNum = rand.nextInt((ub - lb) + 1) + lb;
      return vals[randomNum];
    } // selectPivot(T[], vals, Comparator<T> order, int lb, int ub)
  } // QuickSorterRandom


