///////////////////////////////////
// This is the main shift register class.
// Notice that it implements the ILFShiftRegister interface.
// You will need to fill in the functionality.
///////////////////////////////////

import java.util.ArrayList;
import java.util.Arrays;

/**
 * class ShiftRegister
 * @author
 * Description: implements the ILFShiftRegister interface.
 */
public class ShiftRegister implements ILFShiftRegister {
    ///////////////////////////////////
    // Create your class variables here
    ///////////////////////////////////
    // TODO:

    int[] register;
    int size1;
    int tap1;


    ///////////////////////////////////
    // Create your constructor here:
    ///////////////////////////////////
    ShiftRegister(int size, int tap) {
        // TODO:
        if (tap >= size) {
            System.out.println("Error: Tap cannot be a bigger index than size");

        }
        if (tap < 0 || size < 0) {
            System.out.println("Error: Tap and size cannot be negative");
        }
        size1 = size;
        tap1 = tap;
    }

    ///////////////////////////////////
    // Create your class methods here:
    ///////////////////////////////////
    /**
     * setSeed
     * @param seed
     * Description:
     */
    @Override
    public void setSeed(int[] seed) {
        // TODO:

        // Check that the array is the correct size
        if (seed.length != size1) {
            System.out.println("Error: Seed incorrect size");

            return;
        }
        // Check that the array is all 0's and 1's
        for (int i = 0; i < seed.length; i++) {
            int bit = seed[i];
            if (bit != 0 && bit != 1) {
                System.out.println("Error: detected number not zero or one");
                break;
            }

        }


        register = seed;

        System.out.println(register);
    }

    /**
     * shift
     * @return
     * Description:
     */
    @Override
    public int shift() {
        // TODO:
//        System.out.println(tap1);
//        System.out.println("^^^^^");
//        System.out.println(Arrays.toString(register));
//        System.out.println("----------");
        int feedback = register[tap1] ^ register[register.length - 1];
        for (int i = register.length - 2; i >= 0 ; i--) {
            register[i+1] = register[i];
        }
        register[0] = feedback;
//        System.out.println(Arrays.toString(register));
        return feedback;
    }

    /**
     * generate
     * @param k
     * @return
     * Description:
     */
    @Override
    public int generate(int k) {
        // TODO:

        // create an array of length k
        int helper[] = new int[k];
        for (int i = 0; i < k; i++) {
            helper[i] = shift();
        }

        // convert helper[] to binary
//        System.out.println(Arrays.toString((helper)));
        int res = 0;
        for (int i = 0; i < k; i++) {
            res = (int) (res + Math.pow(2, i) * helper[k - i - 1]);
        }


        return res;
    }

    /**
     * Returns the integer representation for a binary int array.
     * @param array
     * @return
     */
    private int toDecimal(int[] array) {
        // TODO:
        return 0;
    }

    private int[] getBit() {
        return register;
    }

    public static void main(String[] args) {
//        int[] array = new int[] {0, 1, 0, 1, 1, 1, 1, 0, 1};
//        ShiftRegister shifter = new ShiftRegister(9, 7);
//        shifter.setSeed(array);
//        for (int i = 0; i < 10; i++) {
//            int j = shifter.shift();
//            System.out.print(j);
//        }
//
//        int[] array = new int[] {0, 1, 0, 1, 1, 1, 1, 0, 1};
//        ShiftRegister shifter = new ShiftRegister(9, 7);
//        shifter.setSeed(array);
//        for (int i = 0; i < 10; i++) {
//            int j = shifter.generate(3);
//            System.out.println(j);
//        }

        int[] array = new int[]{0, 1, 0, 1, 1, 1, 1, 0, 1};
        ShiftRegister shifter = new ShiftRegister(9, 1);
        shifter.setSeed(array);

        ArrayList<String> holder = new ArrayList<String>();
        boolean repeated = false;
        for (int i = 0; i < 1000; i++) {

            int j = shifter.shift();
//            System.out.println("hello");
//            System.out.print(j);
//            System.out.println(shifter);
            if (holder.contains(shifter.toString())) {
                System.out.println("repeated at index " + i);
                repeated = true;
//                break;
            }
            holder.add(shifter.toString());
        }
        if (!repeated) {
            System.out.println("Did not repeat in 1000 tries");
        }

        // TEST RESULTS for size = 9 for various values of tap:
        // tap = 0: repeated after 73 shifts
        // tap = 1: 465
        // 2: 21
        // 3: 511
        // 4: 511
        // 5: 21
        // 6: 465
        // 7: 73
        // 8: 8
        // therefore, some taps are better than others.

        int sum = 0;
        for (int i = 0; i < holder.size(); i++) {
            String str = holder.get(i);
            String[] arr = str.split("");
            for (int j = 0; j < arr.length; j++) {
                sum = sum + Integer.parseInt(arr[j]);
            }
        }

        System.out.println("Total number of ones: " + sum);
        System.out.println("Total number of zeros: " + (1000 * 9 - sum));

        // Somewhat random!
        // Varies depending on the tap.

    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < register.length; i++) {
            str += register[i];
        }
        return str;
    }
}
