package regular2dshapes;

/**
 * 
 * @author Roger Jean
 * Last modified: 3/13/2019
 * Polygons is a subclass of Shape
 * Performs calculations universal to all polygons
 * 
 */
public class Polygons extends Shape {
    private double shapeArea;
    private double shapeCircum;
    private int shapeSides;
 
    /**
     * constructor inherits superclass protected values
     */       
    public Polygons(){
    }
    
    @Override
    public void setArea(double area){
        shapeArea = area;
    }  
    
    public void setCircum(double circum){
        shapeCircum = circum;
    }
    
    @Override
    public void setSides(int sides){
        shapeSides = sides;
    }
    
    @Override
    public int getSides(){
        return shapeSides;
    }
    
     /**
     * {@inheritDoc}
     * @return double value of shape area defined in superclass    
     */       
    @Override
    public double getArea() { 
        if(shapeCircum != 0){ shapeArea = (shapeSides * Math.pow(getLength(),2)
                /(4*Math.tan(Math.PI/shapeSides)));}        
        return shapeArea;
    }

    /**
     * @return double value of shape circumference from area and number of sides
     * circumference = sides * side length
     */  
    @Override
    public double getCircumference() {
        return shapeSides*getLength();
    }

     /**
     * @return double value of shape side length from area and number of sides 
     * area = (Number of Sides * side_length^2)/(4 tan(pi/#sides)) 
     * note: radians calculation
     * side_length = sqrt(area*4*tan(pi/#sides)/#sides)
     */      
    public double getLength() {         
        if(shapeArea != 0){ 
            shapeCircum = shapeSides* Math.sqrt((shapeArea * 4 * Math.tan(Math.PI/shapeSides))
                    /shapeSides);
            return shapeCircum/shapeSides;}
        else if(shapeCircum != 0)
            return shapeCircum / shapeSides;
        else return 0;   
    }
}
