#include "graphics.h"

int main(void) {
  char *filename = "/home/Martin/ucl/coursewk/comp2013/drawappfx/examples/avatar.jpeg";
  imageSize(400, 400);
  imagePattern(filename, -25, 0, 75, 50);
  fillRect(25, 25, 350, 350);
  linearGradient("from 50% 0% to 100% 0%, reflect, 0xffffff00 0%, 0xffffffff 100%");
  fillRect(30, 30, 340, 340);
  drawImage(filename, 50, 250, 75, 100);
  radialGradient("center 50% 50%, radius 75%, 0x00008080 0%, 0x00ff0020 100%");
  fillRect(270, -70, 200, 200);
  setColour(red);
  drawQuad(175, 325, 250, 350, 325, 200);
  drawCubic(75, 200, 100, 0, 150, 300, 200, 50);
  return 0;
}
