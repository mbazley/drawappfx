#include "graphics.h"

int main(void) {
  int i;
  turtleForward(200);
  turtleLeft(90);
  turtleForward(50);
  setColour(orange);
  turtlePenDown();
  for(i = 0; i < 3; i++) {
    turtleForward(100);
    turtleRight(120);
  }
  turtlePenUp();
  turtleForward(150);
  turtlePenDown();
  for(i = 0; i < 5; i++) {
    int n;
    for(n = 0; n < 5; n++) {
      setColour(darkgray);
      turtleForward(10);
      setColour(lightgray);
      turtleForward(10);
    }
    turtleLeft(72);
  }
  turtlePenUp();
  return 0;
}
