/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular2dshapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import javax.swing.JPanel;

/**
 *
 * @author vinoses
 */
public class DrawPolygon extends JPanel {
    int numberOfSides;
    public DrawPolygon() { numberOfSides = 12;
    }
    public DrawPolygon(int sides){
        numberOfSides = sides;
    }
    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        this.setBackground(Color.WHITE);        
        graphics.setColor(Color.GREEN);  
    Polygon p = new Polygon();
    for (int i = 0; i < numberOfSides; i++)
      p.addPoint((int) (140 + 50 * Math.cos(i * 2 * Math.PI / numberOfSides)),
          (int) (140 + 50 * Math.sin(i * 2 * Math.PI / numberOfSides)));

    graphics.drawPolygon(p);
    graphics.fillPolygon(p);
    }
}
