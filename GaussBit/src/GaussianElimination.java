import java.util.BitSet;

public class GaussianElimination {
    private static final double EPSILON = 1e-8;

    private final int m;      // number of rows
    private final int n;      // number of columns
    private BitSet[] a;     // m-by-(n+1) augmented matrix

    /**
     * Solves the linear system of equations <em>Ax</em> = <em>b</em>,
     * where <em>A</em> is an <em>m</em>-by-<em>n</em> matrix and <em>b</em>
     * is a length <em>m</em> vector.
     *
     * @param  A the <em>m</em>-by-<em>n</em> constraint matrix
     * @param  b the length <em>m</em> right-hand-side vector
     * @throws IllegalArgumentException if the dimensions disagree, i.e.,
     *         the length of {@code b} does not equal {@code m}
     */
    public GaussianElimination(BitSet[] A, BitSet b) {
        m = A.length;
        n = A[0].length();

        if (b.length() != m) throw new IllegalArgumentException("Dimensions disagree");

        // build augmented matrix
        for (int i = 0; i < m; i++) {
        	a[i] = new BitSet(n+1);
        	a[i] = A[i];
        	a[i].set(n, b.get(i));
        }

        forwardElimination();

        //assert certifySolution(A, b);
    }

    // forward elimination
    private void forwardElimination() {
        for (int p = 0; p < Math.min(m, n); p++) {

            // find pivot row using partial pivoting
            int max = p;
            for (int i = p+1; i < m; i++) 
                if(a[i].get(p)==true && a[max].get(p)==false)
                	max = i;

            // swap
            swap(p, max);

            // singular or nearly singular
            /*if (Math.abs(a[p][p]) <= EPSILON) {
                continue;
            }*/

            // pivot
            pivot(p);
        }
    }

    // swap row1 and row2
    private void swap(int row1, int row2) {
        BitSet temp = a[row1];
        a[row1] = a[row2];
        a[row2] = temp;
    }

    // pivot on a[p][p]
    private void pivot(int p) {
        for (int i = p+1; i < m; i++) {
            /*double alpha = a[i][p] / a[p][p];
            for (int j = p; j <= n; j++) {
                a[i][j] -= alpha * a[p][j];
            }*/
        	if (a[i].get(p) == false)
        		a[i].xor(a[p]);
        }
    }
    
    public BitSet stringToBit (String[] str) {
    	long[] l = new long[str.length];
    	for (int i = 0; i < str.length; i++)
    		l[i] = Long.parseLong(str[i], 2);
    	return BitSet.valueOf(l);
    }

    /**
     * Returns a solution to the linear system of equations <em>Ax</em> = <em>b</em>.
     *      
     * @return a solution <em>x</em> to the linear system of equations
     *         <em>Ax</em> = <em>b</em>; {@code null} if no such solution
     */
    public BitSet primal() {
        // back substitution
        BitSet x = new BitSet(n);
        /*for(int i = n - 1; i > m - 1; i--)
        	x[i] = 1.0;*/
        /*for (int i = Math.min(n-1, m-1); i >= 0; i--) {
            double sum = 0.0;
            for (int j = i+1; j < n; j++) {
                sum += a[i][j] * x[j];
            }

            if (Math.abs(a[i][i]) > EPSILON)
                x[i] = (a[i][n] - sum) / a[i][i];
            else if (Math.abs(a[i][n] - sum) > EPSILON)
                return null;
        }

        // redundant rows
        for (int i = n; i < m; i++) {
            double sum = 0.0;
            for (int j = 0; j < n; j++) {
                sum += a[i][j] * x[j];
            }
            if (Math.abs(a[i][n] - sum) > EPSILON)
                return null;
        }*/
        return x;
    }

    /**
     * Returns true if there exists a solution to the linear system of
     * equations <em>Ax</em> = <em>b</em>.
     *      
     * @return {@code true} if there exists a solution to the linear system
     *         of equations <em>Ax</em> = <em>b</em>; {@code false} otherwise
     */
    //public boolean isFeasible() {
    //    return primal() != null;
    //}


    // check that Ax = b
    /*private boolean certifySolution(double[][] A, double[] b) {
        if (!isFeasible()) return true;
        double[] x = primal();
        for (int i = 0; i < m; i++) {
            double sum = 0.0;
            for (int j = 0; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            if (Math.abs(sum - b[i]) > EPSILON) {
                StdOut.println("not feasible");
                StdOut.println("b[" + i + "] = " + b[i] + ", sum = " + sum);
                return false;
            }
        }
        return true;
    }*/


    /**
     * Unit tests the {@code GaussianElimination} data type.
     */
    private static void test(String name, BitSet[] A, BitSet b) {
        StdOut.println("----------------------------------------------------");
        StdOut.println(name);
        StdOut.println("----------------------------------------------------");
        GaussianElimination gaussian = new GaussianElimination(A, b);
        BitSet x = gaussian.primal();
    }


    public static void main(String[] args) {
       
    }

}

/******************************************************************************
 *  Copyright 2002-2018, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
