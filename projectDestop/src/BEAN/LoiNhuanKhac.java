/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN;

/**
 *
 * @author dell
 */
public class LoiNhuanKhac {
//    index_nam int primary key,
//	value_Money int,
//	-- những mặt hàng thanh toán
//	information nvarchar(Max)
    private int index_nam;
    private int value_Money;
    private String information;

    public int getIndex_nam() {
        return index_nam;
    }

    public void setIndex_nam(int index_nam) {
        this.index_nam = index_nam;
    }

    public int getValue_Money() {
        return value_Money;
    }

    public void setValue_Money(int value_Money) {
        this.value_Money = value_Money;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
    
    
    
}
