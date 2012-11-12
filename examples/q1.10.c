#include "graphics.h"

char *bar1[] = {"\"I've made an", "unannounced", "alteration to", "your timetable",
		"affecting your", "class starting", "five minutes",
                "from now.", "What do you", "mean you're",
                "not at your", "computer?\""};
char *bar2[] = {"\"You are", "receiving this", "email because", "you are",
		"subscribed to", "the Moodle", "forum 'Inane", "Chatter'. This",
		"forum forces", "everyone to be", "subscribed.\""};
char *bar3[] = {"\"Do YOU want", "to earn £££ in", "your spare",
                "time simply by", "completing a", "few simple", "tests? Send",
                "email with", "\'Diamorphine", "Study\' as", "subject to Dr",
                "H. Shipman", "ASAP.\""};
char *bar4[] = {"\"Yo student-", "dudes &", "dudettes! The", "Volunteering",
		"Unit is like", "totally rad,", "innit? You do all",
		"this hard work,", "like and you", "DON'T GET", "PAID! Phat!",
		"Um, bluds!", "Erm, chillax!\""};
char *bar5[] = {"\"Thanks to our", "generous", "sponsors", "Deutsche",
		"Bank, we can", "EXCLUSIVELY", "ask you: have",
		"you ever", "considered a", "fulfilling,", "dynamic career",
		"in debt", "recovery?\""};

const int size1 = sizeof(bar1)/sizeof(bar1[0]);
const int size2 = sizeof(bar2)/sizeof(bar2[0]);
const int size3 = sizeof(bar3)/sizeof(bar3[0]);
const int size4 = sizeof(bar4)/sizeof(bar4[0]);
const int size5 = sizeof(bar5)/sizeof(bar5[0]);

/* Print multi-line text */
void manylines(char *str[],int size,int xpos, int ypos) {
  int i = 0;
  while(i < size) {
    drawString(str[i],xpos,ypos + i*12);
    i++;
  }
}

int main(void) {
  int i = 0;

  /* Draw bars */
  setColour(red);
  fillRect(40,40+(100-95),90,95);
  setColour(yellow);
  fillRect(130,40+(100-60),90,60);
  setColour(blue);
  fillRect(220,40+(100-25),90,25);
  setColour(magenta);
  fillRect(310,40+(100-5),90,5);
  setColour(green);
  fillRect(400,140,90,120);

  /* Draw gridlines */
  setColour(black);
  drawLine(40,40,40,140);
  drawLine(40,140,490,140);
  while(i <= 100) {
    drawLine(40,140-i,35,140-i);
    i += 25;
  }

  /* Draw y-axis labels */
  drawString("0%",0,140);
  drawString("25%",0,115);
  drawString("50%",0,90);
  drawString("75%",0,65);
  drawString("100%",0,40);

  /* Draw title */
  drawString("Relevance to myself or my course of emails found in my inbox",0,20);

  /* Draw x-axis labels */
  manylines(bar1,size1,40,152);
  manylines(bar2,size2,130,152);
  manylines(bar3,size3,220,152);
  manylines(bar4,size4,310,152);
  manylines(bar5,size5,400,152);

  return 0;
}
