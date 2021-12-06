package s1.demo

import java.awt.image.BufferedImage
import s1.image.ImageExtensions._

class Circle(val x: Int, val y: Int)

/**
 * The idea for this effect came from Felix Bade.
 *
 * The effect draws a continuous stream of filled
 * circles that changes it's course randomly.
 */


object Snakes extends Effect(500, 500) {

    // This variable could hold a background image if wanted
    // See [[DIssolve]] for an exampleon how to load image files
    var clock = 0

    val random = new util.Random

    def changeThings() = {

      clock += 1

    }

    //------- drawing -------//

    // Thick and thin line widths
    override def makePic(): BufferedImage = {
      val pic = emptyImage
      val graphics = pic.graphics

      if(clock%4 == 1){
      graphics.setColor(java.awt.Color.black)
      } else if(clock%4 ==2) {
      graphics.setColor(java.awt.Color.black)
      } else if(clock%4 ==3) {
      graphics.setColor(java.awt.Color.black)
      } else graphics.setColor(java.awt.Color.black)

      graphics.fillRect(0, 0, 500, 500)

      for (i <- 1 to 30) {

        val radius = 700 - i * 10
        val xCenter = 250
        val yCenter = 250

        var corners = 7

        if (clock > 100) {
          corners = 6
        }
        if (clock > 200) {
          corners = 5
        }
        if (clock > 300) {
          corners = 4
        }
        if (clock > 400) {
          corners = 3
        }

        val cornersFloat = corners * 2.0

        var speed = 0.1

        if(clock > 100){
          speed = -0.1
        }

        var circle = i * 10

        val xs = Array.tabulate(corners)( x => ( radius * math.cos(clock * i * speed + x / cornersFloat * (2.0 * math.Pi))).toInt + xCenter)
        val ys = Array.tabulate(corners)( x => ( radius * math.sin(clock * i * speed + x / cornersFloat * (2.0 * math.Pi))).toInt + yCenter)
        val hs = corners
        println(xs.mkString("Array(", ", ", ")"))
        if(i% 2 == 1) {
          graphics.setColor(java.awt.Color.black)
        } else {
          graphics.setColor(java.awt.Color.white)
        }
        //graphics.fillPolygon
        //graphics.fillRect(250, 250, 100, 100)
        //graphics.setColor(java.awt.Color.pink)
        graphics.fillOval(150 - circle, 150 - circle, 500 - circle, 500 - circle)
      }
      pic
    }

    // Effects can also receive information on mouse movements.
    // When a mouse goes to ne coordinates this method gets called.

    // We use it to draw still more circles at the mouse location

    // This effect will never end
    def next = clock > 100
  }


