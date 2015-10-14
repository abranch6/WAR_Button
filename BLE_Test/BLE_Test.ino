#include <SoftwareSerial.h>

SoftwareSerial mySerial(10, 9); // RX, TX
char mode = 0;
char changed = 1;
void setup() 
{
    
    pinMode(0, INPUT);
    digitalWrite(0,HIGH);
    pinMode(3, OUTPUT);
    mySerial.begin(9600);
    delay(500);
    mySerial.write("CENT=OFF");
    mySerial.write("\r");
}

void loop() 
{
 
 if(digitalRead(0) == HIGH)
 {
   delay(50);
   while(digitalRead(0) == HIGH);
   mode = !mode;
   changed = 1;
 }
 
 if(!mode && changed)
 {
    mySerial.write("ADV ON");
    mySerial.write("\r");
    digitalWrite(3, HIGH);
    changed = 0;
    delay(50);
 }
 else if(mode && changed)
 {
   mySerial.write("ADV OFF");
   mySerial.write("\r");
   digitalWrite(3,LOW);
   changed = 0;
   delay(50);
 }
}
