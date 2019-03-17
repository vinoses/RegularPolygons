package regular2dshapes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author Roger Jean
 * Last edited: 03/16/19
 * Graphical User Interface
 * <p>
 * Client selects area or circumference, enters values for area and side count
 * Program returns rendered picture of polygon with relevant facts
 * <p>
 * Note: Mostly swing, the graphics of polygon rendering uses awt container? 
 *  Utilizing JPanel class and overriding paintComponent method for 
 *    smoother graphics rendering with swing implementation. 
 * Note:: See startGUI method for client implementation details
*/
public class Regular2DShapes implements Processable
{
    private double area;
    private double circum;
    private int sides;
    private static JFrame frame;
    private JPanel buttonPanel, inputPane, outputPane;
    private JButton button;
    private JLabel inputLabel,sidesLabel,areaLabel,cirLabel,nameLabel,angleLabel,lengthLabel;
    private JTextField text,areaText,cirText,nameText,angleText,lengthText,edgesText;
    private JComboBox combo;
    private JSpinner spinner;
    private SpinnerNumberModel model;
    private String details = "";
    private String[] detailsLines;
    private JTextArea instructionText;                                                    //somehow linked to graphics ability to repaint new graphics
    DrawPolygon dp ;

    /**
     * @param args the command line arguments
     * self instantiating to invoke overriding classes? works this way.
     */
    public static void main(String[] args) {
        Regular2DShapes rgs = new Regular2DShapes();
        rgs.start();
    }
    /**
     * GUI entrance 
     */
    private void start() {
        SwingUtilities.invokeLater(this::startGUI);
    }
    
    /**
     * GUI driver
     * <p>
     * IMPLEMENTED ELEMENTS:
     * Layouts: BorderLayout for-> 
     *              Polygon graphic[CENTER], 
     *              Action cells[NORTH,EAST], 
     *              BoxLayout[WEST] 
     *                  OutputDetails[Y-AXIS]
     * Polygon graphic  : JPanel point-to-point paintComponent graphic   
     * Action button    : Initiates call to interface methods with inputs 
     *                      for relevant details, details are parsed for output
     * Spinner          : Number of sides of the polygon (3-100)
     * DropDownMenu     : Input choice between area and circumference
     * TextField        : Input spot for area or circumference
     * TextArea         : Informational block, ??required for graphic repaint??
     * <p>
     * IMPLEMENTATIONS/FIXES PENDING: 
     * exceptions for incorrect input
     * determine link between graphic and textarea 
     *      (graphic does not repaint if textarea removed)
     *      *best guess: To get polygon graphic in frame had to add 
     *                      directly without placing in a panel first.
     *      *            This causes the frame packaging to render over the
     *                      graphic when packaging the panels, except text area 
     *                      'instructionText' creates deadspace in the center 
     *                      of the frame that the packaging doesn't eliminate, 
     *                      leaving space for the graphic.
     */
    public void startGUI()
    {       
        DecimalFormat df = new DecimalFormat("###.###");
        frame = new JFrame("Regular 2D Geometric Shapes");
        frame.setVisible(true);
        frame.setSize(600,375);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);         
        
        /*
         * GUI Title 
         */
        JLabel titleLabel = new JLabel("♦▲♦POLYGONS▲♦▲     ", SwingConstants.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(25));

        /*
         * Panel Instruction Set 
         */
        String instructions = "INSTRUCTIONS\n"
                + "(1) Select circumference or area\n"
                + "(2) Enter value for selected\n"
                + "(3) Select number of sides\n"
                + "(4) Calculations are FUN!";
        
        /*
         * Drop Down Menu, calculation variables
         */
        String[] dynamicFactors = {"area","circumference"};
        combo = new JComboBox(dynamicFactors);
        combo.setSelectedIndex(1);
        
        /*
         * Text box declarations 
         */
        text = new JTextField(10);
        instructionText = new JTextArea(10,4);
        instructionText.setEditable(false);
        areaText = new JTextField(15);
        areaText.setEditable(false);
        cirText = new JTextField(10);
        cirText.setEditable(false);
        nameText = new JTextField(10);
        nameText.setEditable(false);
        angleText = new JTextField(10);     
        angleText.setEditable(false); 
        lengthText = new JTextField(10);
        lengthText.setEditable(false);
        edgesText = new JTextField(10);
        edgesText.setEditable(false);       
 
        /*
         * Label instantiations
         */
        inputLabel = new JLabel("       Input ");
        sidesLabel = new JLabel("Sides              ");   
        areaLabel = new JLabel("Area                ");
        cirLabel = new JLabel("Circumference        ");
        nameLabel = new JLabel("Name                ");
        angleLabel = new JLabel("Inner Angle        ");      
        lengthLabel = new JLabel("Side Length       "); 
        
        /*
         * Number of polygon sides input   
         */      
        model = new SpinnerNumberModel(3,1,100,1); //(start,count+,stop,count-)         
        spinner = new JSpinner(model); 

        /*
         * Frame Button Panel - WEST  
         */            
        buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(Color.CYAN);  

        /*
         * Frame Input Panel - NORTH  
         */         
        inputPane = new JPanel();
        inputPane.setBackground(Color.CYAN);
        inputPane.add(titleLabel);
        inputPane.add(combo); 
        inputPane.add(inputLabel);
        inputPane.add(text);
        inputPane.add(sidesLabel);
        inputPane.add(spinner);    

        /*
         * Frame Output Panel - EAST  
         */           
        outputPane = new JPanel();
        outputPane.setLayout(new BoxLayout(outputPane,BoxLayout.Y_AXIS));
        outputPane.setBackground(Color.CYAN);   
        outputPane.add(areaLabel);  outputPane.add(areaText);
        outputPane.add(cirLabel);   outputPane.add(cirText);
        outputPane.add(nameLabel);  outputPane.add(nameText);
        outputPane.add(angleLabel); outputPane.add(angleText);
        outputPane.add(lengthLabel);outputPane.add(lengthText);
        outputPane.add(sidesLabel); outputPane.add(edgesText);       
         
        outputPane.add(instructionText); 
        
        /*
         * Opening Graphic and UI- CENTER  
         */ 
        details = getDetailsFromSidesArea(20, 7337);  
        instructionText.setText(instructions);
        frame.add(inputPane,BorderLayout.NORTH);
        frame.add(outputPane,BorderLayout.WEST); 
                dp = new DrawPolygon(20);
        frame.add(dp,BorderLayout.CENTER);
        
        /*
         * Button ActionEvent listener:
         * (1)Retrieves input values 
         * (2)Calls interface methods with input values
         * (3)Parses returned details for output
         * (4)Generates selected polygon 
         */         
        button = new JButton("Calculate");        
        button.addActionListener((ActionEvent e) -> {
            sides = (int)spinner.getValue();
            if (text.getText().equals("")) {
                area = 7337;
                details = getDetailsFromSidesArea(sides, area);
            } else if (combo.getSelectedItem().equals("area")) {
                area = Math.abs(Double.parseDouble(text.getText()));            // assign input from text field
               details = getDetailsFromSidesArea(sides, area);
            }
            else{
                circum =  Math.abs(Double.parseDouble(text.getText()));         
               details = getDetailsFromCircumferenceSides(circum,sides);
            }
            instructionText.setText(instructions);
            detailsLines = details.split("\\n");
            if(sides < 21){
                angleText.setText(detailsLines[1].split(" ")[0]);               //parse polygon details 
                nameText.setText(detailsLines[0]);
                areaText.setText(detailsLines[5].split(" ")[0]);
                cirText.setText(detailsLines[3].split(" ")[0]);
                lengthText.setText(detailsLines[4].split(" ")[0]);
                edgesText.setText(detailsLines[2].split(" ")[0]);
            }
            else{
                nameText.setText("NA");   
                angleText.setText((df.format(((double)sides-2)*180              //calculate angle past enumerated values
                        /(double)sides)) +"" );
                edgesText.setText(detailsLines[0].split(" ")[0]);
                cirText.setText(detailsLines[1].split(" ")[0]);
                lengthText.setText(detailsLines[2].split(" ")[0]);
                areaText.setText(detailsLines[3].split(" ")[0]);
            }
            //nameText.setText(
            
            frame.add(inputPane,BorderLayout.NORTH);
            frame.add(outputPane, BorderLayout.WEST);
            dp = new DrawPolygon(sides);
            frame.add(dp,BorderLayout.CENTER);
            
        });   
 
        buttonPanel.add(button);        
        frame.add(buttonPanel,BorderLayout.LINE_END);        
    }
    
    public void getDetailsFromGUI(){}
    @Override
    public String getDetailsFromSides(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDetailsFromArea(double areaInput) {
        area = areaInput;
        Shape shape = new Shape();
        details =  shape.getDetailsFromArea(area);
        circum = shape.getCircumference();
        sides = shape.getSides();
        return details;
    }

    @Override
    public String getDetailsFromSidesArea(int sidesInput, double areaInput) {
        sides = sidesInput;
        area = areaInput;
        Shape shape = new Shape();
        details = shape.getDetailsFromSidesArea(sides, area);
        circum = shape.getCircumference();
        return details;
    }

    @Override
    public String getDetailsFromCircumference(double circumInput) {
        circum = circumInput;
        Shape shape = new Shape();
        details = shape.getDetailsFromCircumference(circum);
        sides = shape.getSides();
        area = shape.getArea();
        return details;
    }

    @Override
    public String getDetailsFromCircumferenceSides(double circumInput, int sidesInput) {
        sides = sidesInput;
        circum = circumInput;
        Shape shape = new Shape();
        details = shape.getDetailsFromCircumferenceSides(circum,sides);
        area = shape.getArea();
        return details;
    }
}    
 
