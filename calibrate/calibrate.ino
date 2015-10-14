#include <SoftwareSerial.h>

SoftwareSerial mySerial(8, 9); // RX, TX
int val = 255;
void setup() 
{
    pinMode(8, INPUT);
    pinMode(9, OUTPUT);
    pinMode(10, OUTPUT);
    mySerial.begin(9600);
    //OSCCAL = 0x69;
    //61 to 73 hex
}

void loop() 
{

  mySerial.print("Osccal= ");  
  mySerial.println(OSCCAL,HEX);
  delay(100);
}

