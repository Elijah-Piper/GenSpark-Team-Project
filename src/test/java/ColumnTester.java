import org.junit.Test;

public class ColumnTester   {
    @Test
    public void construct() {
        String[] testSet = { "p[p", "ppp", "ppppp" };
        System.out.println(testSet);
        ColumnFormatter test = new ColumnFormatter(testSet, 3);
        System.out.println(test);
        test = new ColumnFormatter(testSet);
        System.out.println(test);
        System.out.println("\n");
        test.add(testSet);
        test.setColumns(4);
        System.out.println(test);
    }
}