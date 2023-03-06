package com.owlbeatsmusic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static File file;
    static byte[][][] bytes;
    static int PIXEL_SIZE   = 0;
    static int renderWidth  = 0;
    static int renderHeight = 0;
    static int width        = 0;
    static int height       = 0;

    public static File convertToBGP(File input) throws IOException {
        StringBuilder colorString = new StringBuilder();
        BufferedImage image = ImageIO.read(input);
        //System.out.println(new Color(image.getRGB(180, 0), true).getRed() + " -> " + (char) (new Color(image.getRGB(180, 0)).getRed()) + " -> " + (byte) (char) (new Color(image.getRGB(180, 0)).getRed()));
        //System.out.println(new Color(image.getRGB(180, 0), true).getGreen() + " -> " + (char) (new Color(image.getRGB(180, 0)).getGreen()) + " -> " + (byte) (char) (new Color(image.getRGB(180, 0)).getGreen()));
        //System.out.println(new Color(image.getRGB(180, 0), true).getBlue() + " -> " + (char) (new Color(image.getRGB(180, 0)).getBlue()) + " -> " + (byte) (char) (new Color(image.getRGB(180, 0)).getBlue()));
        String extension = "";

        int index = input.getName().lastIndexOf('.');
        if (index > 0) {
            extension = input.getName().substring(index + 1);
        }
        if (extension.equals("png")) {
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    Integer[] numbers = new Integer[]{8, 10, 13};
                    List<Integer> nons = Arrays.asList(numbers);
                    //System.out.println("x=" + x + ", y=" + y + " | r=" +(new Color(image.getRGB(x, y)).getRed()/2-1) + (char) (new Color(image.getRGB(x, y)).getRed()/2-1)+ ", g="+ (new Color(image.getRGB(x, y)).getGreen()/2-1) + (char) (new Color(image.getRGB(x, y)).getGreen()/2-1)+ ", b="+ (new Color(image.getRGB(x, y)).getBlue()/2-1) + (char) (new Color(image.getRGB(x, y)).getBlue()/2-1));
                    System.out.println("x=" + x + ", y=" + y + " | " + new Color(image.getRGB(x, y), true).getRed() / 2 + " -> " + (char) (new Color(image.getRGB(x, y)).getRed() / 2) + " -> " + (byte) (char) (new Color(image.getRGB(x, y)).getRed() / 2));
                    if (!(nons.contains(new Color(image.getRGB(x, y)).getRed() / 2)))
                        colorString.append((char) (new Color(image.getRGB(x, y)).getRed() / 2));
                    else
                        colorString.append("0");

                    if (!(nons.contains(new Color(image.getRGB(x, y)).getGreen() / 2)))
                        colorString.append((char) (new Color(image.getRGB(x, y)).getGreen() / 2));
                    else
                        colorString.append("0");

                    if (!(nons.contains(new Color(image.getRGB(x, y)).getBlue() / 2)))
                        colorString.append((char) (new Color(image.getRGB(x, y)).getBlue() / 2));
                    else
                        colorString.append("0");

                }
            }
        }
        if (extension.equals("jpg")) {
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    Integer[] numbers = new Integer[]{8, 10, 13};
                    List<Integer> nons = Arrays.asList(numbers);
                    //System.out.println("x=" + x + ", y=" + y + " | r=" +(new Color(image.getRGB(x, y)).getRed()/2-1) + (char) (new Color(image.getRGB(x, y)).getRed()/2-1)+ ", g="+ (new Color(image.getRGB(x, y)).getGreen()/2-1) + (char) (new Color(image.getRGB(x, y)).getGreen()/2-1)+ ", b="+ (new Color(image.getRGB(x, y)).getBlue()/2-1) + (char) (new Color(image.getRGB(x, y)).getBlue()/2-1));
                    System.out.println("x=" + x + ", y=" + y + " | " + new Color(image.getRGB(x, y), true).getRed() / 2 + " -> " + (char) (new Color(image.getRGB(x, y)).getRed() / 2) + " -> " + (byte) (char) (new Color(image.getRGB(x, y)).getRed() / 2));
                    if (!(nons.contains(new Color(image.getRGB(x, y)).getGreen() / 2)))
                        colorString.append((char) (new Color(image.getRGB(x, y)).getGreen() / 2));
                    else
                        colorString.append("0");

                    if (!(nons.contains(new Color(image.getRGB(x, y)).getBlue() / 2)))
                        colorString.append((char) (new Color(image.getRGB(x, y)).getBlue() / 2));
                    else
                        colorString.append("0");

                    if (!(nons.contains(new Color(image.getRGB(x, y)).getRed() / 2)))
                        colorString.append((char) (new Color(image.getRGB(x, y)).getRed() / 2));
                    else
                        colorString.append("0");

                }
            }
        }
        //System.out.println((image.getWidth()*image.getHeight()) + " = " + colorString.length()/3);

        OLib.Fil.setFileToString(image.getWidth() + "," + image.getHeight() + " >>>>>>>\n" + colorString, new File("src/com/owlbeatsmusic/output.bgp"));
        return new File("src/com/owlbeatsmusic/output.bgp");
    }

    public static void render(int scale) {

        renderWidth  = bytes.length*scale;
        renderHeight = bytes[0].length*scale;
        PIXEL_SIZE = renderWidth  / width;
        
        JFrame root = new JFrame();
        ImagePanel panel = new ImagePanel();
        panel.setSize(new Dimension(width*PIXEL_SIZE+16, height *PIXEL_SIZE+39));
        root.setSize(new Dimension(width*PIXEL_SIZE+16, height *PIXEL_SIZE+39));
        root.add(panel);
        root.setVisible(true);

    }

    public static void parse() {
        System.out.println("1");
        String contents = OLib.Fil.fileToString(file);
        System.out.println("2");

        width = Integer.parseInt(contents.split(",")[0]);
        height = Integer.parseInt(contents.split(",")[1].split(">")[0].strip());
        //System.out.println(contents.split(",")[1].split(">")[0]);
        //System.out.println("w,h : " + width + ", " + height);
        bytes = new byte[height][width][3];                         // RGB

        System.out.println("3");

        int y = -1;
        //System.out.println("c_length = " + contents.length());
        //System.out.println("length   = " + contents.substring(14).length());
        //System.out.println(contents.substring(14));
        String contentsSub = contents.substring(13);
        for (int c = 0; c < contentsSub.length(); c++) {
            if (c%(width *3) == 0) y++;                             // Row
            int x = (c/3)-y* width;                                 // Column
            int i = c%3;                                            // R, G or B
            if (y < height) bytes[y][x][i] = (byte) contentsSub.charAt(c);
            System.out.println("c="+c+" -> x="+x+", y="+y+" | ");
        }
        System.out.println("4");
    }

    static class ImagePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int y = 0; y < Main.height; y++) {
                for (int x = 0; x < Main.width; x++) {
                    System.out.println(x + ", "+ y + " -> r=" + Math.abs(bytes[y][x][0]) + ", g=" + Math.abs(bytes[y][x][1]) + ", b=" + Math.abs(bytes[y][x][2]));
                    g.setColor(new Color(Math.abs(bytes[y][x][0]), Math.abs(bytes[y][x][1]), Math.abs(bytes[y][x][2])));
                    g.fillRect(x * PIXEL_SIZE, y * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        // ### SUPPORTED FILE FORMATS : PNG, JPG
        //file = new File("src/com/owlbeatsmusic/image.bgp");
        file = convertToBGP(new File("src/com/owlbeatsmusic/thumb.jpg"));
        parse();
        render(3);
        //System.out.println(Arrays.deepToString(bytes));
    }
}
