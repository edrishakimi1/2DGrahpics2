package s1.demo

import s1.demo.Epilepse.{ball, clock, pic, speed}
import s1.demo.Snakes.clock

import javax.imageio.ImageIO
import java.io.File
import s1.image.ImageExtensions._
import s1.image.{Color, ImageExtensions}

import scala.util.Random
/**
 * This example demonstrates manipulating single pixels
 */
object Dissolve extends Effect(500, 500) {
  // This variable is used to find out when to end this effect
  var clock = 0

  // Here we load the image we are going to manipulate
  var current = {
    // Start with an empty image
    val start = emptyImage

    // ...and draw the Aalto logo in the middle of it
    val aaltoLogo = ImageIO.read(new File("pictures/Aalto_logo.png"))
    start.graphics.drawImage(aaltoLogo, 100, 100, null)

    // Return the modified image
    start
  }

  val random = new Random

  // A completely random color used later in the algorithm
  val transparent = s1.image.Color(0,0,0,0)

  val red   = s1.image.Color(255,0,0,255)
  val black = s1.image.Color(0,0,0,255)
  val green = s1.image.Color(0,255,0,127)

  val fireColors = Vector.tabulate(256)(
    i => s1.image.Color(
      i,
      clamp((i-107)*3, 255),
      clamp((i-130)*4, 255),
      255)
  )

  // Clamp used to keep (randomly generated) pixel coordinates inside the image area
  def clamp(v: Int, maxValue: Int) = math.max(0, math.min(v, maxValue))





  val fireWidth  = 100
  val fireHeight = 100

  val fire = Array.ofDim[Int](fireHeight, fireWidth)

  /**
   * This method creates (or in this case only returns) a BufferedImage
   * of the effect
   */
  def makePic() = {
    val nextImage = ImageExtensions.emptyImage(100, 100)
    val fireImage = emptyImage
    val graphics = fireImage.graphics

     for (k <- 1 to 30) {

        val sade = 250 - k * 10
        val xkohta = 250
        val ykohta = 250

        var kulmat = 7

        val kulmatkellu = kulmat * 1.0

        var nopeus = 0.1

        if(clock > 100){
          speed = -0.1
        }

        val xx = Array.tabulate(kulmat)( x => ( sade * math.cos(clock * k * speed + x / kulmatkellu * (2.0 * math.Pi))).toInt + xkohta)
        val yy = Array.tabulate(kulmat)( x => ( sade * math.sin(clock * k * speed + x / kulmatkellu * (2.0 * math.Pi))).toInt + ykohta)
        if(k % 2 == 1) {
          graphics.setColor(java.awt.Color.black)
        } else {

        }
       for {
      y <- 0 until fireHeight
      x <- 0 until fireWidth
    } nextImage(x, y).color = fireColors(fire(y)(x))


        val g = fireImage.graphics
      g.drawImage(nextImage, 0,0,500, 500, null)
       g.fillPolygon(xx, yy, kulmat)
        //graphics.fillRect(250, 250, 100, 100)
        //graphics.setColor(java.awt.Color.pink)








    }
    fireImage
  }

  /**
   * This method updates the state of the effect - In this particular algorithm it
   * actually manipulates the image.
   */
  def changeThings() = {
     for (x <- 0 until fireWidth) {
       fire(fireHeight - 1)(x) = random.nextInt(2) * 255
       fire(fireHeight - 2)(x) = random.nextInt(2) * 255
     }
     clock += 1

     for {
       x <- 30 to 60
       y <- 30 to 60
     } if (math.hypot(50-x, 50-y) < 10) fire(y)(x) =  random.nextInt(2) * 255

     for {
       y <- 0 until fireHeight - 2
       x <- 1 until fireWidth  - 1
     } {
        fire(y)(x) = clamp((fire(y+1)(x-1) + fire(y+1)(x) + fire(y+1)(x+1) + fire(y+2)(x)) / 4 , 255)
     }
  }


  def crap() = {
    // The current bitmap image can be copied if we do not wish to edit the original
    // (In this example editing the original would have been fine - this is to explain things)
    val nextImage = current.copy

    for (i <- 1 to 300) {
      // pick a random coordinate
      val x1 = random.nextInt(width)
      val y1 = random.nextInt(height)

      // ...and another one nearby
      val x2 = clamp(x1 - 10 + random.nextInt(20), width-1)
      val y2 = clamp(y1 - 10 + random.nextInt(20), height-1)

      // We can check if pixels are opaque
      if (nextImage(x1,y1).opaque && !nextImage(x2, y2).opaque) {

        // We can rean and write the value of a pixel
        nextImage(x2, y2).color = nextImage(x1,y1).color

        // We can set the color of any pixel to a predefined color
        nextImage(x1, y1).color = transparent
      }
    }
    // This helper variable is used to check when to change effects
    clock += 1

    current = nextImage
  }

  // The effect ends when we have changed the model 300 times
  def next = clock > 300
}
