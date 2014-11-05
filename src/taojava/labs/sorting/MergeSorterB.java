package taojava.labs.sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.io.PrintWriter;


public class MergeSorterB<T>
{
 
  public T[] merge(T[] vals, Comparator<T> order, int lb, int ub, T[]scratch)
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    int mid;
    int length = ub-lb;
    if (length <= 1)
      {
        return vals;
      }//if
    else 
      {
        mid = lb + length/2;
        merge(vals, order, lb, mid, scratch);
        merge(vals, order, mid+1, ub, scratch);
        for (int i = lb; i < ub; i++)
          {
            scratch[i] = vals[i];
          }//for  
        pen.println(Arrays.toString(merge(vals, order, lb, mid, scratch))); 
        //testing out steps
        pen.println(Arrays.toString(merge(vals, order, mid+1, ub, scratch))); 
        //testing out steps
        return Utils.merge(order, scratch, lb, mid, scratch, mid + 1, ub, vals,
                           lb, ub);
      }//else
  }//merge(T[], Comparator<T>, int, int, T[])
  public static void main(String args[])
  {
    T[] arr = { 3, 4, 8, 5, 6, 7, 1 };
    int[] scratch = {};
    merge(arr, StandardIntegerComparator.COMPARATOR, 0, 6, scratch);
  }
}//class MergeSorterB
