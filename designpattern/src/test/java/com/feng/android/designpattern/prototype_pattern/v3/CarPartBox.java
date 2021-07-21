package com.feng.android.designpattern.prototype_pattern.v3;


/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 14:14
 * @tips
 * 具体的出货的物品 - 汽车的零件
 */
public class CarPartBox extends IBox {
    private int number;
    private String name;
    private String cardBrand;//汽车的品牌
    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(String cardBrand) {
        this.cardBrand = cardBrand;
    }

}
