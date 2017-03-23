import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JOptionPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
public class Main{
	public static int val4=0;
	public static int val1=0;
public static	 int val2=0;
public static	 int val3=0;
    public static void main(String args[])throws IOException{
       BufferedImage img = new BufferedImage(1200,720,BufferedImage.TYPE_INT_RGB);
    	
        File f = null;
       String fileName="fractal"+((int)(Math.random()*1000))+".jpg";
       String inputValue1 = JOptionPane.showInputDialog("Please enter a value for R");
       String inputValue2 = JOptionPane.showInputDialog("Please enter a value for G");   
       String inputValue3 = JOptionPane.showInputDialog("Please enter a value for B");


       String inputValue4 = JOptionPane.showInputDialog("Please enter a value for The number of iterations");
       val1= Integer.parseInt(inputValue1);
	   val2= Integer.parseInt(inputValue2);
	   val3= Integer.parseInt(inputValue3);
       
	   val4= Integer.parseInt(inputValue4);
       
        //read image
       /* try{
            f = new File("IMG_0293.jpg");
            img = ImageIO.read(f);
        }catch(IOException e){
            System.out.println(e);
        }
       */ 
        //get image width and height
        int width = img.getWidth();
        int height = img.getHeight();
        
///box_count_2d(img);
img=   make_grey(img); 
  
        //write image
        try{
            f = new File(fileName);
            ImageIO.write(img, "jpg", f);
        }catch(IOException e){
            System.out.println(e);
        }
   
    	try {
			new Gui(fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
        System.out.println(fileName);   }//main
    
    
    
    
    
    static int findMandelbrot(double cr, double ci, int max_iterations)
    {
    	int i = 0;
    	double zr = 0.0, zi = 0.0;
    	while (i < max_iterations && zr * zr + zi * zi < 4.0)
    	{
    		double temp = zr * zr - zi * zi + cr;
    		zi = 2.0 * zr * zi + ci;
    		zr = temp;
    		i++;
    	}

    	return i;
    }

    static double mapToReal(int x, int imageWidth, double minR, double maxR)
    {
    	double range = maxR - minR;
    	return x * (range / imageWidth) + minR;
    }

    static double mapToImaginary(int y, int imageHeight, double minI, double maxI)
    {
    	double range = maxI - minI;
    	return y * (range / imageHeight) + minI;
    }


public static void box_count_2d( BufferedImage img){
	
   img=   make_grey(img); //make image black and white to make it easier to work with
    int max_width = img.getWidth();
    int max_height = img.getHeight();
int squere_size = 1;
    int numSteps;
    int numBoxes=0;

    for(int y = 0; y <squere_size; y++){
    	
        for(int x = 0; x < squere_size; x++){
        	
        	
    if ( img.getRGB(x, y) !=-1)
        		
{
	numBoxes++;
}
        
        	
        	System.out.print("possiton " +"(" +x+","+ y+")" + " value : " );
        	System.out.println(img.getRGB(x, y));
        }}
    
    
    System.out.println(numBoxes);
	
}
public static BufferedImage make_grey( BufferedImage img){
	
	//get image width and height
    int width = img.getWidth();
    int height = img.getHeight();
    // file dimentions   
    
    int maxN=val4;
	double minR =-1.5;  
	double  maxR=0.7;
	double minI=-1; 
	double maxI= 1;
    
    //write pixel by pixel
    for(int y = 0; y < height; y++){
        for(int x = 0; x < width; x++){
        
            
            //replace RGB value with avg
        	double cr = mapToReal(x, width, minR, maxR);
			double ci = mapToImaginary(y, height, minI, maxI);

			// ... Find the number of iterations in the Mandelbrot formula
			//     using said c.
			
			
			int n = findMandelbrot(cr, ci, maxN);
			
			int r=(n*val1)%256;
			int g=(n*val2)%256;
			int b=(n*val3)%256;
			
			int rgb = new Color(r, g, b).getRGB();
			
		

			// ... Map the resulting number to an RGP value
	
		//	r=r%256;
			System.out.println(rgb);
            img.setRGB(x, y, rgb);
          
            
        }
    
    }
	return img;
	

}

}