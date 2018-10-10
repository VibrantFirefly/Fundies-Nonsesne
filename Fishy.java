# Fundies-Nonsesne

import tester.*;                // The tester library
import javalib.worldimages.*;   // images, like RectangleImage or OverlayImages
import javalib.funworld.*;      // the abstract World class and the big-bang library
import java.awt.Color;          // general colors (as triples of red,green,blue values)
                                // and predefined colors (Red, Green, Yellow, Blue, Black, White)


class Fishy extends World {
  // TODO: makeScene
  static int SCENE = 500;
  MyFish fish;
  GameFish otherfish;
  
  Fishy(MyFish fish) {
    super();
    this.fish = fish;
  }
  
  public WorldScene makeScene() {
    // TODO: make the fucking scene
  }
  
  public World start() {
    // TODO: create key event to start game
  }
  
  public World onTick() {
    // TODO: create the on tick 
  }
  
  public WorldEnd gameOver() {
    if (fish.eatFish(otherfish) == null) {
      
    }
    return new WorldEnd(true, this.makeScene().placeImageXY(
                    new TextImage("Good Luck Finding Nemo", 20, FontStyle.BOLD, Color.ORANGE),
                    250, 250));
  }
  
  // TODO: background
  
}

/* FIELDS:
 * WorldImage body
 * Posn p
 * METHODS: 
 * fishRight() -> WorldImage
 * fishLeft() -> WorldImage
 */



abstract class Fish {
  
  WorldImage direction; // direction that the fish is facing
  Posn p; // location of the pinhole
  int size; // size of the fish 
  int SIZEFACTOR = 2;
  
  // the constructor
  Fish(WorldImage direction, Posn p, int size) {
    
    this.direction = direction; 
    this.p = p;
    this.size = size;
  }
  
  // creates the shape of the fish facing right
  public WorldImage fishRight(OutlineMode o, Color co) {
    // TODO: Combo of oval & triangle facing right proportional to size
    return new EllipseImage(40, 30, o, co);
  }
  
  // creates the shape of the fish facing left 
  public WorldImage fishLeft(Color c) {
    // TODO: Combo of oval & triangle facing left proportional to size
    return new EllipseImage(40, 30, o, co);
  }
}

/* FIELDS:
 * WorldImage body
 * Posn p
 * Color c
 * OutlineMode ol
 * METHODS: 
 * fishLeft() -> WorldImage
 * fishRight() -> WorldImage
 * moveFish(String) -> MyFish
 * eatFish(GameFish) -> MyFish
 * inBounds() -> MyFish
 */

class MyFish extends Fish {
  
  Color c = Color.YELLOW;
  OutlineMode olm = OutlineMode.SOLID;
  
  MyFish(WorldImage direction, Posn p, int size) {
    super(direction, p, size);
  }
  
  // moves MyFish based on keystroke
  MyFish moveFish(String ke) {
    if (ke.equals("right")) {
      return new MyFish(this.fishRight(c), new Posn(this.p.x + 5, this.p.y), this.size);
    }
    if (ke.equals("left")) {
      return new MyFish(this.fishLeft(c), new Posn(this.p.x - 5, this.p.y), this.size);
    }
    if (ke.equals("up")) {
      return new MyFish(this.direction, new Posn(this.p.x, this.p.y + 5), this.size);
    }
    if (ke.equals("down")) {
      return new MyFish(this.direction, new Posn(this.p.x, this.p.y - 5), this.size);
    }
    else {
      return this;
    }
  }
  
  // if GameFish enters MyFish, go to eatFish
  MyFish interactFish(GameFish f) { 
    if (f.p.x + f.size / 2 == this.p.x + this.size / 2) {
      return this.eatFish(f);
    }
    if (f.p.y + f.size * SIZEFACTOR / 2 == this.p.y + this.size * SIZEFACTOR / 2) {
      return this.eatFish(f);
    }
    else {
      return this;
    }
  }
  
  // adds to size of MyFish if it eats a fish smaller than it
  MyFish eatFish(GameFish f) {
    if (f.size <= this.size) {
      return new MyFish(this.direction, this.p, this.size + 5);
    }
    else {
      return null;
    }
  }
  
  MyFish inBounds() {
    // TODO: if MyFish.Posn approaches edge of scene, loop it around
    if (this.p.x + this.size / 2 >= Fishy.SCENE) {
      return new MyFish(this.direction, new Posn(0, this.p.y), this.size);
    }
    if (this.p.y + this.size * SIZEFACTOR / 2 >= Fishy.SCENE) {
      return new MyFish(this.direction, new Posn(this.p.x, 0), this.size);
    }
    else {
      return this;
    }
  }
}

/* FIELDS:
 * WorldImage body
 * Posn p
 * Color c
 * OutlineMode ol
 * METHODS: 
 * fishMaker() -> WorldImage
 * moveFish() -> GameFish
 * eatFish() -> MyFish
 */

class GameFish extends Fish {
  
  OutlineMode ol = OutlineMode.SOLID;
  Color c = Color.BLUE;
  
  GameFish(WorldImage direction, Posn p, int size) {
    super(direction, p, size);
  }
  
  GameFish moveFish() {
    // TODO: create left and right moving fish (at random speeds proportional to their size?
    // do I even need this?
  }
  
  // if GameFish enters MyFish, go to eatFish
  GameFish interactFish(MyFish f) { 
    if (f.p.x + f.size / 2 == this.p.x + this.size / 2 &&
        f.size >= this.size) {
      return this.eatFish(f);
    }
    if (f.p.y + f.size * SIZEFACTOR / 2 == this.p.y + this.size * SIZEFACTOR / 2 &&
        f.size >= this.size) {
      return this.eatFish(f);
    }
    else {
      return this;
    }
  }
  
  // removes GameFish if MyFish is larger
  GameFish eatFish(MyFish f) {
    return null;
  }
}

class ExamplesFishy {
  MyFish fish1 = new MyFish(new EllipseImage(40,30,OutlineMode.SOLID, Color.YELLOW), 
      new Posn(0,0), 30);
  // TODO: GameFish Example
  
  // TODO: make all the tests for the methods
}

