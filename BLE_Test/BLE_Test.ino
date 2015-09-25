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
    delay(500);
    mySerial.write("CENT=OFF");
    mySerial.write("\r");
}

void loop() 
{
 digitalWrite(3,LOW);
 OSCCAL=val;
 if(digitalRead(2) == HIGH)
 {
    mySerial.write("ADV ON");
    mySerial.write("\r");
 //   digitalWrite(3, HIGH);
    delay(50);
 }
 else
 {
   mySerial.write("ADV OFF");
   mySerial.write("\r");
   //digitalWrite(3,LOW);
   delay(50);
 }
 
 while(val == 0)
 {
   digitalWrite(3,HIGH);
 }
 val--;
}
