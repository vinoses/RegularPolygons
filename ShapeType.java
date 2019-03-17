package regular2dshapes;

/**
 *  static values of considered regular geometric shapes
 */   
public enum ShapeType {
    TRIANGLE(3),
    EQUILATERAL(4),
    PENTAGON(5),
    HEXAGON(6),
    HEPTAGON(7),
    OCTAGON(8),
    NONAGON(9),
    DECAGON(10),
    HENDECAGON(11),
    DODECAGON(12),
    TRIDECAGON(13),
    TETRADECAGON(14),
    PENDEDECAGON(15),
    HEXDECAGON(16),
    HEPTDECAGON(17),
    OCTDECAGON(18),
    ENNEADECAGON(19),
    ICOSAGON(20);
    private final int sides;
    private final double innerAngles;    
    
     /**
     * @param sides as enumerated values
     * sides determine static inner angles
     */      
    private ShapeType(int sides) {       
         this.sides = sides; 
         this.innerAngles = ((double)this.sides-2)*180/(double)this.sides;
    }

     /**
     * @return integer value of shapes number of sides 
     */  
    public int getSides() {
        return sides;
    }

     /**
     * @return double value of shapes inner angle from shapes defined properties 
     * sum of inner angles = 180 + (sides*180)
     * inner angle = sum of inner angles / sides
     */      
    public double getInnerAngle() {
       return innerAngles;
    }
}

