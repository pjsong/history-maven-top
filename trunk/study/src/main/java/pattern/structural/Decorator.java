package pattern.structural;

interface Window {
    public void draw(); // draws the Window
    public String getDescription(); // returns a description of the Window
}
 
 
// implementation of a simple Window without any scrollbars
class SimpleWindow implements Window {
    public void draw() {
        // draw window
    }
 
    public String getDescription() {
        return "simple window";
    }
}
abstract class WindowDecorator implements Window {
    protected Window decoratedWindow; // the Window being decorated
 
    public WindowDecorator (Window decoratedWindow) {
        this.decoratedWindow = decoratedWindow;
    }
}
 
 
// the first concrete decorator which adds vertical scrollbar functionality
class VerticalScrollBarDecorator extends WindowDecorator {
    public VerticalScrollBarDecorator (Window decoratedWindow) {
        super(decoratedWindow);
    }
 
    public void draw() {
        drawVerticalScrollBar();
        decoratedWindow.draw();
    }
 
    private void drawVerticalScrollBar() {
        // draw the vertical scrollbar
    }
 
    public String getDescription() {
        return decoratedWindow.getDescription() + ", including vertical scrollbars";
    }
}
 
 
// the second concrete decorator which adds horizontal scrollbar functionality
class HorizontalScrollBarDecorator extends WindowDecorator {
    public HorizontalScrollBarDecorator (Window decoratedWindow) {
        super(decoratedWindow);
    }
 
    public void draw() {
        drawHorizontalScrollBar();
        decoratedWindow.draw();
    }
 
    private void drawHorizontalScrollBar() {
        // draw the horizontal scrollbar
    }
 
    public String getDescription() {
        return decoratedWindow.getDescription() + ", including horizontal scrollbars";
    }
}
    public class Decorator {
        public static void main(String[] args) {
            // create a decorated Window with horizontal and vertical scrollbars
            Window decoratedWindow = new HorizontalScrollBarDecorator (
                    new VerticalScrollBarDecorator(new SimpleWindow()));
     
            // print the Window's description
            System.out.println(decoratedWindow.getDescription());
        }
    }
