package my.numberaddition;

import java.io.Serializable;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Acer
 */
public class RecIntegral implements Serializable{

    public double topBorder;
    public double downBorder;
    public double step;
    public double nextStep;
    public double result;
    public double iterationResult;

    public double getTopBorder() {
        return topBorder;
    }
    public double getDownBorder() {
        return downBorder;
    }
    public double getStep() {
        return step;
    }
    public double getIterationResult() {
        return iterationResult;
    }
    public double getResult() {
        return result;
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
    
//    public void setIterationResult(double iterationResult) throws ExceptionLab3 {
//        if (this.getStep() >= iterationResult) {
//            throw new ExceptionLab3("Значение шага больше значения h");
//        }
//        this.iterationResult = iterationResult;
//    }
    
    
    public RecIntegral(double topBorder, double downBorder, double step, double result) {
        this.topBorder = topBorder;
        this.downBorder = downBorder;
        this.step = step;
        this.result = result;
    }
    public RecIntegral() {
        this.topBorder = 0;
        this.downBorder = 0;
        this.step = 0;
        this.result = 0;
    }
    
    public RecIntegral(Object topBorder, Object downBorder, Object step) throws ExceptionLab3 {
        try {
        this.setTopBorder(Double.parseDouble(topBorder.toString()));
        this.setDownBorder(Double.parseDouble(downBorder.toString()));
        this.setStep(Double.parseDouble(step.toString()));
        
        } catch (NumberFormatException e) {
            throw new ExceptionLab3("---", e);
        }catch (ExceptionLab3 ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"err",JOptionPane.ERROR_MESSAGE);
        }
    }

}
