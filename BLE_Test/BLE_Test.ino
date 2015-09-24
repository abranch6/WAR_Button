#include <SoftwareSerial.h>

SoftwareSerial mySerial(10, 11); // RX, TX

void setup() 
{
    pinMode(10, INPUT);
    pinMode(11, OUTPUT);
    
    pinMode(7, INPUT);
    pinMode(13, OUTPUT);
    mySerial.begin(9600);
    delay(2000);
    mySerial.write("CENT=OFF");
    mySerial.write("\r");
}

void loop() 
{
 char last = 0;
 
 if(digitalRead(7) == HIGH)
 {
    mySerial.write("ADV ON");
    mySerial.write("\r");
    digitalWrite(13, HIGH);
    delay(2000);
 }
 else if(digitalRead(7) == LOW)
 {
   mySerial.write("ADV OFF");
   mySerial.write("\r");
   digitalWrite(13,LOW);
   delay(2000);
 }
}
