package com.feng.android.designpattern.prototype_pattern.v1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 14:19
 * @tips
 */
public class SplitService {
    /**
     * 把箱子进行一下拆分
     * 这么写的问题，就是这个代码有点多，而且不便于扩展，比如我要新增一种货箱(尾盖)
     * @param box
     * @return
     */
    public static List<TruckCar> splitBox(IBox box){
        List<TruckCar> carList = new ArrayList<>();
        while(box.getNumber() > 200){
            //要进行拆分
            if(box instanceof PlasticClampBox){
                PlasticClampBox oldBox = (PlasticClampBox)box;

                //每辆车的箱子
                PlasticClampBox newBox = new PlasticClampBox();
                newBox.setName(oldBox.getName());
                newBox.setNumber(200);

                TruckCar truckCar = new TruckCar();
                truckCar.addBox(newBox);
                carList.add(truckCar);

                box.setNumber(box.getNumber() - 200);
            }else if(box instanceof CarPartBox){
                CarPartBox oldBox = (CarPartBox)box;

                //每辆车的箱子
                CarPartBox newBox = new CarPartBox();
                newBox.setName(oldBox.getName());
                newBox.setNumber(200);
                newBox.setCardBrand(oldBox.getCardBrand());

                TruckCar truckCar = new TruckCar();
                truckCar.addBox(newBox);
                carList.add(truckCar);

                box.setNumber(box.getNumber() - 200);
            }
        }
        TruckCar truckCar = new TruckCar();
        truckCar.addBox(box);
        carList.add(truckCar);
        return carList;
    }
}
