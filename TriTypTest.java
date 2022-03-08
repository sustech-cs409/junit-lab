import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class TriTypTest {
    int BOUND = Integer.MAX_VALUE;
    Random random = new Random();

    private int randInt(){
        return ((int) Math.pow(-1, random.nextInt(2))) * random.nextInt(BOUND);
    }

    private int randNonPos(){
        return - random.nextInt(BOUND);
    }

    private int randPos(){
        return random.nextInt(BOUND) + 1;
    }

    @Test
    public void TestResultRange(){
        for (int i = 0; i < 1000; i++) {
            int T = trityp.Triang(randInt(), randInt(), randInt());
            assertTrue(1 <= T && T <= 4);
        }
    }

    @Test
    public void TestSymmetry(){
        for (int i = 0; i < 1000; i++) {
            int A = randInt();
            int B = randInt();
            int C = randInt();
            int T1 = trityp.Triang(A, B, C);
            int T2 = trityp.Triang(A, C, B);
            int T3 = trityp.Triang(B, A, C);
            int T4 = trityp.Triang(B, C, A);
            int T5 = trityp.Triang(C, A, B);
            int T6 = trityp.Triang(C, B, A);
            assertTrue(T1 == T2 && T2 == T3 &&
                    T3 == T4 && T4 == T5 && T5 == T6);
        }
    }

    @Test
    public void TestIsosceles(){
        /* When the waist of isosceles triangle is too large,
        the type of triangle will be misjudged */

        for (int i = 0; i < 1000; i++) {
            int A = randPos();
            int B = randPos();
            while (A == B)
                B = randPos();

            assert A > 0 && B > 0;

            int T = trityp.Triang(A, A, B);
            assertEquals(2, T);
        }
    }

    @Test
    public void TestEquilateral(){
        for (int i = 0; i < 1000; i++) {
            int A = randPos();
            assert A > 0;

            int T = trityp.Triang(A, A, A);
            assertEquals(3, T);
        }
    }

    @Test
    public void TestBoundary1(){
        for (int i = 0; i < 1000; i++) {
            int A = randPos();
            int B = randPos();
            while (A == B)
                B = randPos();
            assert A > 0 && B > 0;

            int T = trityp.Triang(A, B, Math.abs(A-B));
            assertEquals(4, T);
        }

    }

    @Test
    public void TestBoundary2(){
        for (int i = 0; i < 1000; i++) {
            int T = trityp.Triang(randNonPos(), randPos(), randPos());
            assertEquals(4, T);
        }
    }
}
