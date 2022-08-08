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
        setPadding();
    }
    private void setPadding()   {
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
    public String[] getSet()    {
        StringBuilder temp;
        int maxStringsInCol = set.size() / columns;
        maxStringsInCol += set.size() % columns;
        String[] outSet = new String[maxStringsInCol];
        for (int i = 0; i < maxStringsInCol; i++)   {
            temp = new StringBuilder(set.get(i));
            if (i + maxStringsInCol < set.size())   {
                while (temp.length() < padding)
                    temp.append(' ');
                temp.append(set.get(i + maxStringsInCol));
            }
            outSet[i] = temp.toString();
        }
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
