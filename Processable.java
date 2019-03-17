/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular2dshapes;

/**
 *
 * @author vinoses
 */
public interface Processable {
    
   String getDetailsFromSides(int i);
   String getDetailsFromArea(double area);
   String getDetailsFromSidesArea(int i, double area);
   String getDetailsFromCircumference(double circum);
   String getDetailsFromCircumferenceSides(double circum,int sides);
   
}
