import java.util.ArrayList;
import java.util.Collections;

public class ColumnFormatter {
    private int padding = 0;
    private int _minPad = 4; // to give a minimum number of spaces between columns
    private int columns = 1;
    private ArrayList<String> set = new ArrayList<>();
    ColumnFormatter(String[] set)   {
        Collections.addAll(this.set, set);
        setPadding();
    }
    ColumnFormatter(int columns)    {
        this.columns = columns;
    }
    ColumnFormatter(String[] set, int columns)   {
        Collections.addAll(this.set, set);
        this.columns = columns;
        setPadding();
    }
    private void setPadding()   {
        padding = 0;
        for (String s : set)
            if (s.length() > padding)
                padding = s.length();
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
    public void remove(int index)   {
        set.remove(index);
    }
    public void remove(Object o)    {
        set.remove(o);
    }
    private String appendEveryX(int start, int interval)    {
        StringBuilder temp = new StringBuilder(set.get(start));
        int j = 1;
        start += interval;
        while (start < set.size())  {
            while (temp.length() < (padding * j))
                temp.append(' ');
            j++;
            temp.append(set.get(start));
            start += interval;
        }
        return temp.toString();
    }
    public String[] getSet()    {
        int maxStringsInCol = set.size() / columns;
        if (set.size() % columns != 0)
            maxStringsInCol++;
        String[] outSet = new String[maxStringsInCol];
        for (int i = 0; i < maxStringsInCol; i++)
            outSet[i] = appendEveryX(i, maxStringsInCol);
        return outSet;
    }
    @Override
    public String toString()  {
        String[] temp = getSet();
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
