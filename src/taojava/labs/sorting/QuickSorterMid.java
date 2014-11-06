package taojava.labs.sorting;

import java.util.Comparator;

public class QuickSorterMid<T>
  extends NewQuicksorter<T>
  {
    @Override
    /**
     * Select the middle element of the subarray as the pivot.
     */
    public T selectPivot(T[] vals, Comparator<T> order, int lb, int ub)
    {
      int mid = ub-lb/2;
      return vals[mid];
    } // selectPivot(T[], vals, Comparator<T> order, int lb, int ub)
  } // QuickSorterMid

