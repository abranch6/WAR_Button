#include <SoftwareSerial.h>

SoftwareSerial mySerial(PCINT2, PCINT1); // RX, TX
int val = 0;
void setup() 
{
    pinMode(3, OUTPUT);
    mySerial.begin(9600);
    OSCCAL = 0;
}

void loop() 
{
  OSCCAL = val
  mySerial.print("Osccal= ");  
  mySerial.println(OSCCAL,HEX);
  digitalWrite(3, HIGH);
  delay(100);
  digitalWrite(3,LOW);
  val++;
}

