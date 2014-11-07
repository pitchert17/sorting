package taojava.labs.sorting;

import java.io.PrintWriter;

public class IntSorter
  
  {
    /**
     * Sort vals using insertion sort.
     * @param vals: an array of ints
     * @return vals
     */
    
    public static int[] sorti(int[] vals)
    {
      for (int i = 1; i < vals.length; i++)
        {
          insert(vals, i);
        } // for
      return vals;
    } // sorti(T[], Comparator<T>)

    /**
     * 
     * @pre: n <= val.length
     * @post: none
     * @param vals: an array of ints
     * @param n: position in the array
     * 
     */
    static void insert(int[] vals, int n)
    {
      int i = n;
      int temp = vals[n];
      while ((i > 0) && (vals[i - 1] > vals[i]))
        {
          vals[i] = vals[i-1];
          i--;
        } // while
      vals[i] = temp;
    } //insert (T[], Comparator, int)
    
    
    public static void main(String[] args)
    {
      PrintWriter pen = new PrintWriter(System.out, true);
      SimpleTimer timer = new SimpleTimer();
      int[] tester = new int[10000];
      for (int i=0; i<10000; i++)
        {
          tester[i] = i+1;
        }//for
      System.gc();
      
      timer.start();
      sorti(tester);
      
      timer.pause();
      
      pen.println(timer.elapsed());//display the time
    }//main(String[])
} // class InsertionSortShift
/* We found the difference between the generic method and 
 * the int specific method was negligible. We also found that
 * the fact that generics work with all data types makes a 
 * generic implementation far more useful that a method 
 * specific to a certain data type.
 */
