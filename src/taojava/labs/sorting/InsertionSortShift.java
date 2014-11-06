package taojava.labs.sorting;

import java.util.Comparator;

public class InsertionSortShift<T>
  extends SorterBridge<T>
  {
    /**
     * Sort vals using insertion sort. See the Sorter<T> interface for additional
     * details.
     */
    @Override
    public T[] sorti(T[] vals, Comparator<T> order)
    {
      for (int i = 1; i < vals.length; i++)
        {
          insert(vals, order, i);
        } // for
      return vals;
    } // sorti(T[], Comparator<T>)

    
    void insert(T[] vals, Comparator<T> order, int n)
    {
      int i = n;
      T temp = vals[n];
      while ((i > 0) && (order.compare(vals[i - 1], vals[i]) > 0))
        {
          vals[i] = vals[i-1];
          i--;
        } // while
      vals[i] = temp;
    } //insert (T[], Comparator, int)
} // class InsertionSortShift
