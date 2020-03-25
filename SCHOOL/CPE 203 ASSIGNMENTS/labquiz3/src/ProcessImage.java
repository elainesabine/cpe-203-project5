//
//
//*** YOU MAY MODIFY THIS CLASS
//
//

//No need to refactor any code here, but to fix any issues that might occur after you refactored your code!


import processing.core.PApplet;

public class ProcessImage extends PApplet {


	public void settings() {
    size(500, 500);
	}

	private boolean redraw=false;
	private boolean start= true;
	private static String description="";

	private static Rectangle rec1,rec2;
	private static Point2D point;
	private static Line line;

	public void setup() {
    	background(180);
    	//noLoop();
  	}

  	public void draw() {

		if (start)
		{
			textSize(20);
			text("Click me!", 10, 30);
			fill(0, 102, 153);
			text("Now!", 10, 60);
			start= false;
		}

		if(redraw)
		{
			//Modify this part so that it works after you refactored the code


			background(180);//reset background to gray

			//you can comment the next 3 lines out after you are done!
			textSize(18);
			fill(255, 0, 100, 255);
			text("Okay, there is more to do - please read the instructions!", 10, 30);

			//this will draw rec1
			rec1 = new Rectangle(new Point(100, 250), 100,50, "rec1",new ColorRGB(0,255,0));
			stroke(rec1.col.R,rec1.col.G,rec1.col.B);
			fill(rec1.col.R,rec1.col.G,rec1.col.B);
			rect(rec1.p.x, rec1.p.y, rec1.length, rec1.width);

			//this will draw rec2
			rec2 = new Rectangle(new Point(150, 100), 100,50, "rec2",new ColorRGB(255,255,0));
			stroke(rec2.col.R,rec2.col.G,rec2.col.B);
			fill(rec2.col.R,rec2.col.G,rec2.col.B);
			rect(rec2.p.x, rec2.p.y, rec2.length, rec2.width);

			//this will draw a line 10 pixel below the mouse click
			line = new Line(new Point(mouseX,mouseY+10), 100, "line1",new ColorRGB(0,0,0));
			stroke(line.col.R,line.col.B,line.col.G);
			line(mouseX,mouseY+10,mouseX+100,mouseY+10);

			//this will draw point
			// for better visualization you can make this spot several pixels large (thickness)

			point = new Point2D(mouseX,mouseY,"MousePoint");
			stroke(point.col.R,point.col.G,point.col.B);
			int thickness=5;
			for(int x=0;x<thickness;x++)
				for(int y=0;y<thickness;y++)
					point(point.p.x+x,point.p.y+y);


				//Intersection Tests:
			boolean intersect;
			description="";

			intersect = Util.testIntersect(rec1, rec2);
			description += rec1 + " intersected "+ rec2 + ": " + intersect+"\n";

			intersect = Util.testIntersect(rec1, line);
			description+= rec1 + " intersected "+ line + ": " + intersect+"\n";

			intersect = Util.testIntersect(rec1, point);
			description+=  rec1 + " intersected "+ point + ": " + intersect+"\n";

			textSize(10);
			fill(0, 102, 153);
			text(description,10, 400);

			redraw = false;
		}



  	}
  	public void mousePressed()
	{
		redraw= true;

	}

  	public static void main(String args[]) {
      PApplet.main("ProcessImage");




   }
}
