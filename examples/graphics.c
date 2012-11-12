#include "graphics.h"

void drawLine(int x1, int y1, int x2, int y2)
{
  printf("DL %i %i %i %i\n", x1, y1, x2, y2);
}

void drawRect(int x, int y, int width, int height)
{
  printf("DR %i %i %i %i\n", x, y, width, height);
}

void drawOval(int x, int y, int width, int height)
{
  printf("DO %i %i %i %i\n", x, y, width, height);
}

void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle)
{
  printf("DA %i %i %i %i %i %i\n",x,y,width,height, startAngle, arcAngle);
}

void fillRect(int x, int y, int width, int height)
{
  printf("FR %i %i %i %i\n", x, y, width, height);
}

void drawString(char* s, int x, int y)
{
  printf("DS %i %i @%s\n",x,y,s);
}

void setColour(colour c)
{
  char* colourName;
  switch(c)
  {
    case black : colourName = "black"; break;
    case blue : colourName = "blue"; break;
    case cyan : colourName = "cyan"; break;
    case darkgray : colourName = "darkgray"; break;
    case gray : colourName = "gray"; break;
    case green : colourName = "green"; break;
    case lightgray : colourName = "lightgray"; break;
    case magenta : colourName = "magenta"; break;
    case orange : colourName = "orange"; break;
    case pink : colourName = "pink"; break;
    case red : colourName = "red"; break;
    case white : colourName = "white"; break;
    case yellow : colourName = "yellow"; break;
  }
  printf("SC %s\n", colourName);
}

void singleStepOn(void)
{
#ifndef OLDDRAWAPP
  printf("S+\n");
#endif
}

void singleStepOff(void)
{
#ifndef OLDDRAWAPP
  printf("S-\n");
#endif
}

void imageSize(int width, int height)
{
#ifndef OLDDRAWAPP
  printf("IS %i %i\n", width, height);
#endif
}

void drawImage(char *filename, int x, int y, int width, int height)
{
#ifndef OLDDRAWAPP
  printf("DI %i %i %i %i @%s\n", x, y, width, height, filename);
#endif
}

void linearGradient(char *s)
{
#ifndef OLDDRAWAPP
  printf("LG %s\n", s);
#endif
}

void radialGradient(char *s)
{
#ifndef OLDDRAWAPP
  printf("RG %s\n", s);
#endif
}

void imagePattern(char *filename, int x, int y, int width, int height)
{
#ifndef OLDDRAWAPP
  printf("IP %i %i %i %i @%s\n", x, y, width, height, filename);
#endif
}

void drawQuad(int x1, int y1, int cx1, int cy1, int x2, int y2)
{
#ifndef OLDDRAWAPP
  printf("DQ %i %i %i %i %i %i\n", x1, y1, cx1, cy1, x2, y2);
#endif
}

void drawCubic(int x1, int y1, int cx1, int cy1, int cx2, int cy2, int x2, int y2)
{
#ifndef OLDDRAWAPP
  printf("DC %i %i %i %i %i %i %i %i\n", x1, y1, cx1, cy1, cx2, cy2, x2, y2);
#endif
}

void turtlePenDown(void)
{
#ifndef OLDDRAWAPP
  printf("T+\n");
#endif
}

void turtlePenUp(void)
{
#ifndef OLDDRAWAPP
  printf("T-\n");
#endif
}

void turtleForward(int n)
{
#ifndef OLDDRAWAPP
  printf("TF %i\n", n);
#endif
}

void turtleRight(int n)
{
#ifndef OLDDRAWAPP
  printf("TR %i\n", n);
#endif
}

void turtleLeft(int n)
{
#ifndef OLDDRAWAPP
  printf("TL %i\n", n);
#endif
}
