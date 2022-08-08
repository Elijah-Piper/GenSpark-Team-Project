public class ColumnFormatter {
    private int padding = 0;
    private int _minPad = 4; // to give a minimum number of spaces between columns
    private int columns = 1;
    private String[] set;
    ColumnFormatter(String[] set)   {
        this.set = set;
    }
    ColumnFormatter(int columns)    {
        this.columns = columns;
    }
    ColumnFormatter(String[] set, int columns)   {
        this.set = set;
        for (String s : set)
            if (s.length() > padding)
                padding = s.length();
        padding += _minPad;
    }
    public void setColumns(int columns)  {
        this.columns = columns;
    }
    public void setStrings(String[] strings) {
        set = strings;
    }
    public String[] getSet()    {
        StringBuilder temp;
        int maxStringsInCol = set.length / columns;
        maxStringsInCol += set.length % columns;
        String[] outSet = new String[maxStringsInCol];
        for (int i = 0; i < maxStringsInCol; i++)   {
            temp = new StringBuilder(set[i]);
            if (i + maxStringsInCol < set.length)   {
                while (temp.length() < padding)
                    temp.append(' ');
                temp.append(set[i + maxStringsInCol]);
            }
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
