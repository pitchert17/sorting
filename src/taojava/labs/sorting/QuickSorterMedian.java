package taojava.labs.sorting;

import java.util.Comparator;
import java.util.Random;

public class QuickSorterMedian<T>
  extends NewQuicksorter<T>
  {
    @Override
    /**
     * Select the median of 3 random elements of the subarray as the pivot.
     */
    public T selectPivot(T[] vals, Comparator<T> order, int lb, int ub)
    {
      Random rand = new Random();
      int randomNum = rand.nextInt((ub - lb) + 1) + lb;
      int randomNum2 = rand.nextInt((ub - lb) + 1) + lb;
      int randomNum3 = rand.nextInt((ub - lb) + 1) + lb;
      int med;
      if (randomNum < randomNum2 && randomNum < randomNum3 
          && randomNum2 < randomNum3)
        {
          med = randomNum2;
        }//if
      else if (randomNum2 < randomNum && randomNum2 < randomNum3 
          && randomNum < randomNum3)
        {
          med = randomNum;
        }//else if
      else 
        {
          med = randomNum3;
        }//else
      
      return vals[med];
    } // selectPivot(T[], vals, Comparator<T> order, int lb, int ub)
  } // QuickSorterMedian
