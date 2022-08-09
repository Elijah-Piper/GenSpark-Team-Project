import org.junit.jupiter.api.Test;

public class ColumnTester   {
    @Test
    public void construct() {
        System.out.println("Testing several means of construction:");
        String[] testSet = { "p[p", "ppp", "ppppp" };
        ColumnFormatter test = new ColumnFormatter(testSet, 3);
        String[] got = test.getSet();
        for (String s : got)
            System.out.println(s);
        assert test.getSet()[0].compareTo("p[p      ppp      ppppp") == 0;
        System.out.println("\nnext\n");
        test.setColumns(1);
        String[] expected = { "p[p", "ppp", "ppppp" };
        got = test.getSet();
        System.out.println("checking single column printing");
        for (String s : got)
            System.out.println(s);
        assert got.length == 3;
        for (int i = 0; i < 3; i++)
            assert got[i].compareTo(expected[i]) == 0;
        System.out.println("\ntesting compound set\n");
        test.add(testSet);
        test.setColumns(4);
        got = test.getSet();
        for (String s : got)
            System.out.println(s);
    }
}