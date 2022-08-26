package cn.myh001.pojo;

public class Fruit {
    private Integer id;
    private String fruitName;
    private Double fruitPrice;
    private String saleUnit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public Double getFruitPrice() {
        return fruitPrice;
    }

    public void setFruitPrice(Double fruitPrice) {
        this.fruitPrice = fruitPrice;
    }

    public String getSaleUnit() {
        return saleUnit;
    }

    public void setSaleUnit(String saleUnit) {
        this.saleUnit = saleUnit;
    }

    @Override
    public int hashCode() {
        return this.fruitName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Fruit) {
            Fruit fruit = (Fruit) obj;
            return fruit.fruitName.equals(this.fruitName);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", fruit_name='" + fruitName + '\'' +
                ", fruit_price=" + fruitPrice +
                ", sale_unit='" + saleUnit + '\'' +
                '}';
    }
}
