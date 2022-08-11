import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ColumnFormatter {
    private int padding = 0;
    private int _minPad = 4; // to give a minimum number of spaces between columns
    private int columns = 1;
    private ArrayList<String> set = new ArrayList<>();
    ColumnFormatter(String[] set)   {
        Collections.addAll(this.set, set);
        setPadding();
    }
    ColumnFormatter(ArrayList<String> set)  {
        this.set = set;
    }
    ColumnFormatter(int columns)    {
        this.columns = columns;
    }
    ColumnFormatter(String[] set, int columns)   {
        Collections.addAll(this.set, set);
        this.columns = columns;
        setPadding();
    }
    ColumnFormatter(ArrayList<String> set, int columns)   {
        this.set = set;
        this.columns = columns;
        setPadding();
    }
    private void setPadding()   {
        padding = 0;
        int len = set.stream().reduce("", (a,b) -> a.length() > b.length() ? a : b).length();
        padding = Math.max(len, padding);
        padding += _minPad;
    }
    public void setColumns(int columns)  {
        this.columns = columns;
    }
    public void setStrings(String[] strings) {
        set = new ArrayList<>();
        Collections.addAll(set, strings);
        setPadding();
    }
    public void add(String[] strings)   {
        Collections.addAll(set, strings);
        setPadding();
    }
    public void add(ArrayList<String> strings)  {
        set.addAll(strings);
        setPadding();
    }
    public void remove(int index)   {
        set.remove(index);
    }
    public void remove(Object o)    {
        set.remove(o);
    }
    private String appendEveryX(final int start, final int interval)    {
        StringBuilder temp = new StringBuilder(set.get(start));
        //without this assignment, the streams don't want to do anything
        ArrayList<String> trickery = set.stream().map(a -> {
            int index = set.indexOf(a);
            if (index > start && index % interval == 0) {
                while (temp.length() % padding != 0)
                    temp.append(' ');
                temp.append(a);
            }
            return temp.toString();
        }).collect(Collectors.toCollection(ArrayList::new));
        return temp.toString();
    }
    private String[] makeSet()  {
        int maxStringsInCol = set.size() / columns;
        if (set.size() % columns != 0)
            maxStringsInCol++;
        String[] outSet = new String[maxStringsInCol];
        for (int i = 0; i < maxStringsInCol; i++)
            outSet[i] = appendEveryX(i, maxStringsInCol);
        return outSet;
    }
    public String[] getSetAsArray()    {
        return makeSet();
    }
    public ArrayList<String> getSetAsList()   {
        return new ArrayList<>(Arrays.asList(makeSet()));
    }
    @Override
    public String toString()  {
        String[] temp = getSetAsArray();
        if (temp.length == 0)
            return "";
        String out = temp[0];
        for (int i = 0; i < temp.length; i++)   {
            out += '\n';
            out += temp[i];
        }
        return out;
    }
}
