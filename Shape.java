package regular2dshapes;

import java.text.DecimalFormat;

/**
 * 
 * @author Roger Jean
 * Last modified: 3/13/2019
 * regular geometric shape superclass
 */
public class Shape implements Processable{
    
    private int sides;
    private double area;
    private double circum;
    
    public Shape(){ }    
    /**
     *
     * @param i
     * @return
     */
    @Override
   public String getDetailsFromSides(int i){
       String details = ShapeType.values()[i-3] +"\n" + ShapeType.values()[i-3].getSides()
               + " sides \n" + ShapeType.values()[i-3].getInnerAngle() + " degree Inner Angle";
       System.out.println(details);
       return details;
   }

    /**
     *
     * @param areaInput
     * @return String of details for each polygon with count less than 12 
     * not utilized in ui, for terminal testing or future implementation
     */
    @Override
   public String getDetailsFromArea(double areaInput){
       area = areaInput;
       String details = "";
       double length = 0; 
       circum = 0;

       Polygons rgs = new Polygons();
       DecimalFormat df = new DecimalFormat("###.###");
       for(int i = 0; i < 10;i++)
       {
            rgs = new Polygons();
            rgs.setSides(i+3);
            rgs.setArea(area);    
            circum = rgs.getCircumference();
            length = rgs.getLength();

            if(i <10){ 
                details += ShapeType.values()[i] + "\n"+ df.format(ShapeType.values()[i].getInnerAngle()) + " InnerAngle\n";       
            }
            details += rgs.getSides() + " sides\n" + df.format(circum) + " Circumference" + df.format(length) + " Length" + df.format(area) + " Area\n";
       }      
       return details;
   }

    /**
     *
     * @param sidesInput
     * @param areaInput
     * @return String of polygon details
     */
    @Override
   public String getDetailsFromSidesArea(int sidesInput, double areaInput){
       area = areaInput;
       sides = sidesInput;
       String details = "";
       double length = 0; 
       circum = 0;
       Polygons rgs = new Polygons();
       DecimalFormat df = new DecimalFormat("###.###");
       rgs = new Polygons();
       rgs.setSides(sides);
       rgs.setArea(area);    
       circum = rgs.getCircumference();
       length = rgs.getLength();
        if(sides <21 && sides > 2){ 
            details += ShapeType.values()[sides - 3] + "\n"
                    + df.format(ShapeType.values()[sides - 3].getInnerAngle()) + " InnerAngle\n";         
        }
        details += rgs.getSides() + " sides\n" + df.format(circum) + " Circumference\n" + df.format(length) + " Length\n" + df.format(area) + " Area\n";
            
        return details;
   }

    /**
     *
     * @param circumInput
     * @return String of details for each polygon with count less than 12 
     * not utilized in ui, for terminal testing or future implementation
     * 
     */
    @Override
   public String getDetailsFromCircumference(double circumInput){
   
       circum = circumInput;
       String details = "";
       double length = circum; 
       Polygons rgs = new Polygons();
       DecimalFormat df = new DecimalFormat("###.###");
       
        for(int i = 0; i < 10;i++)
       {
            rgs = new Polygons();
            rgs.setSides(i+3);
            rgs.setCircum(circum);    
            area = rgs.getArea();
            length = rgs.getLength();

            if(i <10){ 
                details += ShapeType.values()[i] + "\n"+ df.format(ShapeType.values()[i].getInnerAngle()) + " InnerAngle\n";       
            }
            details += rgs.getSides() + " sides\n" + df.format(circum) + " Circumference\n" + df.format(length) + " Length\n" + df.format(area) + " Area\n\n";
       }
        return details;
   }

    /**
     *
     * @param circumInput
     * @param sidesInput
     * @return String of polygon details
     */
    @Override
   public String getDetailsFromCircumferenceSides(double circumInput,int sidesInput){
       circum = circumInput;
       sides = sidesInput;
       String details = "";
       double length = 0;
       Polygons rgs = new Polygons();
       DecimalFormat df = new DecimalFormat("###.###");
       rgs = new Polygons();
       rgs.setSides(sides);
       rgs.setCircum(circum);    
       area = rgs.getArea();
       length = rgs.getLength();
        if(sides <21 && sides > 2){ 
            details += ShapeType.values()[sides -3] + "\n"+ df.format(ShapeType.values()[sides - 3].getInnerAngle()) + " InnerAngle\n";         
        }
        details += rgs.getSides() + " sides\n" + df.format(circum) + " Circumference\n" + df.format(length) + " Length\n" + df.format(area) + " Area\n";
        System.out.println(details);
        return details;
   }  
    /**
     * @param areaInput shape area input for all shapes   
     */  
    public void setArea(double areaInput) {
        area = areaInput;
    } 
    
    /**
     * @param circumInput shape circumference input for all shapes   
     */  
    public void setCircumference(double circumInput) {
        circum = circumInput;
    } 
    
    /**
     * @param sidesInput shape sides count, determines shape   
     */  
    public void setSides(int sidesInput) {
        sides = sidesInput;     
    } 
    
    /**
     * @return double area, internal method for testing,    
     */     
    public int getSides(){
        return sides;
    } 
    
    /**
     * @return double area, internal method for testing,    
     */     
    public ShapeType getName(){
        System.out.println(sides);
        
        return ShapeType.values()[sides-3];
    }    
    
    /**
     * @return double area, internal method for testing,    
     */     
    public double getArea(){
        return area;
    } 
    
    /**
     * @return double area, internal method for testing,    
     */     
    public double getCircumference(){
        return circum;
    }    
    
     /**
     * @return double value of shapes inner angle from shapes defined properties 
     * sum of inner angles = 180 + (sides -3)*180
     * inner angle = sum of inner angles / sides
     */      
    public double getInnerAngle() {
        return ShapeType.values()[sides-3].getInnerAngle();
    }
}

