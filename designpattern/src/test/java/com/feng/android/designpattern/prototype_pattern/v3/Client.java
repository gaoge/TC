package com.feng.android.designpattern.prototype_pattern.v3;

import org.junit.Test;

import java.util.List;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 14:17
 * @tips
 */
public class Client {

    @Test
    public void main(){
        CarPartBox carPartBox = new CarPartBox();
        carPartBox.setNumber(500);
        carPartBox.setName("尾灯灯罩");
        carPartBox.setCardBrand("奥迪");

        //接下来要去拆分
        List<TruckCar> carList = SplitService.splitBox(carPartBox);

        for(TruckCar truckCar: carList){
            System.out.println("数量: " + truckCar.remove().getNumber());
        }
    }
}
