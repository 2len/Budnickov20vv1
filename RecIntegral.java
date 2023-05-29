/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.numberaddition;

import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class RecIntegral {

    public double topBorder;
    public double downBorder;
    public double step;

    public double getTopBorder() {
        return topBorder;
    }

    public double getDownBorder() {
        return downBorder;
    }

    public double getStep() {
        return step;
    }

    public boolean valueForCmp(double vfc) {
        return vfc > 0.000001 && vfc < 1000000;
    }

    public void setTopBorder(double topBorder) throws ExceptionLab3 {
        if (!valueForCmp(topBorder)) {
            throw new ExceptionLab3("Значение верхней границы не подходит для диапазона от 0,000001 до 1000000");
        }
        if (this.getDownBorder() >= topBorder) {
            throw new ExceptionLab3("Значение верхней границы меньше, чем нижней");
        }
        this.topBorder = topBorder;
    }

    public void setDownBorder(double downBorder) throws ExceptionLab3 {
        if (!valueForCmp(downBorder)) {
            throw new ExceptionLab3("Значение нижней границы не подходит для диапазона от 0,000001 до 1000000");
        }
        if (this.getTopBorder() <= downBorder) {
            throw new ExceptionLab3("Значение верхней границы меньше, чем нижней");
        }
        this.downBorder = downBorder;
    }

    public void setStep(double step) throws ExceptionLab3 {
        if (!valueForCmp(step)) {
            throw new ExceptionLab3("Значение шага не подходит для диапазона от 0,000001 до 1000000");
        }
        this.step = step;
    }

    public RecIntegral(double topBorder, double downBorder, double step) {
        this.topBorder = topBorder;
        this.downBorder = downBorder;
        this.step = step;
    }

    public RecIntegral() {
        this.topBorder = 0;
        this.downBorder = 0;
        this.step = 0;

    }

}
