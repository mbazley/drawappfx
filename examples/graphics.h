#include <stdio.h>
#include <stdbool.h>

enum colour {black,blue,cyan,darkgray,gray,green,lightgray,magenta,orange,pink,red,white,yellow};
typedef enum colour colour;

void drawLine(int,int,int,int);
void drawRect(int,int,int,int);
void drawOval(int,int,int,int);
void drawArc(int,int,int,int,int,int);
void fillRect(int,int,int,int);
void drawString(char*,int,int);

void setColour(colour);

void singleStepOn(void);
void singleStepOff(void);
void imageSize(int,int);

void drawImage(char*,int,int,int,int);
void linearGradient(char*);
void radialGradient(char*);
void imagePattern(char*,int,int,int,int);
void drawQuad(int,int,int,int,int,int);
void drawCubic(int,int,int,int,int,int,int,int);

void turtlePenDown(void);
void turtlePenUp(void);
void turtleForward(int);
void turtleRight(int);
void turtleLeft(int);
