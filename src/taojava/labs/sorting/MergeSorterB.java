package taojava.labs.sorting;

import java.util.Arrays;
import java.util.Comparator;


public class MergeSorterB<T>
    extends SorterBridge<T>
{
  @Override
  public T[] sort(T[] vals, Comparator<T> order)
  {
    T[] scratch = (T[]) new Object[vals.length];
    return merge(vals, order, 0, vals.length, scratch);
  }//sort((T[], Comparator<T>)

  public T[] merge(T[] vals, Comparator<T> order, int lb, int ub, T[] scratch)
  {
    int mid;
    int length = ub - lb;
    if (length <= 1)
      {
        return vals;
      }//if
    else
      {
        mid = lb + length / 2;
        merge(vals, order, lb, mid, scratch);
        merge(vals, order, mid, ub, scratch);
        for (int i = lb; i < ub; i++)
          {
            scratch[i] = vals[i];
          }//for        
        return Utils.merge(order, scratch, lb, mid, scratch, mid, ub, vals,
                           lb, ub);
      }//else
  }//merge(T[], Comparator<T>, int, int, T[])
}//class MergeSorterB
