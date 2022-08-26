package cn.myh001.model;

import cn.myh001.pojo.Fruit;
import cn.myh001.service.FruitService;

import javax.swing.table.AbstractTableModel;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class MyTableModel extends AbstractTableModel {

    String[] titles = {
            "水果编号",
            "水果名称",
            "水果价格",
            "计价单位"
    };
    Vector<String> titlesV = new Vector<>(Arrays.asList(titles));

    FruitService fruitService = new FruitService();
    List<Fruit> fruits = fruitService.selectAll();

    Vector<Vector<Object>> fruitsV = new Vector<>();


    public MyTableModel() {
        dataInit(this.fruits);
    }
    public MyTableModel(List<Fruit> fruits) {
        dataInit(fruits);
    }


    @Override
    public int getRowCount() {
        return fruitsV.size();
    }

    @Override
    public int getColumnCount() {
        return titlesV.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return fruitsV.get(rowIndex).get(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return titlesV.get(column);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void dataInit(List<Fruit> fruits) {

        for (Fruit fruit : fruits) {
            Integer id = fruit.getId();
            String fruitName = fruit.getFruitName();
            Double fruitPrice = fruit.getFruitPrice();
            String saleUnit = fruit.getSaleUnit();
            Vector<Object> objects = new Vector<>();
            objects.add(id);
            objects.add(fruitName);
            objects.add(fruitPrice);
            objects.add(saleUnit);
            fruitsV.add(objects);
        }
    }

}
