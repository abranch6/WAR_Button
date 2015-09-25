#include <SoftwareSerial.h>

SoftwareSerial mySerial(9, 8); // RX, TX
int val = 255;
void setup() 
{
    pinMode(9, INPUT);
    pinMode(8, OUTPUT);
    
    pinMode(2, INPUT);
    pinMode(3, OUTPUT);
    mySerial.begin(9600);
}

void loop() 
{
 int i = 0;
 digitalWrite(3,LOW);
 for(i = 0; i < 256; i++)
 {
   OSCCAL = i;
   mySerial.print("Osccal= ");  
   mySerial.println(OSCCAL,HEX);
   delay(100);
 }
 
 
 while(1)
 {
   digitalWrite(3,HIGH);
 }
}
