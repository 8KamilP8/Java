package DataFrame;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DataFrame {
    private ArrayList<Column> columns;
    private class Column{
        public String name;
        public Type recordsType;
        public ArrayList<Object> records;
        public Type GetRecordsType() {
            return records.get(0).getClass();
        }
        public Column(String name, Type type){
            this.name = name;
            this.recordsType = type;
        }
        public void addRecord(Object obj){
            if(obj.getClass() == recordsType){
                records.add(obj);
            }
        }
        public void setRecord(int index,Object obj){
            if(obj.getClass() == recordsType){
                records.set(index,obj);
            }
        }
    }
    public DataFrame(String[] columnsNames, Type[] types) {
        int tmpColumnsLength = columnsNames.length;
        columns = new ArrayList<Column>(tmpColumnsLength);
        for(int i = 0;i < tmpColumnsLength; ++i) {
            Column currentColumn = columns.get(i);
            currentColumn.recordsType = types[i];
            currentColumn.name = columnsNames[i];
        }
    }
    public int size(){
        return columns.size();
    }
    public ArrayList<Object> get(String colname){
        for (Column column:columns) {
            if(colname == column.name)
                return column.records;
        }
        return  null;
    }
    public DataFrame get(String [] cols, boolean copy) {
        if (copy) {
            Type types[] = new Type[cols.length];
            for(int i = 0;i < this.columns.size(); ++i) {
                Column currentColumn = columns.get(i);
                if(currentColumn.name.equals(cols[i])){
                    types[i] = currentColumn.recordsType;
                }
            }
            DataFrame dataFrame = new DataFrame(cols,types);
            return dataFrame;
        }
        else{

            return null;
        }
    }
    public DataFrame iloc(int i){
        Column target = columns.get(i);
        String[] S = {target.name};
        Type[] T = {target.recordsType};
        DataFrame result = new DataFrame(S,T);
        result.columns.get(0).records = new ArrayList<Object>(target.records);
        return result;
    }
    public DataFrame iloc(int from, int to){
        String[] targets = new String[to - from + 1]; //od 0 do 0 jest jeden wiersz
        for (int i =from; i <= to; i++){
            targets[i-from] = columns.get(i).name;
        }
        return get(S,true);
    }
}

