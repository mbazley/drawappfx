package drawappfx;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.StringTokenizer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Arc;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.scene.transform.Scale;

public class Parser {

    private BufferedReader reader;
    private Drawing drawing;
    private Paint currentPaint = Color.BLACK;
    private boolean singleStep = false;
    private boolean turtlePen = false;
    private double turtleX = 0;
    private double turtleY = 0;
    private double turtleAngle = 0;
    public String line;

    public Parser(Reader reader, Drawing image) {
        this.reader = new BufferedReader(reader);
        this.drawing = image;
    }

    public boolean parse() throws ParseException {
        try {
            do {
                line = reader.readLine();
                if (line == null) {
                    break;
                }
                parseLine(line);
            } while (!singleStep);
            return line == null;
        } catch (ParseException e) {
            throw e;
        } catch (IOException e) {
            throw new ParseException("IO exception: " + e.getMessage());
        } catch (Exception e) {
            throw new ParseException("Unknown exception: " + e.getMessage());
        }
    }

    private void parseLine(String line) throws ParseException, IOException {
        if (line.length() < 2) {
            throw new ParseException("Line too short");
        }
        String command = line.substring(0, 2);
        if (command.equals("DL")) {
            drawLine(line.substring(2, line.length()));
            return;
        }
        if (command.equals("DR")) {
            drawRect(line.substring(2, line.length()));
            return;
        }
        if (command.equals("FR")) {
            fillRect(line.substring(2, line.length()));
            return;
        }
        if (command.equals("SC")) {
            setColour(line.substring(3, line.length()));
            return;
        }
        if (command.equals("DS")) {
            drawString(line.substring(3, line.length()));
            return;
        }
        if (command.equals("DA")) {
            drawArc(line.substring(2, line.length()));
            return;
        }
        if (command.equals("DO")) {
            drawOval(line.substring(2, line.length()));
            return;
        }
        if (command.equals("S+")) {
            singleStep = true;
            return;
        }
        if (command.equals("S-")) {
            singleStep = false;
            return;
        }
        if (command.equals("IS")) {
            imageSize(line.substring(2, line.length()));
            return;
        }
        if (command.equals("DI")) {
            drawImage(line.substring(2, line.length()));
            return;
        }
        if (command.equals("IP")) {
            imagePattern(line.substring(2, line.length()));
            return;
        }
        if (command.equals("LG")) {
            linearGradient(line.substring(3, line.length()));
            return;
        }
        if (command.equals("RG")) {
            radialGradient(line.substring(3, line.length()));
            return;
        }
        if (command.equals("DQ")) {
            drawQuad(line.substring(2, line.length()));
            return;
        }
        if (command.equals("DC")) {
            drawCubic(line.substring(2, line.length()));
            return;
        }
        if (command.equals("T+")) {
            turtlePen = true;
            return;
        }
        if (command.equals("T-")) {
            turtlePen = false;
            return;
        }
        if (command.equals("TF")) {
            turtleForward(line.substring(2, line.length()));
            return;
        }
        if (command.equals("TR")) {
            turtleRight(line.substring(2, line.length()));
            return;
        }
        if (command.equals("TL")) {
            turtleLeft(line.substring(2, line.length()));
            return;
        }

        throw new ParseException("Unknown drawing command: " + command);
    }

    private void drawLine(String args) throws ParseException {
        try {
            StringTokenizer tokenizer = new StringTokenizer(args);
            double x1 = getNumber(tokenizer);
            double y1 = getNumber(tokenizer);
            double x2 = getNumber(tokenizer);
            double y2 = getNumber(tokenizer);
            Line s = new Line(x1, y1, x2, y2);
            s.setStroke(currentPaint);
            drawing.add(s);
        } catch (ParseException e) {
            throw new ParseException("Format: DL x1 y1 x2 y2");
        }
    }

    private void drawRect(String args) throws ParseException {
        try {
            Rectangle s = rect(args);
            s.setFill(null);
            s.setStroke(currentPaint);
            drawing.add(s);
        } catch (ParseException e) {
            throw new ParseException("Format: DR x y width height");
        }
    }

    private void fillRect(String args) throws ParseException {
        try {
            Rectangle s = rect(args);
            s.setFill(currentPaint);
            drawing.add(s);
        } catch (ParseException e) {
            throw new ParseException("Format: FR x y width height");
        }
    }

    private Rectangle rect(String args) throws ParseException {
        StringTokenizer tokenizer = new StringTokenizer(args);
        double x = getNumber(tokenizer);
        double y = getNumber(tokenizer);
        double width = getNumber(tokenizer);
        double height = getNumber(tokenizer);
        return new Rectangle(x, y, width, height);
    }

    private void drawArc(String args) throws ParseException {
        try {
            StringTokenizer tokenizer = new StringTokenizer(args);
            double x = getNumber(tokenizer);
            double y = getNumber(tokenizer);
            double width = getNumber(tokenizer);
            double height = getNumber(tokenizer);
            double startAngle = getNumber(tokenizer);
            double arcAngle = getNumber(tokenizer);
            Arc s = new Arc(x + width / 2, y + height / 2,
                    width / 2, height / 2, startAngle, arcAngle);
            s.setFill(null);
            s.setStroke(currentPaint);
            drawing.add(s);
        } catch (ParseException e) {
            throw new ParseException("Format: DA x y width height startAngle arcAngle");
        }
    }

    private void drawOval(String args) throws ParseException {
        try {
            StringTokenizer tokenizer = new StringTokenizer(args);
            double x = getNumber(tokenizer);
            double y = getNumber(tokenizer);
            double width = getNumber(tokenizer);
            double height = getNumber(tokenizer);
            Ellipse s = new Ellipse(x + width / 2, y + height / 2,
                    width / 2, height / 2);
            s.setFill(null);
            s.setStroke(currentPaint);
            drawing.add(s);
        } catch (ParseException e) {
            throw new ParseException("Format: DO x y width height");
        }
    }

    private void drawQuad(String args) throws ParseException {
        try {
            StringTokenizer tokenizer = new StringTokenizer(args);
            double x1 = getNumber(tokenizer);
            double y1 = getNumber(tokenizer);
            double cx1 = getNumber(tokenizer);
            double cy1 = getNumber(tokenizer);
            double x2 = getNumber(tokenizer);
            double y2 = getNumber(tokenizer);
            QuadCurve s = new QuadCurve(x1, y1, cx1, cy1, x2, y2);
            s.setFill(null);
            s.setStroke(currentPaint);
            drawing.add(s);
        } catch (ParseException e) {
            throw new ParseException("Format: DQ x1 y1 cx1 cy1 x2 y2");
        }
    }

    private void drawCubic(String args) throws ParseException {
        try {
            StringTokenizer tokenizer = new StringTokenizer(args);
            double x1 = getNumber(tokenizer);
            double y1 = getNumber(tokenizer);
            double cx1 = getNumber(tokenizer);
            double cy1 = getNumber(tokenizer);
            double cx2 = getNumber(tokenizer);
            double cy2 = getNumber(tokenizer);
            double x2 = getNumber(tokenizer);
            double y2 = getNumber(tokenizer);
            CubicCurve s = new CubicCurve(x1, y1, cx1, cy1, cx2, cy2, x2, y2);
            s.setFill(null);
            s.setStroke(currentPaint);
            drawing.add(s);
        } catch (ParseException e) {
            throw new ParseException("Format: DC x1 y1 cx1 cy1 cx2 cy2 x2 y2");
        }
    }

    private void imageSize(String args) throws ParseException {
        try {
            StringTokenizer tokenizer = new StringTokenizer(args);
            double width = getNumber(tokenizer);
            double height = getNumber(tokenizer);
            drawing.changeSize(width, height);
        } catch (ParseException e) {
            throw new ParseException("Format: IS width height");
        }
    }

    private void drawString(String args) throws ParseException {
        try {
            StringTokenizer tokenizer = new StringTokenizer(args);
            double x = getNumber(tokenizer);
            double y = getNumber(tokenizer);
            int position = args.indexOf("@");
            if (position == -1) {
                throw new ParseException();
            }
            Text s = new Text(x, y, args.substring(position + 1, args.length()));
            s.getTransforms().add(new Scale(0.85, 0.85, x, y));
            s.setBoundsType(TextBoundsType.VISUAL);
            drawing.add(s);
        } catch (ParseException e) {
            throw new ParseException("Format: DS x y @string");
        }
    }

    private void drawImage(String args) throws ParseException, IOException {
        int position;
        double x, y, width, height;
        try {
            StringTokenizer tokenizer = new StringTokenizer(args);
            x = getNumber(tokenizer);
            y = getNumber(tokenizer);
            width = getNumber(tokenizer);
            height = getNumber(tokenizer);
            position = args.indexOf("@");
            if (position == -1) {
                throw new ParseException();
            }
        } catch (ParseException e) {
            throw new ParseException("Format: DI x y width height @filename");
        }
        String filename = args.substring(position + 1, args.length());
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(filename);
        } catch (Exception e) {
            throw new ParseException("Could not open file '" + filename + "'");
        }
        Image image = new Image(inputStream, width, height, false, true);
        inputStream.close();
        ImageView imageView = new ImageView(image);
        imageView.setX(x);
        imageView.setY(y);
        drawing.add(imageView);
    }

    private void imagePattern(String args) throws ParseException, IOException {
        int position;
        double x, y, width, height;
        try {
            StringTokenizer tokenizer = new StringTokenizer(args);
            x = getNumber(tokenizer);
            y = getNumber(tokenizer);
            width = getNumber(tokenizer);
            height = getNumber(tokenizer);
            position = args.indexOf("@");
            if (position == -1) {
                throw new ParseException();
            }
        } catch (ParseException e) {
            throw new ParseException("Format: DI x y width height @filename");
        }
        String filename = args.substring(position + 1, args.length());
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(filename);
        } catch (Exception e) {
            throw new ParseException("Could not open file '" + filename + "'");
        }
        Image image = new Image(inputStream);
        inputStream.close();
        currentPaint = new ImagePattern(image, x, y, width, height, false);
    }

    private void linearGradient(String args) throws ParseException {
        try {
            currentPaint = LinearGradient.valueOf(args);
        } catch (Exception e) {
            throw new ParseException("Could not parse linear gradient string");
        }
    }

    private void radialGradient(String args) throws ParseException {
        try {
            currentPaint = RadialGradient.valueOf(args);
        } catch (Exception e) {
            throw new ParseException("Could not parse radial gradient string");
        }
    }

    private void setColour(String colourName) throws ParseException {
        if (colourName.equals("black")) {
            currentPaint = Color.BLACK;
            return;
        }
        if (colourName.equals("blue")) {
            currentPaint = Color.BLUE;
            return;
        }
        if (colourName.equals("cyan")) {
            currentPaint = Color.CYAN;
            return;
        }
        if (colourName.equals("darkgray")) {
            currentPaint = Color.DARKGRAY;
            return;
        }
        if (colourName.equals("gray")) {
            currentPaint = Color.GRAY;
            return;
        }
        if (colourName.equals("green")) {
            currentPaint = Color.GREEN;
            return;
        }
        if (colourName.equals("lightgray")) {
            currentPaint = Color.LIGHTGRAY;
            return;
        }
        if (colourName.equals("magenta")) {
            currentPaint = Color.MAGENTA;
            return;
        }
        if (colourName.equals("orange")) {
            currentPaint = Color.ORANGE;
            return;
        }
        if (colourName.equals("pink")) {
            currentPaint = Color.PINK;
            return;
        }
        if (colourName.equals("red")) {
            currentPaint = Color.RED;
            return;
        }
        if (colourName.equals("white")) {
            currentPaint = Color.WHITE;
            return;
        }
        if (colourName.equals("yellow")) {
            currentPaint = Color.YELLOW;
            return;
        }
        throw new ParseException("Unknown colour name '" + colourName + "'");
    }

    private void turtleForward(String args) throws ParseException {
        try {
            StringTokenizer tokenizer = new StringTokenizer(args);
            double n = getNumber(tokenizer);
            double oldx = turtleX;
            double oldy = turtleY;
            turtleX += n * Math.sin(Math.toRadians(turtleAngle));
            turtleY += n * Math.cos(Math.toRadians(turtleAngle));
            if(turtlePen) {
                Line s = new Line(oldx, oldy, turtleX, turtleY);
                s.setStroke(currentPaint);
                drawing.add(s);
            }
        } catch (ParseException e) {
            throw new ParseException("Format: TF n");
        }
    }

    private void turtleRight(String args) throws ParseException {
        try {
            StringTokenizer tokenizer = new StringTokenizer(args);
            double n = getNumber(tokenizer);
            turtleAngle -= n;
        } catch (ParseException e) {
            throw new ParseException("Format: TF n");
        }
    }

    private void turtleLeft(String args) throws ParseException {
        try {
            StringTokenizer tokenizer = new StringTokenizer(args);
            double n = getNumber(tokenizer);
            turtleAngle += n;
        } catch (ParseException e) {
            throw new ParseException("Format: TF n");
        }
    }
    
    private double getNumber(StringTokenizer tokenizer) throws ParseException {
        if (tokenizer.hasMoreTokens()) {
            return Double.parseDouble(tokenizer.nextToken());
        } else {
            throw new ParseException();
        }
    }
}