package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class TransformTest2 extends BasicGame {
   private float scale = 1.0F;
   private boolean scaleUp;
   private boolean scaleDown;
   private float camX = 320.0F;
   private float camY = 240.0F;
   private boolean moveLeft;
   private boolean moveUp;
   private boolean moveRight;
   private boolean moveDown;

   public TransformTest2() {
      super("Transform Test");
   }

   public void init(GameContainer var1) throws SlickException {
      var1.setTargetFrameRate(100);
   }

   public void render(GameContainer var1, Graphics var2) {
      var2.translate(320.0F, 240.0F);
      var2.translate(-this.camX * this.scale, -this.camY * this.scale);
      var2.scale(this.scale, this.scale);
      var2.setColor(Color.red);

      for(int var3 = 0; var3 < 10; ++var3) {
         for(int var4 = 0; var4 < 10; ++var4) {
            var2.fillRect((float)(-500 + var3 * 100), (float)(-500 + var4 * 100), 80.0F, 80.0F);
         }
      }

      var2.setColor(new Color(1.0F, 1.0F, 1.0F, 0.5F));
      var2.fillRect(-320.0F, -240.0F, 640.0F, 480.0F);
      var2.setColor(Color.white);
      var2.drawRect(-320.0F, -240.0F, 640.0F, 480.0F);
   }

   public void update(GameContainer var1, int var2) {
      if (this.scaleUp) {
         this.scale += (float)var2 * 0.001F;
      }

      if (this.scaleDown) {
         this.scale -= (float)var2 * 0.001F;
      }

      float var3 = (float)var2 * 0.4F * (1.0F / this.scale);
      if (this.moveLeft) {
         this.camX -= var3;
      }

      if (this.moveUp) {
         this.camY -= var3;
      }

      if (this.moveRight) {
         this.camX += var3;
      }

      if (this.moveDown) {
         this.camY += var3;
      }

   }

   public void keyPressed(int var1, char var2) {
      if (var1 == 1) {
         System.exit(0);
      }

      if (var1 == 16) {
         this.scaleUp = true;
      }

      if (var1 == 30) {
         this.scaleDown = true;
      }

      if (var1 == 203) {
         this.moveLeft = true;
      }

      if (var1 == 200) {
         this.moveUp = true;
      }

      if (var1 == 205) {
         this.moveRight = true;
      }

      if (var1 == 208) {
         this.moveDown = true;
      }

   }

   public void keyReleased(int var1, char var2) {
      if (var1 == 16) {
         this.scaleUp = false;
      }

      if (var1 == 30) {
         this.scaleDown = false;
      }

      if (var1 == 203) {
         this.moveLeft = false;
      }

      if (var1 == 200) {
         this.moveUp = false;
      }

      if (var1 == 205) {
         this.moveRight = false;
      }

      if (var1 == 208) {
         this.moveDown = false;
      }

   }

   public static void main(String[] var0) {
      try {
         AppGameContainer var1 = new AppGameContainer(new TransformTest2());
         var1.setDisplayMode(640, 480, false);
         var1.start();
      } catch (SlickException var2) {
         var2.printStackTrace();
      }

   }
}
