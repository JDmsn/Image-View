package ImageView;

import Model.Dimension;
import Model.NoImageException;
import View.Persistance.ImageReader;
import Model.Image;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class FileImageReader implements ImageReader {
    
    File[] images;

    protected static String[] fileTypes = {".png",".jpg",".bmp"};

    public FileImageReader(String path){
        images = new File(path).listFiles(
        new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {

                for (String extension : fileTypes)
                    if(name.toLowerCase().endsWith(extension)) return true;
                return false;
            }
        });
    }

    public Image getImage(final int index) throws NoImageException {
        if(images==null || index < 0 || index >= images.length) throw new NoImageException();
        return new Image() {
        	@Override
            public Image prev() throws NoImageException {
                return (index <= 0)? getImage(images.length-1) : getImage(index-1);
            }
            
            @Override
            public Image next() throws NoImageException {
                return (index >= images.length-1)? getImage(0) : getImage(index+1);
            }

            @Override
            public String getName() {
                return images[index].getName();
            }
            
            @Override
            public BufferedImage bitmap() {
                try {
                    return ImageIO.read(images[index]);
                } catch (IOException e) {
                    return null;
                }
            }

            @Override
            public Dimension dimension() {
                try {
                    Dimension dimension = new Dimension(ImageIO.read(images[index]).getWidth(),
                            ImageIO.read(images[index]).getHeight());
                    return dimension;
                } catch (IOException e) {
                    return new Dimension(0,0);
                }

            }
        };
    }
}
