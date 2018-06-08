package apresentacao;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * A panel that contains a background image. The background image is
 * automatically sized to fit in the panel.
 */
public class Background extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5053667317256368716L;
	private BufferedImage image = null;
	private FillType fillType = FillType.RESIZE;

	/**
	 * Creates a new panel with the given background image.
	 * 
	 * @param img The background image.
	 */
	public Background(BufferedImage img)
	{
		setImage(img);
	}

	/**
	 * Creates a new panel with the given background image.
	 * 
	 * @param img The background image.
	 * @throws IOException, if the image file is not found.
	 */
	public Background(File imgSrc) throws IOException
	{
		this(ImageIO.read(imgSrc));
	}

	/**
	 * Creates a new panel with the given background image.
	 * 
	 * @param img The background image.
	 * @throws IOException, if the image file is not found.
	 */
	public Background(String fileName) throws IOException
	{
		this(new File(fileName));
	}

	/**
	 * Changes the image panel image.
	 * 
	 * @param img The new image to set.
	 */
	public final void setImage(BufferedImage img)
	{
		if (img == null)
			throw new NullPointerException("Buffered image cannot be null!");

		this.image = img;
		invalidate();
	}

	/**
	 * Changes the image panel image.
	 * 
	 * @param img The new image to set.
	 * @throws IOException If the file does not exist or is invalid.
	 */
	public void setImage(File img) throws IOException
	{
		setImage(ImageIO.read(img));
	}

	/**
	 * Changes the image panel image.
	 * 
	 * @param img The new image to set.
	 * @throws IOException If the file does not exist or is invalid.
	 */
	public void setImage(String fileName) throws IOException
	{
		setImage(new File(fileName));
	}

	/**
	 * Returns the image associated with this image panel.
	 * 
	 * @return The associated image.
	 */
	public BufferedImage getImage()
	{
		return image;
	}

	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		fillType.drawImage(this, g2d, image);        
		g2d.dispose();
	}

	/**
	 * Returns the way this image fills itself.
	 * 
	 * @return The fill type.
	 */
	public FillType getFillType()
	{
		return fillType;
	}

	/**
	 * Changes the fill type.
	 * 
	 * @param fillType The new fill type
	 * @throws IllegalArgumentException If the fill type is null.
	 */
	public void setFillType(FillType fillType)
	{
		if (fillType == null)
			throw new IllegalArgumentException("Invalid fill type!");

		this.fillType = fillType;
		invalidate();
	}

	public static enum FillType	{
		/**
		 * Make the image size equal to the panel size, by resizing it.
		 */
		RESIZE
		{
			public void drawImage(JPanel panel, Graphics2D g2d,
					BufferedImage image)
			{
				g2d.drawImage(image, 0, 0, panel.getWidth(), panel.getHeight(),
						null);
			}
		},

		/**
		 * Centers the image on the panel.
		 */
		 CENTER
		 {
			public void drawImage(JPanel panel, Graphics2D g2d,
					BufferedImage image)
			{
				int left = (panel.getHeight() - image.getHeight()) / 2;
				int top = (panel.getWidth() - image.getWidth()) / 2;
				g2d.drawImage(image, top, left, null);
			}

		 },
		 /**
		  * Makes several copies of the image in the panel, putting them side by
		  * side.
		  */
		 SIDE_BY_SIDE
		 {
			 public void drawImage(JPanel panel, Graphics2D g2d,
					 BufferedImage image)
			 {
				 Paint p = new TexturePaint(image, new Rectangle2D.Float(0, 0,
						 image.getWidth(), image.getHeight()));
				 g2d.setPaint(p);
				 g2d.fillRect(0, 0, panel.getWidth(), panel.getHeight());
			 }
		 };

		 public abstract void drawImage(JPanel panel, Graphics2D g2d,
				 BufferedImage image);
	}
}
